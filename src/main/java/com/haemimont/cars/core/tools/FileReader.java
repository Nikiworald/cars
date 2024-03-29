package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.logger.CustomLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//basic file reader
public class FileReader {
    //reads from a file and returns all the lines
    public static String[] Reader(String path, int row) {
        File file = new File(path);
        String[] outputOfLines = new String[row];
        int i = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine() && i < row) {
                outputOfLines[i] = scanner.nextLine();
                i++;
            }

        } catch (FileNotFoundException e) {
            CustomLogger.logError("File not found:" + path);
        }
        return outputOfLines;
    }
}


