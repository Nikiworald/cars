package com.haemimont.cars.core.model;

public class Trimmer {
    public static String[] customTrim(String rawLine){
        String[] values = rawLine.split(",");
        for (int n = 0; n < values.length; n++) {
            values[n] = values[n].replace('"', ' ').trim();

        }
        return values;
    }
}
