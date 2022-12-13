package com.haemimont.cars.core.model;

import java.io.*;
import java.util.Scanner;

public class FileReader {

    public static String[] Reader (String path,int row) {
        String line ="";
        File file = new File(path);
        String[] outputOfLines = new String[row];
        int i=0;
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine() && i < row){
                outputOfLines[i] = scanner.nextLine();
                i++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return outputOfLines;


    }
}


