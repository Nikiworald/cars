package com.haemimont.cars.core.loger;

import com.haemimont.cars.core.config.Config;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SuppressWarnings("SpellCheckingInspection")
public class CustomLogger {
    public static final Logger logger = Logger.getLogger(CustomLogger.class
            .getName());

    static {
        FileHandler fh;
        try {
            fh = new FileHandler(Config.getLoggerFilePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
    }
    public static void logInfo(String msg){
        logger.info(msg);
    }
    public static void logError(String msg){
        logger.severe(msg);
    }




}
