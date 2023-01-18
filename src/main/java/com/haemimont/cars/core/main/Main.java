package com.haemimont.cars.core.main;
import com.haemimont.cars.core.Sql.Queries;
import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.tools.FromLinesToObjects;
import com.haemimont.cars.core.tools.StorageTools;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String name = "root";
        String password = "niki";
        String url = "jdbc:mysql://localhost:3306/cars";
        String path = "src/main/java/csv/cars.csv";

        Car[] cars = FromLinesToObjects.linesToCars(path, 500);//There is no data on the first line (n-1)
        Storage<String, Car> storageForCars = new Storage<>();
        StorageTools.importObjectsInToStorage(storageForCars, cars);
        String[] keys = storageForCars.keySet().toArray(new String[0]);
        Queries queries = new Queries(url,name,password);

        for(String key:keys){
            int idDimension,idFuel,idIdentification,idEngineStatistics,idEngineInformation;
            queries.fillDimension(key,storageForCars);
            queries.fillFuelInformation(key,storageForCars);
            queries.fillIdentification(key,storageForCars);
            queries.fillEngineStatistics(key,storageForCars);
             idEngineStatistics = queries.getLatestEngineStatisticsId();
            queries.fillEngineInformation(key,storageForCars,idEngineStatistics);

            idDimension= queries.getLatestDimensionId();
            idFuel = queries.getLatestFuelId();
            idIdentification = queries.getLatestIdentificationId();
            idEngineInformation = queries.getLatestEngineInformationId();

            queries.fillCar(key,storageForCars,idDimension,idEngineInformation,idFuel,idIdentification);

        }

        ArrayList testCars =  queries.fromCarMakeToCarObj("BMW");
        int a = 0;

    }
}
