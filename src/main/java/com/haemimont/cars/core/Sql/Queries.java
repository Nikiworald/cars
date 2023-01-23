package com.haemimont.cars.core.Sql;
import com.haemimont.cars.core.storage.Storage;
import com.haemimont.cars.core.model.*;
import java.sql.*;
import java.util.ArrayList;
public class Queries {//preset of queries
    int dimensionId, fuelId, identificationId, engineStatisticsId, engineInformationId, numberOfCars = 1;
    Connection connection;
    public void connect(String url, String name, String password) {
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //trying to connect to the sql db and inserting the information from an object to the dimension table
    //and gets the corresponding id_dimension
    public void fillDimensionAndSetId(String key, Storage<String, Car> storageForCars) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into dimension(height,width,length) values(?,?,?)";
            preparedStatement = this.connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, storageForCars.get(key).getDimension().getHeight());
            preparedStatement.setInt(2, storageForCars.get(key).getDimension().getWidth());
            preparedStatement.setInt(3, storageForCars.get(key).getDimension().getLength());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                dimensionId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getDimensionId() {
        return dimensionId;
    }

    public void fillFuelInformationAndSetId(String key, Storage<String, Car> storageForCars) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into fuel_information(city_mpg,fuel_type,highway_mpg)" +
                    " values(?,?,?)";
            preparedStatement = this.connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, storageForCars.get(key).getFuelInformation().getCityMpg());
            preparedStatement.setString(2, storageForCars.get(key).getFuelInformation().getFuelType());
            preparedStatement.setInt(3, storageForCars.get(key).getFuelInformation().getHighwayMpg());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                fuelId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getFuelId() {
        return fuelId;
    }


    public void fillIdentificationAndSetId(String key, Storage<String, Car> storageForCars) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into identification(classification,id," +
                    "make,model_year,year,color,price) values(?,?,?,?,?,?,?)";
            preparedStatement = this.connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, storageForCars.get(key).getIdentification().getClassification());
            preparedStatement.setString(2, storageForCars.get(key).getIdentification().getId());
            preparedStatement.setString(3, storageForCars.get(key).getIdentification().getMake());
            preparedStatement.setString(4,storageForCars.get(key).getIdentification().getModelYear());
            preparedStatement.setInt(5,storageForCars.get(key).getIdentification().getYear());
            preparedStatement.setString(6,storageForCars.get(key).getIdentification().getColor());
            preparedStatement.setDouble(7,storageForCars.get(key).getIdentification().getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                identificationId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIdentificationId() {
        return identificationId;
    }
     public void fillEngineStatisticsAndSetId(String key, Storage<String, Car> storageForCars) {
         PreparedStatement preparedStatement = null;
         try {
             String query = "INSERT into cars.engine_statistics(hoursepower,torque) values(?,?)";
             preparedStatement = this.connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
             preparedStatement.setInt(1, storageForCars.get(key).getEngineInformation().getEngineStatistics().getHorsePower());
             preparedStatement.setInt(2, storageForCars.get(key).getEngineInformation().getEngineStatistics().getTorque() );
             preparedStatement.executeUpdate();
             ResultSet resultSet = preparedStatement.getGeneratedKeys();
             if(resultSet.next()) {
                 engineStatisticsId= resultSet.getInt(1);
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }
    public int getEngineStatisticsId() {
        return engineStatisticsId;
    }
    public void fillEngineInformationAndSetId(String key, Storage<String, Car> storageForCars, int engineStatisticsId) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into cars.engine_information" +
                    "(driveline,engine_type,hybrid,number_of_forward_gears,transmission,id_engine_statistics) " +
                    "values(?,?,?,?,?,?)";
            preparedStatement = this.connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, storageForCars.get(key).getEngineInformation().getDriveLine());
            preparedStatement.setString(2, storageForCars.get(key).getEngineInformation().getEngineType());
            preparedStatement.setBoolean(3, storageForCars.get(key).getEngineInformation().isHybrid());
            preparedStatement.setInt(4,storageForCars.get(key).getEngineInformation().getNumberOfForwardGears());
            preparedStatement.setString(5,storageForCars.get(key).getEngineInformation().getTransmission());
            preparedStatement.setInt(6,engineStatisticsId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                engineInformationId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getEngineInformationId() {
        return engineInformationId;
    }

    public void fillCar(String key, Storage<String, Car> storageForCars, int dimensionId, int engineInformationId,
                        int fuelId, int identificationId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement("INSERT into cars.car" +
                    "(id_car,vin,id_dimension,id_engine_information," +
                    "id_fuel_information,id_identification) values(?,?,?,?,?,?)");
            preparedStatement.setInt(1, numberOfCars);
            preparedStatement.setString(2, storageForCars.get(key).getIdentification().getVin());
            preparedStatement.setInt(3, dimensionId);
            preparedStatement.setInt(4,engineInformationId);
            preparedStatement.setInt(5,fuelId);
            preparedStatement.setInt(6,identificationId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        numberOfCars++;
    }

    //get data with a matching identification.make and makes an object
    public ArrayList<Car> fromDbMakeToCarObj(String make) {
        ArrayList<Car> cars = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String query = "SELECT  car.id_car,car.vin,dimension.height,dimension.width,dimension.length,engine_information.driveline,engine_information.engine_type,\n" +
                    "engine_information.hybrid,engine_information.number_of_forward_gears,engine_information.transmission,\n" +
                    "engine_statistics.hoursepower,engine_statistics.torque,fuel_information.city_mpg,fuel_information.fuel_type,fuel_information.highway_mpg,\n" +
                    "identification.classification,identification.id,identification.make,identification.model_year,identification.year,identification.color,identification.price FROM cars.car\n" +
                    " join identification on identification.id_identification = car.id_identification \n" +
                    " join fuel_information on fuel_information.id_fuel_information = car.id_fuel_information\n" +
                    " join dimension on dimension.id_dimension = car.id_dimension\n" +
                    " join engine_information on engine_information.id_engine_information = car.id_engine_information\n" +
                    " join engine_statistics on  engine_statistics.id_engine_statistics = engine_information.id_engine_statistics" +
                    " where make = '" + make + "'";
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



