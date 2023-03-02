package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.model.User;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.sql.ConnectionManager;
import com.haemimont.cars.core.sql.UserStatements;
import com.haemimont.cars.core.storage.Storage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class InitDB {
    public static void initializeDb() {
//        Config config = new Config();
        String s = Config.getCsvFilePath();
        Car[] cars = FromLinesToObjects.linesToCars(Config.getCsvFilePath(), 500);//There is no data on the first line (n-1)
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
        ArrayList<Car> testCars = carStatements.getCarsFromDb("make","BMW", connectionManager.getConnection());//gets all the cars with


        //user test
        UserStatements userStatements = new UserStatements();
        try {//turning off autocommit
            connectionManager.getConnection().setAutoCommit(false);
            CustomLogger.logInfo("Turned off autocommit");
        } catch (
                SQLException e) {
            CustomLogger.logError("Failed to turn off autocommit");
        }
        String name = "Admin";
        String password = "1234";
        User user = new User(name,password);
        userStatements.insertUserToDb(user,connection);
        try {
            connection.commit();
        } catch (SQLException e) {
            CustomLogger.logError("Could not commit changes to user");
        }
        boolean check =  userStatements.checkForMatchingNameAndPassword("a","a",connection);


        connectionManager.disconnect();
    }
}
