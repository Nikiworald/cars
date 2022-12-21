package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.storage.Storage;

public class ImportCarsInToStorage {
    public static void objectsToStorage(Storage storage, Car[] cars){
        for (int i = 1; i < cars.length;i++)
        {
            storage.put(Generator.vinGenerator(cars[i]),cars[i]);
        }

    }
}
