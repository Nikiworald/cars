package com.haemimont.cars.core.tools;
import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.storage.Storage;

public class StorageTools {
    //imports objects in to a storage with their keys being generated vin
    public static void importObjectsInToStorage(Storage storage, Car[] cars){
        for (int i = 1; i < cars.length;i++)
        {
            storage.put(Generator.vinGenerator(cars[i],storage),cars[i]);
        }
    }
}
