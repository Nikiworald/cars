package com.haemimont.cars.core.tools;

public class Trimmer {
    public static String[] customTrim(String rawLine){
        String[] valuesInTheLine = rawLine.split(",");
        for (int n = 0; n < valuesInTheLine.length; n++) {
            valuesInTheLine[n] = valuesInTheLine[n].replace('"', ' ').trim();
        }
        return valuesInTheLine;
    }
}
