package com.haemimont.cars.core.sql;

import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.model.*;
import com.haemimont.cars.core.tools.DbUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;

public class CarStatements {//preset of queries

    //trying to connect to the sql db and inserting the information from an object to the dimension table
    //and gets the corresponding id_dimension
    public int insertCarInDimension(Car car, Connection connection) {
        PreparedStatement preparedStatement;
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
            CustomLogger.logError("Could not insert car:" + car.getIdentification().getVin() + "into dimension");
        }
        return 0;
    }

    public int insertCarInFuelInformation(Car car, Connection connection) {
        PreparedStatement preparedStatement;
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
            CustomLogger.logError("Could not insert car:" + car.getIdentification().getVin() + "into fuel_information");
        }
        return 0;
    }

    public int insertCarInIdentification(Car car, Connection connection) {
        PreparedStatement preparedStatement;
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
            CustomLogger.logError("Could not insert car:" + car.getIdentification().getVin() + "into identification");
        }
        return 0;
    }

    public int insertCarInEngineStatistics(Car car, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            String query = "INSERT into cars.engine_statistics(horsepower,torque) values(?,?)";
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
            CustomLogger.logError("Could not insert car:" + car.getIdentification().getVin() + "into engine_statistics" + e);
        }
        return 0;
    }

    public int insertCarInEngineStatistics(Car car, int engineStatisticsId, Connection connection) {
        PreparedStatement preparedStatement;
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
            CustomLogger.logError("Could not insert car:" + car.getIdentification().getVin() + "into engine_information");
        }
        return 0;
    }

    public void insertCarFromStorageInCar(Car car, int dimensionId, int engineInformationId,
                                          int fuelId, int identificationId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
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

    }

    public Boolean checkForMatchingVin(String vin, Connection connection) {
        boolean check = false;
        PreparedStatement preparedStatement;
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
            CustomLogger.logError("Could not check for vin:" + vin);
        }
        return check;
    }

    //get data with a matching identification.make and makes an object
    public ArrayList<Car> getCarsFromDb(String where, String value, @NotNull Connection connection) {
        ArrayList<Car> cars = new ArrayList<>();
        PreparedStatement preparedStatement;
        String whereStatement = null;
        if (where.equals("make")) {
            whereStatement = " where make";
        }
        if (where.equals("year")) {
            whereStatement = " where year";
        }
        if (where.equals("id")) {
            whereStatement = " where id_car";
        }
//        if(where.equals("make")){whereStatement = "where make";}
//        if(where.equals("make")){whereStatement = "where make";}
        try {
            preparedStatement = connection.prepareStatement("SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length,engine_information.driveline,engine_information.engine_type," +
                    "engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission," +
                    "engine_statistics.horsepower,engine_statistics.torque,fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg," +
                    "identification.classification,identification.id,identification.make,identification.model_year,identification.year,identification.color,identification.price FROM cars.car" +
                    " join identification on identification.id_identification = car.id_identification " +
                    " join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information" +
                    " join dimension on dimension.id_dimension = car.id_dimension" +
                    " join engine_information on engine_information.id_engine_information = car.id_engine_information" +
                    " join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics " +
                    whereStatement + " = ?");
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int height = resultSet.getInt("height");
                int width = resultSet.getInt("width");
                int length = resultSet.getInt("length");
                int number_of_forward_gears = resultSet.getInt("number_of_forward_gears");
                int horsepower = resultSet.getInt("horsepower");
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
                        .setHorsePower(horsepower).setTorque(torque).setColor(color).setPrice(price).build());
                cars.get(cars.size() - 1).getIdentification().setVin(vin);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not get car/s from the DB");
        }
        return cars;
    }

    public ArrayList<Car> getCarsFromDbByPrice(String minValue, String maxValue, @NotNull Connection connection) {
        ArrayList<Car> cars = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT  car.id_car,car.vin,dimension.height," +
                    "dimension.width,dimension.length,engine_information.driveline,engine_information.engine_type," +
                    "engine_information.hybrid,engine_information.number_of_forward_gears," +
                    "engine_information.transmission,engine_statistics.horsepower,engine_statistics.torque," +
                    "fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg," +
                    "identification.classification,identification.id,identification.make,identification.model_year," +
                    "identification.year,identification.color,identification.price FROM cars.car" +
                    " join identification on identification.id_identification = car.id_identification  " +
                    "join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information " +
                    "join dimension on dimension.id_dimension = car.id_dimension " +
                    "join engine_information on engine_information.id_engine_information = car.id_engine_information " +
                    "join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics " +
                    "where price > ? AND price < ?");
            preparedStatement.setString(1, minValue);
            preparedStatement.setString(2, maxValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int height = resultSet.getInt("height");
                int width = resultSet.getInt("width");
                int length = resultSet.getInt("length");
                int number_of_forward_gears = resultSet.getInt("number_of_forward_gears");
                int horsepower = resultSet.getInt("horsepower");
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
                        .setHorsePower(horsepower).setTorque(torque).setColor(color).setPrice(price).build());
                cars.get(cars.size() - 1).getIdentification().setVin(vin);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not get car/s from the DB");
        }
        return cars;
    }

    public Car getCarFromDb(String where, String value, @NotNull Connection connection) {
        Car car = null;
        PreparedStatement preparedStatement;
        String whereStatement = null;
        if (where.equals("make")) {
            whereStatement = " where make";
        }
        if (where.equals("year")) {
            whereStatement = " where year";
        }
        if (where.equals("id")) {
            whereStatement = " where id_car";
        }
        try {
            preparedStatement = connection.prepareStatement("SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length,engine_information.driveline,engine_information.engine_type," +
                    "engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission," +
                    "engine_statistics.horsepower,engine_statistics.torque,fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg," +
                    "identification.classification,identification.id,identification.make,identification.model_year,identification.year,identification.color,identification.price FROM cars.car" +
                    " join identification on identification.id_identification = car.id_identification " +
                    " join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information" +
                    " join dimension on dimension.id_dimension = car.id_dimension" +
                    " join engine_information on engine_information.id_engine_information = car.id_engine_information" +
                    " join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics " +
                    whereStatement + " = ?");
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int height = resultSet.getInt("height");
                int width = resultSet.getInt("width");
                int length = resultSet.getInt("length");
                int number_of_forward_gears = resultSet.getInt("number_of_forward_gears");
                int horsepower = resultSet.getInt("horsepower");
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
                car = (CarBuilder.newInstance().setDimension(new Dimension()).setIdentification(new Identification())
                        .setEngineInformation(engineInformation).setFuelInformation(new FuelInformation())
                        .setHeight(height).setLength(length)
                        .setWidth(width).setDriveLine(driveline).setEngineType(engine_type).setHybrid(hybrid)
                        .setNumberOfForwardGears(number_of_forward_gears).setTransmission(transmission).setCityMpg(city_mpg)
                        .setFuelType(fuel_type).setHighwayMpg(highway_mpg).setClassification(classification)
                        .setId(id).setMake(make).setModelYear(model_year).setYear(year)
                        .setHorsePower(horsepower).setTorque(torque).setColor(color).setPrice(price).build());
                car.getIdentification().setVin(vin);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not get car/s from the DB");
        }
        return car;
    }

    public ArrayList<Car> getAllCarsFromDb(Connection connection) {
        ArrayList<Car> cars = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length," +
                    "engine_information.driveline,engine_information.engine_type,\n" +
                    "engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission,\n" +
                    "engine_statistics.horsepower,engine_statistics.torque," +
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
                int horsepower = resultSet.getInt("horsepower");
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
                        .setHorsePower(horsepower).setTorque(torque).setColor(color).setPrice(price).build());
                cars.get(cars.size() - 1).getIdentification().setVin(vin);
            }
            statement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not get car/s from the DB");
        }
        return cars;
    }

    public Boolean insertCarFromUserInputInCar(Car car, int dimensionId, int engineInformationId,
                                               int fuelId, int identificationId, Connection connection) {
        PreparedStatement preparedStatement;
        car.getIdentification().setVin(DbUtil.getAvailableVin(car));
        try {
            preparedStatement = connection.prepareStatement("INSERT into cars.car" +
                    "(vin,id_dimension,id_engine_information," +
                    "id_fuel_information,id_identification) values(?,?,?,?,?)");
            preparedStatement.setString(1, car.getIdentification().getVin());
            preparedStatement.setInt(2, dimensionId);
            preparedStatement.setInt(3, engineInformationId);
            preparedStatement.setInt(4, fuelId);
            preparedStatement.setInt(5, identificationId);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert car:" + car.getIdentification().getVin() + "into car");
            return false;
        }
    }

    public int getIdByVin(String vin, Connection connection) {
        int id = 0;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length,engine_information.driveline,engine_information.engine_type," +
                    " engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission," +
                    "engine_statistics.horsepower,engine_statistics.torque,fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg," +
                    "identification.classification,identification.id,identification.make,identification.model_year,identification.year,identification.color,identification.price FROM cars.car " +
                    "join identification on identification.id_identification = car.id_identification " +
                    "join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information " +
                    "join dimension on dimension.id_dimension = car.id_dimension " +
                    "join engine_information on engine_information.id_engine_information = car.id_engine_information " +
                    "join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics " +
                    "where vin = ?");
            preparedStatement.setString(1, vin);
            ResultSet resultSet = preparedStatement.executeQuery();
