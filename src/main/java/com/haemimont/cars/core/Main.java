package com.haemimont.cars.core;

import com.haemimont.cars.core.model.*;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        String path="src/main/java/csv/cars.csv";
        int i = 0;

        String rawLine = FileReader.Reader(path,2);//information starts from 2
        //System.out.println(rawLine);
        String[] values = Trimmer.customTrim(rawLine);
        //System.out.println(values);
        Dimension dimension = new Dimension();
        EngineInformation engineInformation= new EngineInformation();
        EngineStatistics engineStatistics = new EngineStatistics();
        FuelInformation fuelInformation = new FuelInformation();
        Identification identification = new Identification();
        engineInformation.setEngineStatistics(engineStatistics);
       while(!FileReader.Reader(path,i).isEmpty() ){
            linkedList.add(Car.CarBuilder.newInstance().setDimension(dimension).setIdentification(identification)
                    .setEngineInformation(engineInformation).setFuelInformation(fuelInformation)
                    .setHeight(values[0]).setLength(values[1])
                    .setWidth(values[2]).setDriveLine(values[3]).setEngineType(values[4]).setHybrid(values[5])
                    .setNumberOfForwardGears(values[6]).setTransmission(values[7]).setCityMpg(values[8])
                    .setFuelType(values[9]).setHighwayMpg(values[10]).setClassification(values[11])
                    .setId(values[12]).setMake(values[13]).setModelYear(values[14]).setYear(values[15])
                    .setHorsePower(values[16]).setTorque(values[17]).build());
            i++;
        }
        System.out.println(linkedList.size());
    }
}
