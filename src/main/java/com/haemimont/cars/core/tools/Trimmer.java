package com.haemimont.cars.core.tools;

@Deprecated//found a better way to do it
public class Trimmer {
    //splits a line and returns the chunks

    public static String[] customTrim(String rawLine) {
        String[] valuesInTheLine = rawLine.split(",");
        for (int n = 0; n < valuesInTheLine.length; n++) {
            valuesInTheLine[n] = valuesInTheLine[n].replace('"', ' ').trim();
        }
        return valuesInTheLine;
    }
}
