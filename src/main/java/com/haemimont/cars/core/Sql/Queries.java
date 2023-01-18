package com.haemimont.cars.core.Sql;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.tools.Generator;
import java.sql.*;
import java.util.ArrayList;

public class Queries {//preset of queries
    String url, name, password;
    int dimensionId, fuelId, identificationId, engineStatisticsId, engineInformationId, rowOfTheCar = 1;

    public Queries(String url, String name, String password) {
        this.url = url;
        this.name = name;
        this.password = password;
    }//passing in the url,username and password when making an object of class Queries

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
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//trying to connect to the sql db and inserting the information from an object to the dimension table

    public int getLatestDimensionId() {
        try {
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT Max(id_dimension)\n" +
                    "  FROM dimension\n" +
                    "  ORDER BY dimension.id_dimension DESC";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                dimensionId = resultSet.getInt("Max(id_dimension)");
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

    public int getLatestFuelId() {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT Max(id_fuel_information)\n" +
                    "  FROM fuel_information\n" +
                    "  ORDER BY id_fuel_information DESC";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                fuelId = resultSet.getInt("Max(id_fuel_information)");
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
            String query = "INSERT into identification(classification,id,make,model_year,year,color,price) values(" +
                    "'" + storageForCars.get(key).getIdentification().getClassification() + "'" + "," +
                    "'" + storageForCars.get(key).getIdentification().getId() + "'" + "," +
                    "'" + storageForCars.get(key).getIdentification().getMake() + "'" + "," +
                    "'" + storageForCars.get(key).getIdentification().getModelYear() + "'" + "," +
                    storageForCars.get(key).getIdentification().getYear() +","+
                    "'"+storageForCars.get(key).getIdentification().getColor()+"'"+","+
                    storageForCars.get(key).getIdentification().getPrice()+
                    ")";
            statement.execute(query);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getLatestIdentificationId() {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT Max(id_identification)\n" +
                    "  FROM identification\n" +
                    "  ORDER BY id_identification DESC";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                identificationId = resultSet.getInt("Max(id_identification)");
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

    public int getLatestEngineStatisticsId() {
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT Max(id_engine_statistics)\n" +
                    "  FROM engine_statistics\n" +
                    "  ORDER BY id_engine_statistics DESC";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                engineStatisticsId = resultSet.getInt("Max(id_engine_statistics)");
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

    public int getLatestEngineInformationId() {
        try {
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT Max(id_engine_information)\n" +
                    "  FROM engine_information\n" +
                    "  ORDER BY id_engine_information DESC";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                engineInformationId = resultSet.getInt("Max(id_engine_information)");
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
            String query = "INSERT into cars.car(id_car,vin,id_dimension,id_engine_information," +
                    "id_fuel_information,id_identification) values(" +
                    rowOfTheCar + "," +
                    "'" + Generator.vinGenerator(storageForCars.get(key), storageForCars) + "'" + "," +
                    dimensionId + "," + engineInformationId + "," + fuelId + "," + identificationId +
                    ")";
            statement.execute(query);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        rowOfTheCar++;
    }

    public ArrayList<Car> fromCarMakeToCarObj(String make) {
        ArrayList<Car> cars = new ArrayList<>();
        try {

            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length,engine_information.driveline,engine_information.engine_type,\n" +
                    "engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission,\n" +
                    "engine_statistics.hoursepower,engine_statistics.torque,fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg,\n" +
                    "identification.classification,identification.id,identification.make,identification.model_year,identification.year FROM cars.car\n" +
                    " join identification on identification.id_identification = car.id_identification \n" +
                    " join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information\n" +
                    " join dimension on dimension.id_dimension = car.id_dimension\n" +
                    " join engine_information on engine_information.id_engine_information = car.id_engine_information\n" +
                    " join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics" +
                    " where make = '"+make+"'";

            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int height = resultSet.getInt("height");
                int width = resultSet.getInt("width");
                int length = resultSet.getInt("length");
                int number_of_forward_gears = resultSet.getInt("number_of_forward_gears");
                int hoursepower = resultSet.getInt("hoursepower");
                int torque = resultSet.getInt("torque");
                int city_mpg = resultSet.getInt("city_mpg");
                int highway_mpg = resultSet.getInt("highway_mpg");
                int year =  resultSet.getInt("year");
                double price = resultSet.getDouble("price");
                String driveline= resultSet.getString("driveline");
                String engine_type = resultSet.getString("engine_type");
                String hybrid = resultSet.getString("hybrid");
                String transmission = resultSet.getString("transmission");
                String fuel_type = resultSet.getString("fuel_type");
                String classification = resultSet.getString("classification");
                String id = resultSet.getString("id");
                //String dbMake = resultSet.getString("make");
                String model_year = resultSet.getString("model_year");
                String color = resultSet.getString("color");
                EngineInformation engineInformation = new EngineInformation();
                engineInformation.setEngineStatistics(new EngineStatistics());
                 cars.add(CarBuilder.newInstance().setDimension(new Dimension()).setIdentification(new Identification())
                         .setEngineInformation(engineInformation).setFuelInformation(new FuelInformation())
                         .setHeight(height).setLength(length)
                         .setWidth(width).setDriveLine(driveline).setEngineType(engine_type).setHybrid(hybrid)
                         .setNumberOfForwardGears(number_of_forward_gears).setTransmission(transmission).setCityMpg(city_mpg)
                         .setFuelType(fuel_type).setHighwayMpg(highway_mpg).setClassification(classification)
                         .setId(id).setMake(make).setModelYear(model_year).setYear(year)
                         .setHorsePower(hoursepower).setTorque(torque).setColor(color).setPrice(price).build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

}



