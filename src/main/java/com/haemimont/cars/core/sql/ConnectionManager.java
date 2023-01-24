package com.haemimont.cars.core.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    Connection connection;
    public void connect(String url, String name, String password) {
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
