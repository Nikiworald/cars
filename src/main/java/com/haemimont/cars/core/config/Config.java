package com.haemimont.cars.core.config;

import com.haemimont.cars.core.loger.CustomLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    static Properties pr = new Properties();
    static InputStream in;
    static InputStream inWeb;
    static String name ;
    static String password ;
    static String dbUrl ;
    static String csvFilePath ;
    static String loggerFilePath ;

    {
        try {
            in = new FileInputStream("src/main/resources/Properties.properties");
        } catch (IOException e) {
            CustomLogger.logError("aa");
            {
                try {
                    in = new FileInputStream("/WEB-INF/classes/Properties.properties");
                } catch (IOException a) {
                    CustomLogger.logError("aa");
                }
            }
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
//    public static String getProperty(String key) throws IOException {
//        pr.load(inWeb);
//       String prop = pr.getProperty(key);
//       return prop;
//    }
}
