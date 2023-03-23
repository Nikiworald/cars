package com.haemimont.cars.core.sql;

import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatements {

    public boolean checkForMatchingName(String name,Connection connection){
        boolean check = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user\n" +
                    "where user.name = ?");
            preparedStatement.setString(1,name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if(resultSet.next()){
                check = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }
    public boolean checkForMatchingEmail(String email,Connection connection){
        boolean check = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user\n" +
                    "where user.email = ?");
            preparedStatement.setString(1,email);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if(resultSet.next()){
                check = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }
    public void insertUserToDb(User user, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("Insert into cars.user(name,password,email,phoneNumber,salt) values (?,?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert USER:"+user.getName().toString()+" into USER");//fixme
        }
    }
}
