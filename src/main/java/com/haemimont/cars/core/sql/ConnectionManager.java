package com.haemimont.cars.core.sql;

import com.haemimont.cars.core.loger.CustomLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    Connection connection;
    public void connect(String url, String name, String password) {
        try {
            connection = DriverManager.getConnection(url, name, password);
            CustomLogger.LogInfo("Connecting to the DB");
        } catch (SQLException e) {
            CustomLogger.LogError("Failed to Connect to the DB");
        }
    }
    public void disconnect() {
        try {
            this.connection.close();
            CustomLogger.LogInfo("Disconnecting from the DB");
        } catch (SQLException e) {
            CustomLogger.LogError("Failed to Disconnect from the DB");
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
