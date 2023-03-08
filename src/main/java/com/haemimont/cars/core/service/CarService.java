package com.haemimont.cars.core.service;

import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.tools.DbUtil;
import com.haemimont.cars.core.tools.Generator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarService<Car> extends CrudService<Car> {
    Connection connection;
    CarStatements carStatements;

    public CarService() {
        this.connection = DbUtil.getConnection();
        this.carStatements = new CarStatements();
    }

    @Override
    public ArrayList<Car> get(String critaria,String value) {
        ArrayList<Car> arrayList = new ArrayList<>();
       if(critaria!=null&&!critaria.equals("")) {//check if we have criteria
           if(critaria.equals("all")){arrayList = (ArrayList<Car>)carStatements.getAllCarsFromDb(connection);}
           if(critaria.equals("id")){arrayList = (ArrayList<Car>) carStatements.getCarsFromDb(critaria, value, connection);
           }
       }
        return  arrayList;
    }

    @Override
    public String put(Car obj) {
        boolean successful = false;
        try {
            com.haemimont.cars.core.model.Car car = (com.haemimont.cars.core.model.Car) obj;
            car.getIdentification().setVin(Generator.vinGenerator(car,new Storage()));
            try {//turning off autocommit
                connection.setAutoCommit(false);
                CustomLogger.logInfo("Turned off autocommit");
            } catch (
                    SQLException e) {
                CustomLogger.logError("Failed to turn off autocommit");
            }
            try {
                int idDimension, idFuel, idIdentification, idEngineStatistics, idEngineInformation;

                idDimension = carStatements.insertCarInDimension(car, connection);

                idFuel = carStatements.insertCarInFuelInformation(car, connection);

                idIdentification = carStatements.insertCarInIdentification(car, connection);

                idEngineStatistics = carStatements.insertCarInEngineStatistics(car, connection);

                idEngineInformation = carStatements.insertCarInEngineStatistics(car, idEngineStatistics,
                        connection);

                successful =  carStatements.insertCarFromUserInputInCar(car, idDimension, idEngineInformation, idFuel, idIdentification,
                        connection);
                connection.commit();//if there is no errors we commit the changes
                CustomLogger.logInfo("car:" + car.getIdentification().getVin() + " inserted in the db:"
                        + connection.getCatalog());
            } catch (Exception e) {
                CustomLogger.logError("car" + car.getIdentification().getVin() +
                        "is already in the db|Rolling back changes");
                try {
                    connection.rollback();
                    CustomLogger.logInfo("Rolled back changes");
                } catch (SQLException ex) {
                    CustomLogger.logError("Failed to roll back");
                }
            }
            try {//turning on autocommit
                connection.setAutoCommit(true);
                CustomLogger.logInfo("Turned on autocommit");
            } catch (SQLException e) {
                CustomLogger.logError("Failed to turn on autocommit");
            }
        }catch (Exception e){CustomLogger.logError("Could not turn obj to car");}
        if(successful){
            return "Successfully added the car";
        }
        else{return " An error occurred when trying to add the car";}
    }

    @Override
    public Car update(Car obj) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
