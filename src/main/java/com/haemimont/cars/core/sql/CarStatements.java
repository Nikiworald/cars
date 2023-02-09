package com.haemimont.cars.core.sql;

import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.model.*;

import java.sql.*;
import java.util.ArrayList;

public class CarStatements {//preset of queries

    //trying to connect to the sql db and inserting the information from an object to the dimension table
    //and gets the corresponding id_dimension
    public int insertCarInDimension(Car car, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into dimension(height,width,length) values(?,?,?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, car.getDimension().getHeight());
            preparedStatement.setInt(2, car.getDimension().getWidth());
            preparedStatement.setInt(3, car.getDimension().getLength());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert car:"+car.getIdentification().getVin()+"into dimension");
        }
        return 0;
    }

    public int insertCarInFuelInformation(Car car, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into fuel_information(city_mpg,fuel_type,highway_mpg)" +
                    " values(?,?,?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, car.getFuelInformation().getCityMpg());
            preparedStatement.setString(2, car.getFuelInformation().getFuelType());
            preparedStatement.setInt(3, car.getFuelInformation().getHighwayMpg());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert car:"+car.getIdentification().getVin()+"into fuel_information");
        }
        return 0;
    }

    public int insertCarInIdentification(Car car, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into identification(classification,id," +
                    "make,model_year,year,color,price) values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, car.getIdentification().getClassification());
            preparedStatement.setString(2, car.getIdentification().getId());
            preparedStatement.setString(3, car.getIdentification().getMake());
            preparedStatement.setString(4, car.getIdentification().getModelYear());
            preparedStatement.setInt(5, car.getIdentification().getYear());
            preparedStatement.setString(6, car.getIdentification().getColor());
            preparedStatement.setDouble(7, car.getIdentification().getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                 return resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert car:"+car.getIdentification().getVin()+"into identification");
        }
        return 0;
    }
    public int insertCarInEngineStatistics(Car car, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into cars.engine_statistics(hoursepower,torque) values(?,?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, car.getEngineInformation().getEngineStatistics().getHorsePower());
            preparedStatement.setInt(2, car.getEngineInformation().getEngineStatistics().getTorque());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert car:"+car.getIdentification().getVin()+"into engine_statistics");
        }
        return 0;
    }

    public int insertCarInEngineStatistics(Car car, int engineStatisticsId, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into cars.engine_information" +
                    "(driveline,engine_type,hybrid,number_of_forward_gears,transmission,id_engine_statistics) " +
                    "values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, car.getEngineInformation().getDriveLine());
            preparedStatement.setString(2, car.getEngineInformation().getEngineType());
            preparedStatement.setBoolean(3, car.getEngineInformation().isHybrid());
            preparedStatement.setInt(4, car.getEngineInformation().getNumberOfForwardGears());
            preparedStatement.setString(5, car.getEngineInformation().getTransmission());
            preparedStatement.setInt(6, engineStatisticsId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert car:"+car.getIdentification().getVin()+"into engine_information");
        }
        return 0;
    }

    public void insertCarInCar(Car car, int dimensionId, int engineInformationId,
                               int fuelId, int identificationId, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT into cars.car" +
                    "(vin,id_dimension,id_engine_information," +
                    "id_fuel_information,id_identification) values(?,?,?,?,?)");
            //preparedStatement.setInt(1, numberOfCars);
            preparedStatement.setString(1, car.getIdentification().getVin());
            preparedStatement.setInt(2, dimensionId);
            preparedStatement.setInt(3, engineInformationId);
            preparedStatement.setInt(4, fuelId);
            preparedStatement.setInt(5, identificationId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert car:"+car.getIdentification().getVin()+"into car");
        }
    }

    public Boolean checkForMatchingVin(String vin, Connection connection) {
        Boolean check = false;
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT * FROM cars.car\n" +
                    "where vin = ?\"\"";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
        } catch (SQLException e) {
            CustomLogger.logError("Could not check for vin:"+vin);
        }
        return check;
    }

    //get data with a matching identification.make and makes an object
    public ArrayList<Car> getCarsFromDb(String para1, String para2, Connection connection) {
        ArrayList<Car> cars = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length,engine_information.driveline,engine_information.engine_type,\n" +
                    "engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission,\n" +
                    "engine_statistics.hoursepower,engine_statistics.torque,fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg,\n" +
                    "identification.classification,identification.id,identification.make,identification.model_year,identification.year,identification.color,identification.price FROM cars.car\n" +
                    " join identification on identification.id_identification = car.id_identification \n" +
                    " join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information\n" +
                    " join dimension on dimension.id_dimension = car.id_dimension\n" +
                    " join engine_information on engine_information.id_engine_information = car.id_engine_information\n" +
                    " join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics" +
                    " where " + para1 + "=" + "'"+ para2 + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int height = resultSet.getInt("height");
                int width = resultSet.getInt("width");
                int length = resultSet.getInt("length");
                int number_of_forward_gears = resultSet.getInt("number_of_forward_gears");
                int hoursepower = resultSet.getInt("hoursepower");
                int torque = resultSet.getInt("torque");
                int city_mpg = resultSet.getInt("city_mpg");
                int highway_mpg = resultSet.getInt("highway_mpg");
                int year = resultSet.getInt("year");
                double price = resultSet.getDouble("price");
                String driveline = resultSet.getString("driveline");
                String engine_type = resultSet.getString("engine_type");
                String hybrid = resultSet.getString("hybrid");
                String transmission = resultSet.getString("transmission");
                String fuel_type = resultSet.getString("fuel_type");
                String classification = resultSet.getString("classification");
                String id = resultSet.getString("id");
                String make = resultSet.getString("make");
                String model_year = resultSet.getString("model_year");
                String color = resultSet.getString("color");
                String vin = resultSet.getString("vin");
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
                cars.get(cars.size()-1).getIdentification().setVin(vin);
            }
            statement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not get car/s from the DB");
        }
        return cars;
    }
    public Car getCarFromDb(String para1, String para2, Connection connection) {
        Car car = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length,engine_information.driveline,engine_information.engine_type,\n" +
                    "engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission,\n" +
                    "engine_statistics.hoursepower,engine_statistics.torque,fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg,\n" +
                    "identification.classification,identification.id,identification.make,identification.model_year,identification.year,identification.color,identification.price FROM cars.car\n" +
                    " join identification on identification.id_identification = car.id_identification \n" +
                    " join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information\n" +
                    " join dimension on dimension.id_dimension = car.id_dimension\n" +
                    " join engine_information on engine_information.id_engine_information = car.id_engine_information\n" +
                    " join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics" +
                    " where " + para1 + "=" + "'"+ para2 + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int height = resultSet.getInt("height");
                int width = resultSet.getInt("width");
                int length = resultSet.getInt("length");
                int number_of_forward_gears = resultSet.getInt("number_of_forward_gears");
                int hoursepower = resultSet.getInt("hoursepower");
                int torque = resultSet.getInt("torque");
                int city_mpg = resultSet.getInt("city_mpg");
                int highway_mpg = resultSet.getInt("highway_mpg");
                int year = resultSet.getInt("year");
                double price = resultSet.getDouble("price");
                String driveline = resultSet.getString("driveline");
                String engine_type = resultSet.getString("engine_type");
                String hybrid = resultSet.getString("hybrid");
                String transmission = resultSet.getString("transmission");
                String fuel_type = resultSet.getString("fuel_type");
                String classification = resultSet.getString("classification");
                String id = resultSet.getString("id");
                String make = resultSet.getString("make");
                String model_year = resultSet.getString("model_year");
                String color = resultSet.getString("color");
                String vin = resultSet.getString("vin");
                EngineInformation engineInformation = new EngineInformation();
                engineInformation.setEngineStatistics(new EngineStatistics());
                car = CarBuilder.newInstance().setDimension(new Dimension()).setIdentification(new Identification())
                        .setEngineInformation(engineInformation).setFuelInformation(new FuelInformation())
                        .setHeight(height).setLength(length)
                        .setWidth(width).setDriveLine(driveline).setEngineType(engine_type).setHybrid(hybrid)
                        .setNumberOfForwardGears(number_of_forward_gears).setTransmission(transmission).setCityMpg(city_mpg)
                        .setFuelType(fuel_type).setHighwayMpg(highway_mpg).setClassification(classification)
                        .setId(id).setMake(make).setModelYear(model_year).setYear(year)
                        .setHorsePower(hoursepower).setTorque(torque).setColor(color).setPrice(price).build();
                car.getIdentification().setVin(vin);
            }
            statement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not get car from the DB");
        }
        return car;
    }
    public ArrayList<Car> getCarsFromDb(Connection connection) {
        ArrayList<Car> cars = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length," +
                    "engine_information.driveline,engine_information.engine_type,\n" +
                    "engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission,\n" +
                    "engine_statistics.hoursepower,engine_statistics.torque," +
                    "fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg,\n" +
                    "identification.classification,identification.id,identification.make,identification.model_year," +
                    "identification.year,identification.color,identification.price FROM cars.car\n" +
                    " join identification on identification.id_identification = car.id_identification \n" +
                    " join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information\n" +
                    " join dimension on dimension.id_dimension = car.id_dimension\n" +
                    " join engine_information on engine_information.id_engine_information = car.id_engine_information\n" +
                    " join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int height = resultSet.getInt("height");
                int width = resultSet.getInt("width");
                int length = resultSet.getInt("length");
                int number_of_forward_gears = resultSet.getInt("number_of_forward_gears");
                int hoursepower = resultSet.getInt("hoursepower");
                int torque = resultSet.getInt("torque");
                int city_mpg = resultSet.getInt("city_mpg");
                int highway_mpg = resultSet.getInt("highway_mpg");
                int year = resultSet.getInt("year");
                double price = resultSet.getDouble("price");
                String driveline = resultSet.getString("driveline");
                String engine_type = resultSet.getString("engine_type");
                String hybrid = resultSet.getString("hybrid");
                String transmission = resultSet.getString("transmission");
                String fuel_type = resultSet.getString("fuel_type");
                String classification = resultSet.getString("classification");
                String id = resultSet.getString("id");
                String make = resultSet.getString("make");
                String model_year = resultSet.getString("model_year");
                String color = resultSet.getString("color");
                String vin = resultSet.getString("vin");
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
                cars.get(cars.size()-1).getIdentification().setVin(vin);
            }
            statement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not get car/s from the DB");
        }
        return cars;
    }
}



