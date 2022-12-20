package com.haemimont.cars.core.main;
import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.tools.FromLinesToObjects;
import com.haemimont.cars.core.tools.ImportCarsInToStorage;

public class Main {

    private static com.haemimont.cars.core.model.Car Car;

    public static void main(String[] args) {

        String path="src/main/java/csv/cars.csv";
        Car[] cars = FromLinesToObjects.linesToCars(path,5);//There is no data on the first line (n-1)
        Storage<Integer,Car> storageForCars = new Storage<>();
        ImportCarsInToStorage.objectsToStorage(storageForCars,cars);





        }
    }
