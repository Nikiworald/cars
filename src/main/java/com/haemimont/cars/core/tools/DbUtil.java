package com.haemimont.cars.core.tools;

import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.storage.Storage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
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
            throw new RuntimeException(a);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static String getAvailableVin(Car car) {
        car.getIdentification().getVin();
        int i = 'A';
        String vin = "";
        CarStatements carStatements = new CarStatements();
        if (car.getIdentification().getVin() == null || car.getIdentification().getVin().equals("")) {
            car.getIdentification().setVin(Generator.vinGenerator(car, new Storage()));
        }
        while (true) {
            vin = "";
            char[] vinToChars = car.getIdentification().getVin().toCharArray();
            vinToChars[vinToChars.length - 1] = (char) i;
            for (int curChar = 0; curChar < vinToChars.length; curChar++) {

                vin += vinToChars[curChar];
            }
//            car.getIdentification().setVin(vin);
            if (!carStatements.checkForMatchingVin(vin, connection)) {
                return vin;
            } else {
                if (i == (int) 'Z') {
//                    String vin = car.getIdentification().getVin();
                    vin += "A";
                    car.getIdentification().setVin(vin);
                    i = 'A';
                } else {
                    i++;
                }
            }
        }
    }
}
