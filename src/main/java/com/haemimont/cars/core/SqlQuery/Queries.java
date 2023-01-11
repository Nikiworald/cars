package com.haemimont.cars.core.SqlQuery;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.tools.Generator;

import java.sql.*;

public class Queries {
    String url, name, password;
    int dimensionId, fuelId, identificationId, engineStatisticsId, engineInformationId, row = 0;

    public Queries(String url, String name, String password) {
        this.url = url;
        this.name = name;
        this.password = password;
    }

    public void fillDimension(String key, Storage<String, Car> storageForCars) {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into dimension(height,width,length) values(" +
                    storageForCars.get(key).getDimension().getHeight() + "," +
                    storageForCars.get(key).getDimension().getWidth() + "," +
                    storageForCars.get(key).getDimension().getLength() +
                    ")";
            statement.execute(query);
            row++;
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getDimensionId() {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.dimension;";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;
            while (resultSet.next() && i != 0) {
                dimensionId = resultSet.getInt("id_dimension");
                i--;
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dimensionId;
    }

    public void fillFuelInformation(String key, Storage<String, Car> storageForCars) {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into fuel_information(city_mpg,fuel_type,highway_mpg) values(" +
                    storageForCars.get(key).getFuelInformation().getCityMpg() + "," +
                    "'" + storageForCars.get(key).getFuelInformation().getFuelType() + "'" + "," +
                    storageForCars.get(key).getFuelInformation().getHighwayMpg() +
                    ")";
            statement.execute(query);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getFuelId() {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.fuel_information;";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;
            while (resultSet.next() && i != 0) {
                fuelId = resultSet.getInt("id_fuel_information");
                i--;
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fuelId;
    }

    public void fillIdentification(String key, Storage<String, Car> storageForCars) {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into identification(classification,id,make,model_year,year) values(" +
                    "'" + storageForCars.get(key).getIdentification().getClassification() + "'" + "," +
                    "'" + storageForCars.get(key).getIdentification().getId() + "'" + "," +
                    "'" + storageForCars.get(key).getIdentification().getMake() + "'" + "," +
                    "'" + storageForCars.get(key).getIdentification().getModelYear() + "'" + "," +
                    storageForCars.get(key).getIdentification().getYear() +
                    ")";
            statement.execute(query);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIdentificationId() {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.identification;";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;
            while (resultSet.next() && i != 0) {
                identificationId = resultSet.getInt("id_identification");
                i--;
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return identificationId;

    }

    public void fillEngineStatistics(String key, Storage<String, Car> storageForCars) {
        try {
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into cars.engine_statistics(hoursepower,torque) values(" +
                    storageForCars.get(key).getEngineInformation().getEngineStatistics().getHorsePower() + "," +
                    storageForCars.get(key).getEngineInformation().getEngineStatistics().getTorque() +
                    ")";
            statement.execute(query);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getEngineStatisticsId() {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.engine_statistics;";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;
            while (resultSet.next() && i != 0) {
                engineStatisticsId = resultSet.getInt("id_engine_statistics");
                i--;
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return engineStatisticsId;
    }

    public void fillEngineInformation(String key, Storage<String, Car> storageForCars, int engineStatisticsId) {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into cars.engine_information(driveline,engine_type,hybrid," +
                    "number_of_forward_gears,transmission,id_engine_statistics) values(" +
                    "'" + storageForCars.get(key).getEngineInformation().getDriveLine() + "'" + "," +
                    "'" + storageForCars.get(key).getEngineInformation().getEngineType() + "'" + "," +
                    "'" + storageForCars.get(key).getEngineInformation().isHybrid() + "'" + "," +
                    storageForCars.get(key).getEngineInformation().getNumberOfForwardGears() + "," +
                    "'" + storageForCars.get(key).getEngineInformation().getTransmission() + "'" + "," +
                    engineStatisticsId +
                    ")";
            statement.execute(query);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getEngineInformationId() {
        try {
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.engine_information;";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;
            while (resultSet.next() && i != 0) {
                engineInformationId = resultSet.getInt("id_engine_information");
                i--;
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return engineInformationId;
    }

    public void fillCar(String key, Storage<String, Car> storageForCars, int dimensionId, int engineInformationId,
                        int fuelId, int identificationId) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "INSERT into cars.car(id_car,car,id_dimension,id_engine_information," +
                    "id_fuel_information,id_identification) values(" +
                    row + "," +
                    "'" + Generator.vinGenerator(storageForCars.get(key),storageForCars) + "'" + "," +
                    dimensionId + "," + engineInformationId + "," + fuelId + "," + identificationId +
                    ")";
            statement.execute(query);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void fromRowToCar(int row){
        try {
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.car";
            ResultSet resultSet = statement.executeQuery(query);
            int i = row;int tempDimension,tempEngineInfo,tempEngineStat,tempFuelInfo,tempIdentification;
            while (resultSet.next() && i != 0) {
               tempDimension= resultSet.getInt("id_dimension");
               tempEngineInfo= resultSet.getInt("id_engine_information");
               tempFuelInfo= resultSet.getInt("id_fuel_information");
               tempIdentification =  resultSet.getInt("id_identification");
            }
            Car car = CarBuilder.newInstance().setDimension().setIdentification(identification)
                    .setEngineInformation(engineInformation).setFuelInformation(fuelInformation)
                    .setHeight(values[0]).setLength(values[1])
                    .setWidth(values[2]).setDriveLine(values[3]).setEngineType(values[4]).setHybrid(values[5])
                    .setNumberOfForwardGears(values[6]).setTransmission(values[7]).setCityMpg(values[8])
                    .setFuelType(values[9]).setHighwayMpg(values[10]).setClassification(values[11])
                    .setId(values[12]).setMake(values[13]).setModelYear(values[14]).setYear(values[15])
                    .setHorsePower(values[16]).setTorque(values[17]).build();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}



