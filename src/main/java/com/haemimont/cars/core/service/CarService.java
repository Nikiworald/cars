package com.haemimont.cars.core.service;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.sql.ConnectionManager;
import com.haemimont.cars.core.tools.DbUtil;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarService {
    Connection connection ;
    CarStatements carStatements;

    public CarService() {
//        ConnectionManager connectionManager = new ConnectionManager();
//        connectionManager.connect("jdbc:mysql://localhost:3306/cars","root","niki");//fuck you
        this.connection = DbUtil.getConnection();
        int a =0;
        CarStatements carStatements = new CarStatements();
        this.carStatements = carStatements;
    }


    public Car getCar(String filter, String param) {
        return carStatements.getCarFromDb(filter,param,this.connection);
    }
    public ArrayList<Car> getCars(String fitler, String param){
        return carStatements.getCarsFromDb(fitler,param,this.connection);
    }
    public ArrayList<Car> getAllCars(){
        return carStatements.getCarsFromDb(this.connection);
    }
}