//    preparedStatement = connection.prepareStatement();
            if (resultSet.next()) {
                id = resultSet.getInt("id_car");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public Car getCarById(int carId, Connection connection) {
        Car car = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length,engine_information.driveline,engine_information.engine_type," +
                    " engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission," +
                    "engine_statistics.horsepower,engine_statistics.torque,fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg," +
                    "identification.classification,identification.id,identification.make,identification.model_year,identification.year,identification.color,identification.price FROM cars.car " +
                    "join identification on identification.id_identification = car.id_identification " +
                    "join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information " +
                    "join dimension on dimension.id_dimension = car.id_dimension " +
                    "join engine_information on engine_information.id_engine_information = car.id_engine_information " +
                    "join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics " +
                    "where id_car = ?");
            preparedStatement.setInt(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int height = resultSet.getInt("height");
                int width = resultSet.getInt("width");
                int length = resultSet.getInt("length");
                int number_of_forward_gears = resultSet.getInt("number_of_forward_gears");
                int horsepower = resultSet.getInt("horsepower");
                int torque = resultSet.getInt("torque");
                int city_mpg = resultSet.getInt("city_mpg");
                int highway_mpg = resultSet.getInt("highway_mpg");
                int year = resultSet.getInt("year");
                double price = resultSet.getDouble("price");
                String driveline = resultSet.getString("driveline");
                String engine_type = resultSet.getString("engine_type");
//                String hybrid = resultSet.getString("hybrid");
                boolean hybrid = resultSet.getBoolean("hybrid");
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
                car = (CarBuilder.newInstance().setDimension(new Dimension()).setIdentification(new Identification())
                        .setEngineInformation(engineInformation).setFuelInformation(new FuelInformation())
                        .setHeight(height).setLength(length)
                        .setWidth(width).setDriveLine(driveline).setEngineType(engine_type).setHybrid(hybrid)
                        .setNumberOfForwardGears(number_of_forward_gears).setTransmission(transmission).setCityMpg(city_mpg)
                        .setFuelType(fuel_type).setHighwayMpg(highway_mpg).setClassification(classification)
                        .setId(id).setMake(make).setModelYear(model_year).setYear(year)
                        .setHorsePower(horsepower).setTorque(torque).setColor(color).setPrice(price).build());
                car.getIdentification().setVin(vin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    public int updateCar(Car car, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            int carId = getIdByVin(car.getIdentification().getVin(), connection);      //get the car's id before we update it
//            car.getIdentification().setVin( Generator.vinGenerator(car,new Storage()));
            car.getIdentification().setVin(DbUtil.getAvailableVin(car));        //generate available vin
            String query = "update car\n" +
                    "join identification i on i.id_identification = car.id_identification \n" +
                    "join fuel_information f on f.id_fuel_information = car.id_fuel_information\n" +
                    "join dimension d on d.id_dimension = car.id_dimension\n" +
                    "join engine_information ei on  car.id_engine_information =  ei.id_engine_information\n" +
                    "join engine_statistics es on  es.id_engine_statistics = ei.id_engine_statistics \n" +
                    "set height=? ,width=?,length=?,driveline=?,engine_type=?,hybrid=?,number_of_forward_gears=?," +
                    "transmission=?,horsepower=?,torque=?,city_mpg=?,fuel_type=?,highway_mpg=?" +
                    ",classification=?,id=?,make=?\n" +
                    ",model_year=?,year=?,color=?,price=?,vin=?\n" +
                    "where car.id_car=?;\n";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, car.getDimension().getHeight());
            preparedStatement.setInt(2, car.getDimension().getWidth());
            preparedStatement.setInt(3, car.getDimension().getLength());
            preparedStatement.setString(4, car.getEngineInformation().getDriveLine());
            preparedStatement.setString(5, car.getEngineInformation().getEngineType());
            preparedStatement.setBoolean(6, car.getEngineInformation().isHybrid());
            preparedStatement.setInt(7, car.getEngineInformation().getNumberOfForwardGears());
            preparedStatement.setString(8, car.getEngineInformation().getTransmission());
            preparedStatement.setInt(9, car.getEngineInformation().getEngineStatistics().getHorsePower());
            preparedStatement.setInt(10, car.getEngineInformation().getEngineStatistics().getTorque());
            preparedStatement.setInt(11, car.getFuelInformation().getCityMpg());
            preparedStatement.setString(12, car.getFuelInformation().getFuelType());
            preparedStatement.setInt(13, car.getFuelInformation().getHighwayMpg());
            preparedStatement.setString(14, car.getIdentification().getClassification());
            preparedStatement.setString(15, car.getIdentification().getId());
            preparedStatement.setString(16, car.getIdentification().getMake());
            preparedStatement.setString(17, car.getIdentification().getModelYear());
            preparedStatement.setInt(18, car.getIdentification().getYear());
            preparedStatement.setString(19, car.getIdentification().getColor());
            preparedStatement.setDouble(20, car.getIdentification().getPrice());
            preparedStatement.setString(21, car.getIdentification().getVin());
            preparedStatement.setInt(22, carId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return carId;
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert car:" + car.getIdentification().getVin() + "into engine_statistics");
        }
        return 0;
    }

    public boolean deleteCarById(int id, Connection connection) {
        boolean check = false;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("delete  car,dimension,engine_information,engine_statistics,fuel_information,identification\n" +
                    "from car\n" +
                    "join identification on identification.id_identification = car.id_identification \n" +
                    "join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information\n" +
                    "join dimension on dimension.id_dimension = car.id_dimension\n" +
                    "join engine_information on engine_information.id_engine_information = car.id_engine_information\n" +
                    "join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics \n" +
                    "where id_car = ? \n");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            Car car = getCarById(id, connection);
            if (car == null) {
                check = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

}



