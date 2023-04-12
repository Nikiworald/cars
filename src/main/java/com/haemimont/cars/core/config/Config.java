package com.haemimont.cars.core.config;

import com.haemimont.cars.core.loger.CustomLogger;

public class Config {//fixme
    static String name;
    static String password;
    static String dbUrl;
    static String csvFilePath;
    static String loggerFilePath;
    private static final java.util.Properties prop = new java.util.Properties();

    static {
        Config.loadProperties();
    }

    public Config() {//get the info from the properties file


        name = prop.getProperty("name");
        password = prop.getProperty("password");
        dbUrl = prop.getProperty("dbUrl");
        csvFilePath = prop.getProperty("csvFilePath");
        loggerFilePath = prop.getProperty("loggerFilePath");
        loggerFilePath = prop.getProperty("loggerFilePath");

    }

    private static void loadProperties() {
        // get class loader
        ClassLoader loader = Config.class.getClassLoader();
        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();

        // assuming you want to load application.properties located in WEB-INF/classes/conf/
        String propFile = "Properties.properties";
        java.net.URL url = loader.getResource(propFile);
        try {
            prop.load(url.openStream());
        } catch (Exception e) {
            System.err.println("Could not load configuration file: " + propFile);
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

    public static String getLoggerFilePath() {
        return loggerFilePath;
    }

    public static String getPropertyByName(String name) {
        String input = null;
        try {
            input = prop.getProperty(name);
        } catch (Exception e) {
            CustomLogger.logError("no property found/" + e);
        }
        return input;

    }
}
