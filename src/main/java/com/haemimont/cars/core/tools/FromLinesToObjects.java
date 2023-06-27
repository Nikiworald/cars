package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.model.*;

import java.util.Random;

//converts lines from CSV to objects
@Deprecated//moved to DbUtil
public class FromLinesToObjects {
    //reads lines one by one trims each and makes an object out of it
    //and then returns an array of all the objects
    public static Car[] linesToCars(String path, int numberOfRows) {
        Car[] cars = new Car[numberOfRows];
        String[] rawLines = FileReader.Reader(path, numberOfRows);
        for (int i = 0; i < numberOfRows; i++) {
            if (i > 0) {
                String[] values = Trimmer.customTrim(rawLines[i]);
                Dimension dimension = new Dimension();
                EngineInformation engineInformation = new EngineInformation();
                EngineStatistics engineStatistics = new EngineStatistics();
                FuelInformation fuelInformation = new FuelInformation();
                Identification identification = new Identification();
                Random random = new Random();
                String[] colors = {"Blue", "Red", "Gray", "White", "Black"};
                engineInformation.setEngineStatistics(engineStatistics);
                cars[i] = CarBuilder.newInstance().setDimension(dimension).setIdentification(identification)
                        .setEngineInformation(engineInformation).setFuelInformation(fuelInformation)
                        .setHeight(values[0]).setLength(values[1])
                        .setWidth(values[2]).setDriveLine(values[3]).setEngineType(values[4]).setHybrid(values[5])
                        .setNumberOfForwardGears(values[6]).setTransmission(values[7]).setCityMpg(values[8])
                        .setFuelType(values[9]).setHighwayMpg(values[10]).setClassification(values[11])
                        .setId(values[12]).setMake(values[13]).setModelYear(values[14]).setYear(values[15])
                        .setHorsePower(values[16]).setTorque(values[17]).setColor(colors[random.nextInt(4)])
                        .setPrice(((double) identification.getYear() / 3) * 10 + random.nextInt(500))
                        .build();
            }
        }
        return cars;
    }
}
