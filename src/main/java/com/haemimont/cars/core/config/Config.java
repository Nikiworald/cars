package com.haemimont.cars.core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    static Properties pr = new Properties();
    InputStream in;
    static String name ;
    static String password ;
    static String dbUrl ;
    static String csvFilePath ;
    static String loggerFilePath ;

    {
        try {
            in = new FileInputStream("src/main/resources/Properties.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Config()  {//get the info from the properties file
        try {
            pr.load(in);
            name = pr.getProperty("name");
            password = pr.getProperty("password");
            dbUrl = pr.getProperty("dbUrl");
            csvFilePath = pr.getProperty("csvFilePath");
            loggerFilePath = pr.getProperty("loggerFilePath");
        } catch (IOException e) {
            throw new RuntimeException(e);//logger in not working at this point
        }
    }


    public static String getUserName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getCsvFilePath() {
        return csvFilePath;
    }
    public static String getLoggerFilePath(){return loggerFilePath;}
}
