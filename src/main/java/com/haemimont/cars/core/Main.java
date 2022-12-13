package com.haemimont.cars.core;
import com.haemimont.cars.core.model.*;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Car> hashMapOfCars = new HashMap<>();
        String path="src/main/java/csv/cars.csv";
        hashMapOfCars = AddToHashMap.addToHashMap(hashMapOfCars,path,656);//first line is not data
        System.out.println(hashMapOfCars.get(5));
        }
    }
