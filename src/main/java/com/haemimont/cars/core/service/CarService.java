package com.haemimont.cars.core.service;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.sql.ConnectionManager;

import java.sql.Connection;
import java.util.ArrayList;

public class CarService {
    CarStatements statements = new CarStatements();
    ConnectionManager connectionManager = new ConnectionManager();
    public Connection connectToDbAndGetConnection(){
        connectionManager.connect(Config.getDbUrl(),Config.getUserName(),Config.getPassword());
        return connectionManager.getConnection();
    }

    public Car getCar(String para1, String para2, Connection connection) {
        return statements.getCarFromDb(para1,para2,connection);
    }
}
