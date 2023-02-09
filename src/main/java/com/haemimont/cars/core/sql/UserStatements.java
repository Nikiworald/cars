package com.haemimont.cars.core.sql;

import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatements {
    public void insertUserToDb(User user, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("Insert into cars.user(name,password) values (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert USER:"+user.getName().toString()+" into USER");
        }
    }
    public boolean checkForMatchingNameAndPassword(String name ,String password,Connection connection){
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            String query = "SELECT * FROM cars.user where name = '"+name+"'"+
                    " AND password = '"+password+"'";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){check = true;}
        } catch (SQLException e) {
            CustomLogger.logError("could not check for matching name and password");
        }
        return check;

    }
}
