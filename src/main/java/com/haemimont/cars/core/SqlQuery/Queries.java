package com.haemimont.cars.core.SqlQuery;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.model.*;

import java.sql.*;

public class Queries {
    String url, name, password;
    int dimensionId, fuelId,identificationId,engineStatisticsId,row = 0;

    public Queries(String url, String name, String password) {
        this.url = url;
        this.name = name;
        this.password = password;
    }

    public void fillDimension(String key, Storage<String, Car> storageForCars) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into dimension(height,width,length) values(" +
                    storageForCars.get(key).getDimension().getHeight() + "," +
                    storageForCars.get(key).getDimension().getWidth() + "," +
                    storageForCars.get(key).getDimension().getLength() +
                    ")";
            statement.execute(query);
            row++;connection.close();statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //catch (ClassNotFoundException e) {
        // throw new RuntimeException(e);
        //}
    }

    public int getDimensionId() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.dimension;";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;
            while (resultSet.next() && i != 0) {
                dimensionId = resultSet.getInt("id_dimension");
                i--;
            }connection.close();statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //catch (ClassNotFoundException e) {
        //throw new RuntimeException(e);
        //}
        return dimensionId;
    }

    public void fillFuelInformation(String key, Storage<String, Car> storageForCars) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into fuel_inforamtion(city_mpg,fuel_type,highway_mpg) values(" +
                    storageForCars.get(key).getFuelInformation().getCityMpg() + "," +
                    "'" +storageForCars.get(key).getFuelInformation().getFuelType()+ "'" + "," +
                    storageForCars.get(key).getFuelInformation().getHighwayMpg() +
                    ")";
            statement.execute(query);connection.close();statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //catch (ClassNotFoundException e) {
        // throw new RuntimeException(e);
        //}
    }
    public int getFuelId(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.fuel_inforamtion;";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;
            while (resultSet.next() && i != 0) {
                fuelId = resultSet.getInt("id_fuel_inforamtion");
                i--;
            }connection.close();statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //catch (ClassNotFoundException e) {
        //throw new RuntimeException(e);
        //}
        return fuelId;
    }
    public void fillIdentification(String key,Storage<String, Car> storageForCars){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into identification(classification,id,make,model_year,year) values(" +
                   "'"+ storageForCars.get(key).getIdentification().getClassification()+"'" + "," +
                    "'" +storageForCars.get(key).getIdentification().getId()+ "'" + "," +
                    "'"+storageForCars.get(key).getIdentification().getMake()+"'" +","+
                    "'"+storageForCars.get(key).getIdentification().getModelYear()+"'"+","+
                    storageForCars.get(key).getIdentification().getYear()+
                    ")";
            statement.execute(query);connection.close();statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //catch (ClassNotFoundException e) {
        // throw new RuntimeException(e);
        //}
    }
    public int getIdentificationId(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.identification;";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;
            while (resultSet.next() && i != 0) {
                identificationId = resultSet.getInt("id_identification");
                i--;
            }connection.close();statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //catch (ClassNotFoundException e) {
        //throw new RuntimeException(e);
        //}
        return identificationId;

    }
    public void fillEngineStatistics(String key,Storage<String, Car> storageForCars) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into cars.engine_statistics(hoursepower,torque) values(" +
                      storageForCars.get(key).getEngineInformation().getEngineStatistics().getHorsePower()  + "," +
                    storageForCars.get(key).getEngineInformation().getEngineStatistics().getTorque()+
                    ")";
            statement.execute(query);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //catch (ClassNotFoundException e) {
        // throw new RuntimeException(e);
        //}
    }
    public int getEngineStatisticsId(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.engine_statistics;";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;
            while (resultSet.next() && i != 0) {
                engineStatisticsId = resultSet.getInt("id_engine_statistics");
                i--;
            }connection.close();statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //catch (ClassNotFoundException e) {
        //throw new RuntimeException(e);
        //}
        return engineStatisticsId;
    }

}



