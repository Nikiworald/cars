package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.logger.CustomLogger;
import com.haemimont.cars.core.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class CsvHandler {
    //reads from a file and returns all the lines
    public static Car[] linesToCars(String path, int numberOfRows) {
        Car[] cars = new Car[numberOfRows];
        String[] rawLines = readAndReturnLines(path, numberOfRows);
        for (int i = 0; i < numberOfRows; i++) {
            if (i > 0) {
                String[] values = rawLines[i].split(",");
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
                        .setHeight(values[0].trim()).setLength(values[1].trim())
                        .setWidth(values[2].trim()).setDriveLine(values[3].trim()).setEngineType(values[4].trim()).setHybrid(values[5].trim())
                        .setNumberOfForwardGears(values[6].trim()).setTransmission(values[7].trim()).setCityMpg(values[8].trim())
                        .setFuelType(values[9].trim()).setHighwayMpg(values[10].trim()).setClassification(values[11].trim())
                        .setId(values[12].trim()).setMake(values[13].trim()).setModelYear(values[14].trim()).setYear(values[15].trim())
                        .setHorsePower(values[16].trim()).setTorque(values[17].trim()).setColor(colors[random.nextInt(4)].trim())
                        .setPrice(((double) identification.getYear() / 3) * 10 + random.nextInt(500))
                        .build();
            }
        }
        return cars;
    }

    public static String[] readAndReturnLines(String path, int row) {
        File file = new File(path);
        String[] outputOfLines = new String[row];
        int i = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine() && i < row) {
                outputOfLines[i] = (scanner.nextLine().replace('"', ' '));
                i++;
            }

        } catch (FileNotFoundException e) {
            CustomLogger.logError("File not found:" + path);
        }
        return outputOfLines;
    }


}
