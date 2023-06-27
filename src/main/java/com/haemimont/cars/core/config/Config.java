package com.haemimont.cars.core.config;

import com.haemimont.cars.core.logger.CustomLogger;

public class Config {
    static private final String NAME;
    static private final String PASSWORD;
    static private final String DB_URL;
    static private final String CSV_FILE_PATH;
    static private final String LOGGER_FILE_PATH;
    private static final java.util.Properties prop = new java.util.Properties();

    static {
        Config.loadProperties();
        NAME = prop.getProperty("name");
        PASSWORD = prop.getProperty("password");
        DB_URL = prop.getProperty("dbUrl");
        CSV_FILE_PATH = prop.getProperty("csvFilePath");
        LOGGER_FILE_PATH = prop.getProperty("loggerFilePath");
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
        } catch (Exception e) {
            System.err.println("Could not load configuration file: " + propFile);
        }
    }

    public static String getUserName() {
        return NAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getCsvFilePath() {
        return CSV_FILE_PATH;
    }

    public static String getLoggerFilePath() {
        return LOGGER_FILE_PATH;
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
