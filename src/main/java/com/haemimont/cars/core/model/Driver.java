package com.haemimont.cars.core.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

    public static String Reader(String path,int row) {

        String line="";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            for(int i=0;i<row;i++)
            {
                line = bufferedReader.readLine();
                if(line == null){break;}
                return line;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "nothing to read";
    }
}


