package com.haemimont.cars.core.model;

import java.io.*;
import java.util.Scanner;

public class FileReader {

    public static String[] Reader (String path,int row) {
        String line ="";
        File file = new File(path);
        String[] output = new String[row];
        int i=0;
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine() && i < row){
                output[i] = scanner.nextLine();
                i++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return output;


    }
}


