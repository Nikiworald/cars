package com.haemimont.cars.core.main;
import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.tools.InitDB;


public class Main {
    public static void main(String[] args) {
        //CustomLogger customLogger = new CustomLogger();
        Config config = new Config();
        InitDB.initializeDb();



    }
}
