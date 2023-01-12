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
                    "'" + Generator.vinGenerator(storageForCars.get(key), storageForCars) + "'" + "," +
                    dimensionId + "," + engineInformationId + "," + fuelId + "," + identificationId +
                    ")";
            statement.execute(query);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Car fromRowToCar(int row) {
        Car car;
        try {
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cars.car";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSet resultSetForD;
            ResultSet resultSetForEi;
            ResultSet resultSetForFi;
            ResultSet resultSetForI;
            ResultSet resultSetForEs;
            int i = row; /*tempDimension = 0, tempEngineInfo = 0, tempEngineStat = 0, tempFuelInfo = 0,
                    tempIdentification = 0, height=0, width=0, length=0, number_of_forward_gears=0,
                    id_engine_statistics = 0, hoursepower=0, torque=0, city_mpg=0, highway_mpg=0, year=0;*/
            String driveline="", engine_type="", hybrid="", transmission="", fuel_type="" ,
                    classification="", id="", make="", model_year="",tempDimension = "", tempEngineInfo = "", tempEngineStat = "", tempFuelInfo = "",
                    tempIdentification = "", height="", width="", length="", number_of_forward_gears="",
                    id_engine_statistics = "", hoursepower="", torque="", city_mpg="", highway_mpg="", year="";

            while (resultSet.next() && i != 0) {
                tempDimension = resultSet.getString("id_dimension");
                tempEngineInfo = resultSet.getString("id_engine_information");
                tempFuelInfo = resultSet.getString("id_fuel_information");
                tempIdentification = resultSet.getString("id_identification");
                i--;
            }
            query = "SELECT * FROM dimension WHERE id_dimension=" + tempDimension;
            resultSetForD = statement.executeQuery(query);
            while (resultSetForD.next()) {
                height = resultSetForD.getString("width");
                width = resultSetForD.getString("width");
                length = resultSetForD.getString("length");
            }
            query = "SELECT * FROM engine_information WHERE id_engine_information=" + tempEngineInfo;
            resultSetForEi = statement.executeQuery(query);
            while (resultSetForEi.next()) {
                driveline = resultSetForEi.getString("driveline");
                engine_type = resultSetForEi.getString("engine_type");
                hybrid = resultSetForEi.getString("hybrid");
                number_of_forward_gears = resultSetForEi.getString("number_of_forward_gears");
                transmission = resultSetForEi.getString("transmission");
                id_engine_statistics = resultSetForEi.getString("id_engine_statistics");
            }
            query = "SELECT * FROM engine_statistics WHERE id_engine_statistics=" + id_engine_statistics;
            resultSetForEs = statement.executeQuery(query);
            while (resultSetForEs.next()) {
                hoursepower = resultSetForEs.getString("hoursepower");
                torque = resultSetForEs.getString("torque");
            }
            query = "SELECT * FROM fuel_information WHERE id_fuel_information=" + tempFuelInfo;
            resultSetForFi = statement.executeQuery(query);
            while (resultSetForFi.next()) {
                city_mpg = resultSetForFi.getString("city_mpg");
                fuel_type = resultSetForFi.getString("fuel_type");
                highway_mpg = resultSetForFi.getString("highway_mpg");
            }
            query = "SELECT * FROM identification WHERE id_identification=" + tempIdentification;
            resultSetForI = statement.executeQuery(query);
            while (resultSetForI.next()) {
                classification = resultSetForI.getString("classification");
                id = resultSetForI.getString("id");
                make = resultSetForI.getString("make");
                model_year = resultSetForI.getString("model_year");
                year = resultSetForI.getString("year");
            }
             car = CarBuilder.newInstance().setDimension(new Dimension()).setIdentification(new Identification())
                    .setEngineInformation(new EngineInformation()).setFuelInformation(new FuelInformation())
                    .setHeight(height).setLength(length)
                    .setWidth(width).setDriveLine(driveline).setEngineType(engine_type).setHybrid(hybrid)
                    .setNumberOfForwardGears(number_of_forward_gears).setTransmission(transmission).setCityMpg(city_mpg)
                    .setFuelType(fuel_type).setHighwayMpg(highway_mpg).setClassification(classification)
                    .setId(id).setMake(make).setModelYear(model_year).setYear(year)
                    .setHorsePower(hoursepower).setTorque(torque).build();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

}



