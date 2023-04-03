package com.haemimont.cars.core.sql;

import com.haemimont.cars.core.loger.CustomLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    Connection connection;
    private String lastConnectionUrl;
    private String lastConnectionName;
    private String lastConnectionPassword;

    public void connect(String url, String name, String password) {
        try {
            connection = DriverManager.getConnection(url, name, password);
            CustomLogger.logInfo("Connecting to the DB");
            lastConnectionUrl = url;
            lastConnectionName = name;
            lastConnectionPassword = password;
        } catch (SQLException e) {
            CustomLogger.logError("Failed to Connect to the DB");
        }
    }

    public void connectToLastDb() {
        if (lastConnectionPassword == null || lastConnectionUrl == null || lastConnectionName == null) {
            CustomLogger.logError("No previous connection");
        } else {
            connect(lastConnectionUrl, lastConnectionName, lastConnectionPassword);
        }
    }

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
