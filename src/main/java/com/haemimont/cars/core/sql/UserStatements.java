package com.haemimont.cars.core.sql;

import com.haemimont.cars.core.loger.CustomLogger;
import com.haemimont.cars.core.model.User;
import com.haemimont.cars.core.tools.PasswordManager;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class UserStatements {
    private static final Random RANDOM = new SecureRandom();

    public boolean checkForMatchingName(String name, Connection connection) {
        boolean check = false;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user\n" +
                    "where user.name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                check = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public HashMap checkForMatchingNameAndReturnHashMap(String name, Connection connection) {
        PreparedStatement preparedStatement;
        HashMap<String, String> hashMap = new HashMap<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user\n" +
                    "where user.name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                hashMap.put("hashedDbPassword", resultSet.getString("password"));
                hashMap.put("salt", resultSet.getString("salt"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hashMap;
    }

    public boolean checkForMatchingEmail(String email, Connection connection) {
        boolean check = false;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user\n" +
                    "where user.email = ?");
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                check = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public boolean checkForMatchingPhoneNumber(String phoneNumber, Connection connection) {
        boolean check = false;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user\n" +
                    "where user.phoneNumber = ?");
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                check = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public String getAvailableSalt(Connection connection) {
        byte[] saltBytes = new byte[16];
        System.out.println(Arrays.toString(saltBytes));
        RANDOM.nextBytes(saltBytes);
        String salt = Arrays.toString(saltBytes);

        while (true) {
            if (checkForMatchingSalt(salt, connection)) {
                saltBytes = new byte[16];
                System.out.println(Arrays.toString(saltBytes));
                RANDOM.nextBytes(saltBytes);
                salt = Arrays.toString(saltBytes);
            } else {
                return salt;
            }
        }
    }

    public boolean checkForMatchingSalt(String salt, Connection connection) {
        boolean check = false;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user\n" +
                    "where user.salt = ?");
            preparedStatement.setString(1, salt);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                check = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void insertUserToDb(User user, Connection connection) {
        PreparedStatement preparedStatement;
        String salt = getAvailableSalt(connection);
        String password = user.getPassword();
        String saltedPassword = salt + password;
        String hashedPassword = PasswordManager.generateEncryptedPassword(saltedPassword);
        try {
            preparedStatement = connection.prepareStatement("Insert into cars.user(name,password,email,phoneNumber,salt) values (?,?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, salt);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            CustomLogger.logError("Could not insert USER:" + user.getName() + " into USER" + e);//fixme
        }
    }

    public boolean checkIfValidNameAndPassword(String name, String password, Connection connection) {
        boolean check = false;
        HashMap hashMap = checkForMatchingNameAndReturnHashMap(name, connection);
        if (hashMap.size() > 0) {
            String salt = hashMap.get("salt").toString();
            String hashedDbPassword = hashMap.get("hashedDbPassword").toString();
            String saltPassword = salt + password;
            String hashedPassword = PasswordManager.generateEncryptedPassword(saltPassword);
            if (hashedPassword.equals(hashedDbPassword)) {
                check = true;
            }
        }
        return check;

    }
}
