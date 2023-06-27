package com.haemimont.cars.core.config;

import com.haemimont.cars.core.logger.CustomLogger;

public class Config {
    static private String name;
    static private String password;
    static private  String dbUrl;
    static private String csvFilePath;
    static private String loggerFilePath;
    private static boolean loaded = false;
    private static final java.util.Properties prop = new java.util.Properties();

    static {
        Config.loadProperties();
        name = prop.getProperty("name");
        password = prop.getProperty("password");
        dbUrl = prop.getProperty("dbUrl");
        csvFilePath = prop.getProperty("csvFilePath");
        loggerFilePath = prop.getProperty("loggerFilePath");
    }

    private Config() {
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
            assert url != null;
            prop.load(url.openStream());
            loaded = true;
        } catch (Exception e) {
            System.err.println("Could not load configuration file: " + propFile);
        }
        System.out.println(loaded);
    }
    public static boolean isLoaded(){return loaded;}

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
