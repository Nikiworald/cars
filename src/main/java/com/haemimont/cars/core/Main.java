package com.haemimont.cars.core;
import com.haemimont.cars.core.model.*;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String path="src/main/java/csv/cars.csv";
        FromLinesToCars.linesToCars(path,656);//There is no data on the first line
        Storage<Car> storageForCars = new Storage<Car>();
        storageForCars.carsToStorage(FromLinesToCars.linesToCars(path,6));
        }
    }
