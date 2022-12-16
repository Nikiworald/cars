package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.model.*;

public class FromLinesToObjects {
    public static Car[] linesToCars(String path, int numberOfRows) {
        Car[] cars = new Car[numberOfRows];
        Dimension dimension = new Dimension();
        EngineInformation engineInformation= new EngineInformation();
        EngineStatistics engineStatistics = new EngineStatistics();
        FuelInformation fuelInformation = new FuelInformation();
        Identification identification = new Identification();
        engineInformation.setEngineStatistics(engineStatistics);
        String[] rawLines = FileReader.Reader(path,numberOfRows);

        for(int i = 0;i<numberOfRows;i++){
            if (i>0) {
                String[] values = Trimmer.customTrim(rawLines[i]);
                cars[i] =  CarBuilder.newInstance().setDimension(dimension).setIdentification(identification)
                        .setEngineInformation(engineInformation).setFuelInformation(fuelInformation)
                        .setHeight(values[0]).setLength(values[1])
                        .setWidth(values[2]).setDriveLine(values[3]).setEngineType(values[4]).setHybrid(values[5])
                        .setNumberOfForwardGears(values[6]).setTransmission(values[7]).setCityMpg(values[8])
                        .setFuelType(values[9]).setHighwayMpg(values[10]).setClassification(values[11])
                        .setId(values[12]).setMake(values[13]).setModelYear(values[14]).setYear(values[15])
                        .setHorsePower(values[16]).setTorque(values[17]).build();
            }
        }
        return cars;
    }
}