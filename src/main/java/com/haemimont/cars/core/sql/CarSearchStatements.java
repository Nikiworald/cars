package com.haemimont.cars.core.sql;

import com.haemimont.cars.core.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CarSearchStatements {
    public ArrayList<Object> searchCarsByMap(HashMap<String, String> criteriaMap, Connection connection) {
        ArrayList<Object> cars = new ArrayList<>();
        String[] keys = criteriaMap.keySet().toArray(new String[0]);
        String sql = "";
        String extra = "";
        SqlBuilder sqlBuilder;
        try {
             sqlBuilder = new SqlBuilder("SELECT  car.id_car,car.vin,dimension.height,dimension.width," +
                     "dimension.length,engine_information.driveline,engine_information.engine_type,\n" +
                     "engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission,\n" +
                     "engine_statistics.horsepower,engine_statistics.torque,fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg,\n" +
                     "identification.classification,identification.id,identification.make,identification.model_year,identification.year,identification.color,identification.price FROM cars.car\n" +
                     "join identification on identification.id_identification = car.id_identification \n" + "join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information\n" +
                     "join dimension on dimension.id_dimension = car.id_dimension\n" + "join engine_information on engine_information.id_engine_information = car.id_engine_information\n" +
                     "join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics \n", connection);

            for (String key : keys) {
                if (key.equals("make")) {
                    sqlBuilder.equation("make", criteriaMap.get(key).trim());

                }
                if (key.equals("minPrice")) {
                    sqlBuilder.biggerThan("price", criteriaMap.get(key).trim());

                }
                if (key.equals("maxPrice")) {
                    sqlBuilder.smallerThan("price", criteriaMap.get(key).trim());
                }
                if (key.equals("minYear")) {
                    sqlBuilder.biggerThan("year", criteriaMap.get(key).trim());

                }
                if (key.equals("maxYear")) {
                    sqlBuilder.smallerThan("year", criteriaMap.get(key).trim());

                }
                if (key.equals("classification")) {
                    sqlBuilder.equation("classification", criteriaMap.get(key).trim());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            ResultSet resultSet = sqlBuilder.execute();
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
                Car car;
                car = CarBuilder.newInstance().setDimension(new Dimension()).setIdentification(new Identification())
                        .setEngineInformation(engineInformation).setFuelInformation(new FuelInformation())
                        .setHeight(height).setLength(length)
                        .setWidth(width).setDriveLine(driveline).setEngineType(engine_type).setHybrid(hybrid)
                        .setNumberOfForwardGears(number_of_forward_gears).setTransmission(transmission).setCityMpg(city_mpg)
                        .setFuelType(fuel_type).setHighwayMpg(highway_mpg).setClassification(classification)
                        .setId(id).setMake(make).setModelYear(model_year).setYear(year)
                        .setHorsePower(horsepower).setTorque(torque).setColor(color).setPrice(price).build();
                car.getIdentification().setVin(vin);
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }
}



