package com.haemimont.cars.core.sql;

import com.haemimont.cars.core.logger.CustomLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    Connection connection;

    public void connect(String url, String name, String password) {
        try {
            connection = DriverManager.getConnection(url, name, password);
            CustomLogger.logInfo("Connecting to the DB");
        } catch (SQLException e) {
            CustomLogger.logError("Failed to Connect to the DB");
        }
    }

// --Commented out by Inspection START (6/22/2023 2:58 PM):
//    public void connectToLastDb() {
//        if (lastConnectionPassword == null || lastConnectionUrl == null || lastConnectionName == null) {
//            CustomLogger.logError("No previous connection");
//        } else {
//            connect(lastConnectionUrl, lastConnectionName, lastConnectionPassword);
//        }
//    }
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)

    public void disconnect() {
        try {
            this.connection.close();
            CustomLogger.logInfo("Disconnecting from the DB");
        } catch (SQLException e) {
            CustomLogger.logError("Failed to Disconnect from the DB");
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
