package com.haemimont.cars.core.tools;

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
            throw new RuntimeException(a);}
   }
    public static Connection getConnection(){
        return connection;
    }
}
