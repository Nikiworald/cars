package com.haemimont.cars.core.config;

public class Config {
    final String name = "root";
    final String password = "niki";
    final String url = "jdbc:mysql://localhost:3306/cars";
    final String path = "src/main/java/csv/cars.csv";

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }
}
