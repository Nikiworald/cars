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
    final Connection connection;
    final CarStatements carStatements;

    public CarService() {
        this.connection = DbUtil.getConnection();
        this.carStatements = new CarStatements();
    }

    @Override
    public ArrayList<Car> get(String criteria, String value) {
        ArrayList<Car> arrayList = new ArrayList<>();
        if (criteria != null && !criteria.equals("")) {//check if we have criteria
            if (criteria.equals("all")) {
                arrayList = (ArrayList<Car>) carStatements.getAllCarsFromDb(connection);
            }
            if (criteria.equals("id")) {
                arrayList = (ArrayList<Car>) carStatements.getCarsFromDb(criteria, value, connection);
            }
        }
        return arrayList;
    }

    @Override
    public String put(Car obj) {
        boolean successful = false;
        try {
            com.haemimont.cars.core.model.Car car = (com.haemimont.cars.core.model.Car) obj;
            car.getIdentification().setVin(Generator.vinGenerator(car, new Storage<>()));
            try {       //turning off autocommit
                connection.setAutoCommit(false);
                CustomLogger.logInfo("Turned off autocommit");
            } catch (
                    SQLException e) {
                CustomLogger.logError("Failed to turn off autocommit");
            }
            try {
                int idDimension = carStatements.insertCarInDimension(car, connection);       //insert the information and get back the id
                int idFuel = carStatements.insertCarInFuelInformation(car, connection);
                int idIdentification = carStatements.insertCarInIdentification(car, connection);
                int idEngineStatistics = carStatements.insertCarInEngineStatistics(car, connection);
                int idEngineInformation = carStatements.insertCarInEngineStatistics(car, idEngineStatistics, connection);
                successful = carStatements.insertCarFromUserInputInCar(car,     //custom statement for uploading a car to the db from user input
                        idDimension, idEngineInformation,
                        idFuel, idIdentification, connection);
                connection.commit();        //if there is no errors we commit the changes
                CustomLogger.logInfo("car:" + car.getIdentification().getVin() + " inserted in the db:"
                        + connection.getCatalog());
            } catch (Exception e) {
                CustomLogger.logError("car" + car.getIdentification().getVin() +
                        "is already in the db|Rolling back changes");
                try {
                    connection.rollback();      //If there was an error we roll back the changes
                    CustomLogger.logInfo("Rolled back changes");
                } catch (SQLException ex) {
                    CustomLogger.logError("Failed to roll back");
                }
            }
            try {       //turning on autocommit after we are done
                connection.setAutoCommit(true);
                CustomLogger.logInfo("Turned on autocommit");
            } catch (SQLException e) {
                CustomLogger.logError("Failed to turn on autocommit");
            }
        } catch (Exception e) {
            CustomLogger.logError("Could not turn obj to car");
        }
        if (successful) {
            return "Successfully added the car";
        } else {
            return " An error occurred when trying to add the car";
        }
    }

    @Override
    public Car update(Car obj) {
        com.haemimont.cars.core.model.Car car = (com.haemimont.cars.core.model.Car) obj;
        if (car.getIdentification().getVin() == null || car.getIdentification().getVin().equals("")) {      //if there is not vin we make one
            Generator.vinGenerator(car, new Storage<>());
        }
        int id = carStatements.updateCar(car, connection);
        return (Car) carStatements.getCarById(id, connection);
    }

    @Override
    public boolean delete(int id) {
        return carStatements.deleteCarById(id, DbUtil.getConnection());     //returns true if car is deleted
    }
}
