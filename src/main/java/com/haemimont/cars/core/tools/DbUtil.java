package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.logger.CustomLogger;
import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.sql.ConnectionManager;
import com.haemimont.cars.core.storage.Storage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil {
    static Connection connection = null;

    static {
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/cars");
            connection = ds.getConnection();
        } catch (NamingException | SQLException b) {
            CustomLogger.logError("could not connect to DB:" + b);
        }
    }

    public static void initializeDb() {
//        Config config = new Config();
        Car[] cars = CsvHandler.linesToCars(Config.getCsvFilePath(), 500);//There is no data on the first line (n-1)
        Storage<String, Car> storageForCars = new Storage<>();//creating a new storage for cars
        StorageTools.putCarsInStorage(storageForCars, cars);//importing cars in to the storage
        String[] keys = storageForCars.keySet().toArray(new String[0]);//getting all the keys for the storage
        CarStatements carStatements = new CarStatements();
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.connect(Config.getDbUrl(), Config.getUserName(), Config.getPassword());
        Connection connection = connectionManager.getConnection();
        try {//turning off autocommit
            connectionManager.getConnection().setAutoCommit(false);
            CustomLogger.logInfo("Turned off autocommit");
        } catch (
                SQLException e) {
            CustomLogger.logError("Failed to turn off autocommit");
        }
        //for each key we get its object and put it in the db
        for (String key : keys) {
            Car currentCar = storageForCars.get(key);
            //check if there is a matching vin in the db if not insert the information
            try {
                int idDimension, idFuel, idIdentification, idEngineStatistics, idEngineInformation;

                idDimension = carStatements.insertCarInDimension(currentCar, connection);

                idFuel = carStatements.insertCarInFuelInformation(currentCar, connection);

                idIdentification = carStatements.insertCarInIdentification(currentCar, connection);

                idEngineStatistics = carStatements.insertCarInEngineStatistics(currentCar, connection);

                idEngineInformation = carStatements.insertCarInEngineStatistics(currentCar, idEngineStatistics,
                        connection);

                carStatements.insertCarFromStorageInCar(storageForCars.get(key), idDimension, idEngineInformation, idFuel, idIdentification,
                        connectionManager.getConnection());
                connectionManager.getConnection().commit();//if there is no errors we commit the changes
                CustomLogger.logInfo("car:" + currentCar.getIdentification().getVin() + " inserted in the db:"
                        + connection.getCatalog());
            } catch (Exception e) {
                CustomLogger.logError("car" + currentCar.getIdentification().getVin() +
                        " is already in the db|Rolling back changes");
                try {
                    connectionManager.getConnection().rollback();
                    CustomLogger.logInfo("Rolled back changes");
                } catch (SQLException ex) {
                    CustomLogger.logError("Failed to roll back");
                }
            }
        }
        try {//turning on autocommit
            connectionManager.getConnection().setAutoCommit(true);
            CustomLogger.logInfo("Turned on autocommit");
        } catch (SQLException e) {
            CustomLogger.logError("Failed to turn on autocommit");
        }
        connectionManager.disconnect();
    }

    public static Connection getConnection() {
        return connection;
    }

    public static String getAvailableVin(Car car) {
        int i = 'A';
        String vin;
        CarStatements carStatements = new CarStatements();
        if (car.getIdentification().getVin() == null || car.getIdentification().getVin().equals("")) {
            car.getIdentification().setVin(Generator.vinGenerator(car, new Storage<>()));
        }
        while (true) {
            vin = "";
            char[] vinToChars = car.getIdentification().getVin().toCharArray();
            vinToChars[vinToChars.length - 1] = (char) i;
            StringBuilder vinBuilder = new StringBuilder(vin);
            for (char vinToChar : vinToChars) {

                vinBuilder.append(vinToChar);
            }
            vin = vinBuilder.toString();
//            car.getIdentification().setVin(vin);
            if (!carStatements.checkForMatchingVin(vin, connection)) {
                return vin;
            } else {
                if (i == (int) 'Z') {
//                    String vin = car.getIdentification().getVin();
                    vin += "A";
                    car.getIdentification().setVin(vin);
                    i = 'A';
                } else {
                    i++;
                }
            }
        }
    }
}
