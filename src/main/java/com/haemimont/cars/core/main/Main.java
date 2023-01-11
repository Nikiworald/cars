package com.haemimont.cars.core.main;
import com.haemimont.cars.core.SqlQuery.Queries;
import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.tools.FromLinesToObjects;
import com.haemimont.cars.core.tools.ImportCarsInToStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Set;

public class Main {

    //private static com.haemimont.cars.core.model.Car Car;

    public static void main(String[] args) {
        String name = "root";
        String password = "niki";
        String url = "jdbc:mysql://localhost:3306/cars";
        String path = "src/main/java/csv/cars.csv";

        Car[] cars = FromLinesToObjects.linesToCars(path, 50);//There is no data on the first line (n-1)
        Storage<String, Car> storageForCars = new Storage<>();
        ImportCarsInToStorage.objectsToStorage(storageForCars, cars);
        String[] keys = storageForCars.keySet().toArray(new String[0]);
        Queries queries = new Queries(url,name,password);

        for(String key:keys){
            /*int idDimension,idFuel,idIdentification,idEngineStatistics,idEngineInformation;
            queries.fillDimension(key,storageForCars);
            queries.fillFuelInformation(key,storageForCars);
            queries.fillIdentification(key,storageForCars);
            queries.fillEngineStatistics(key,storageForCars);
             idEngineStatistics = queries.getEngineStatisticsId();
            queries.fillEngineInformation(key,storageForCars,idEngineStatistics);

            idDimension= queries.getDimensionId();
            idFuel = queries.getFuelId();
            idIdentification = queries.getIdentificationId();
            idEngineInformation = queries.getEngineInformationId();

            queries.fillCar(key,storageForCars,idDimension,idEngineInformation,idFuel,idIdentification);*/
        }
        queries.fromRowToCar(7);


    }
}
