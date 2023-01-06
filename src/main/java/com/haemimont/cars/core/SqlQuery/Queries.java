package com.haemimont.cars.core.SqlQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
    String url, name, password;

    Queries(String url, String name, String password) {
        this.url = url;
        this.name = name;
        this.password = password;
    }
    /*{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/
    private void FillDimension(String key) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
