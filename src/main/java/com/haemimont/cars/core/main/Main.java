package com.haemimont.cars.core.main;
import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.sql.ConnectionManager;
import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.tools.FromLinesToObjects;
import com.haemimont.cars.core.tools.StorageTools;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();
        Car[] cars = FromLinesToObjects.linesToCars(config.getPath(), 50);//There is no data on the first line (n-1)
        Storage<String, Car> storageForCars = new Storage<>();//creating a new storage for cars
        StorageTools.importObjectsInToStorage(storageForCars, cars);//importing cars in to the storage
        String[] keys = storageForCars.keySet().toArray(new String[0]);//getting all the keys for the storage
        CarStatements carStatements = new CarStatements();
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.connect(config.getUrl(), config.getName(), config.getPassword());
        try {//turning of autocommit
            connectionManager.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //for each key we get its object and put it in the db
        for(String key:keys){
            //check if there is a matching vin in the db if not inserts the information
           try {
               int idDimension, idFuel, idIdentification, idEngineStatistics, idEngineInformation;
               carStatements.fillDimensionAndSetId(storageForCars.get(key), connectionManager.getConnection());
               idDimension = carStatements.getDimensionId();
               carStatements.fillFuelInformationAndSetId(storageForCars.get(key), connectionManager.getConnection());
               idFuel = carStatements.getFuelId();
               carStatements.fillIdentificationAndSetId(storageForCars.get(key), connectionManager.getConnection());
               idIdentification = carStatements.getIdentificationId();
               carStatements.fillEngineStatisticsAndSetId(storageForCars.get(key), connectionManager.getConnection());
               idEngineStatistics = carStatements.getEngineStatisticsId();
               carStatements.fillEngineInformationAndSetId(storageForCars.get(key), idEngineStatistics,
                       connectionManager.getConnection());
               idEngineInformation = carStatements.getEngineInformationId();

               carStatements.fillCar(storageForCars.get(key), idDimension, idEngineInformation, idFuel, idIdentification,
                       connectionManager.getConnection());
               connectionManager.getConnection().commit();//if there is no error we commit the changes
           }
           catch (Exception e){
               System.out.println("imago");
               try {
                   connectionManager.getConnection().rollback();
               } catch (SQLException ex) {
                   throw new RuntimeException(ex);
               }
           }
        }
        //System.out.println("uploaded:"+uploaded);
        //System.out.println("not uploaded:"+notUploaded);
        ArrayList<Car> testCars =  carStatements.fromDbMakeToCarObj("BMW",connectionManager.getConnection());//gets all the cars with make = ?
        connectionManager.disconnect();
    }
}
