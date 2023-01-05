package com.haemimont.cars.core.main;
import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.tools.FromLinesToObjects;
import com.haemimont.cars.core.tools.ImportCarsInToStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class Main {

    private static com.haemimont.cars.core.model.Car Car;

    public static void main(String[] args) {

        String path = "src/main/java/csv/cars.csv";
        Car[] cars = FromLinesToObjects.linesToCars(path, 200);//There is no data on the first line (n-1)
        Storage<String, Car> storageForCars = new Storage<>();
        ImportCarsInToStorage.objectsToStorage(storageForCars, cars);
        String name = "root";
        String password = "niki";
        String url = "jdbc:mysql://localhost:3306/cars";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            for(String key : storageForCars.keySet()){



                String query = "INSERT into car(idcar,car) values('"+key.toString()+"'"+","+
                        "car"+")";
                statement.execute(query);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
