package com.haemimont.cars.core.config;

public class Config {
    final static String name = "root";
    final static String password = "niki";
    final static String url = "jdbc:mysql://localhost:3306/cars";
    final static String path = "src/main/java/csv/cars.csv";

    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl() {
        return url;
    }

    public static String getPath() {
        return path;
    }
}
