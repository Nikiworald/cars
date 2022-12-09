package com.haemimont.cars.core.model;

import java.io.*;
import java.util.Scanner;

public class FileReader {

    public static String Reader(String path,int row) {
        String line ="";
        int i = 0;
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                if (i > 0) {
                    line = scanner.nextLine();
                    return line;
                }
                else{scanner.nextLine();}
                i++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return line;


    }
}


