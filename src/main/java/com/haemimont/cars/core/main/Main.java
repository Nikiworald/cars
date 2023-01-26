package com.haemimont.cars.core.main;
import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.sql.ConnectionManager;
import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.tools.FromLinesToObjects;
import com.haemimont.cars.core.tools.InitDB;
import com.haemimont.cars.core.tools.StorageTools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //CustomLogger customLogger = new CustomLogger();
        Config config = new Config();
        InitDB.initializeDb();


    }
}
