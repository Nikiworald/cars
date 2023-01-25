package com.haemimont.cars.core.config;

public class Config {
    final static String name = "root";
    final static String password = "niki";
    final static String url = "jdbc:mysql://localhost:3306/cars";
    final static String csvFilePath = "src/main/java/csv/cars.csv";
    final static String loggerFilePath = "C:\\Users\\user\\Desktop\\cars\\logger.log";

    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl() {
        return url;
    }

    public static String getCsvFilePath() {
        return csvFilePath;
    }
    public static String getLoggerFilePath(){return loggerFilePath;}
}
