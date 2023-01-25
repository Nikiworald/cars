package com.haemimont.cars.core.loger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLogger {
    private static final Logger logger = Logger.getLogger(CustomLogger.class
            .getName());
    private FileHandler fh;

    {
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
        try {
            fh = new FileHandler("C:\\Users\\user\\Desktop\\cars\\logger.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
    }
    public static void LogInfo(String msg){
        logger.info(msg);
    }
    public static void LogError(String msg){
        logger.severe(msg);
    }



}
