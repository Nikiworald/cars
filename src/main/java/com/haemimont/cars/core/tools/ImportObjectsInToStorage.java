package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.storage.Storage;

public class ImportObjectsInToStorage {
    public static void objectsToStorage(Storage storage, Object[] objects){
        for (int i = 1; i < objects.length;i++)
        {
            storage.put(i,objects[i]);
        }

    }
}
