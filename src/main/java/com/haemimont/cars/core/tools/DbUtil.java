package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.model.Car;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtil {
    static Connection connection = null;

   static {
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/cars");
            connection = ds.getConnection();
        } catch (NamingException b) {
            throw new RuntimeException(b);
        } catch (SQLException a) {
            throw new RuntimeException(a);}
   }
    public static Connection getConnection(){
        return connection;
    }
    public static String getFreeVin(Car car){
        int i = (int) 'A';
        PreparedStatement preparedStatement = null;
//        while(i<=(int) 'Z')
        while(true)
        {
            try {
                char[] vinToChars= car.getIdentification().getVin().toCharArray();
                vinToChars[vinToChars.length-1] = (char) i;
                String vin ="";
                for(int curChar =0;curChar<vinToChars.length;curChar++)
                {
                    vin = vin+vinToChars[curChar];
                }
                car.getIdentification().setVin(vin);

                preparedStatement = connection.prepareStatement("INSERT into cars.car" +
                        "(vin,id_dimension,id_engine_information," +
                        "id_fuel_information,id_identification) values(?,?,?,?,?)");
                //preparedStatement.setInt(1, numberOfCars);
                preparedStatement.setString(1, car.getIdentification().getVin());
                preparedStatement.execute();
                preparedStatement.close();
                return  car.getIdentification().getVin();
            } catch (SQLException e) {
                CustomLogger.logError("Could not insert car:" + car.getIdentification().getVin() + "into car");
                if(i==(int) 'Z'){
                    String vin = car.getIdentification().getVin();
                    vin+="A";
                    car.getIdentification().setVin(vin);
                    i = (int) 'A';
                }else {
                    i++;
                }
            }
    }

}
