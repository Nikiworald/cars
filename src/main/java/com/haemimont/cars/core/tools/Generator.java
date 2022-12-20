package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.model.Identification;

import java.time.Year;

public class Generator {
    public static String vinGenerator(Car car){
        String temp ="";
        String vin="";
        String[] value = new String[5];
        temp = car.getMake();
        value[0]= "" + temp.charAt(0);

        temp = car.getModel();
        value[1]= ""+temp.charAt(temp.length()-2)+temp.charAt(temp.length()-1);

        temp = ""+car.getYear();
        value[2]= ""+temp.charAt(temp.length()-2) + temp.charAt(temp.length()-1);

        temp = car.getDriveLin();
        value[3]= "" +temp.charAt(0);

        temp = car.getClassification();
        value[4]="" + temp.charAt(0);

        for (String x:value) {
            vin += x;
        }
        return vin;


    }
}
