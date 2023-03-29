package com.haemimont.cars.core.service;

import com.haemimont.cars.core.model.User;
import com.haemimont.cars.core.sql.UserStatements;
import com.haemimont.cars.core.tools.DbUtil;

import java.sql.Connection;

public class UserService {
    Connection connection;
    UserStatements userStatements;

    public UserService() {
        this.connection = DbUtil.getConnection();
        UserStatements userStatements = new UserStatements();
        this.userStatements = userStatements;
    }

    public boolean get(String name, String password) {
        return userStatements.checkIfValidNameAndPassword(name, password, DbUtil.getConnection());
    }


    public String put(String name, String password, String email, String phoneNumber) {
        String response = "";
        if (userStatements.checkForMatchingName(name, connection)) {
            response += "Name is already taken.\n";
        }
        if (userStatements.checkForMatchingEmail(email, connection)) {
            response += "Email is already taken.\n";
        }
        if (userStatements.checkForMatchingPhoneNumber(phoneNumber, connection)) {
            response += "Phone number is already taken.\n";
        } else {
            User user = new User(name, password, email, phoneNumber);
            userStatements.insertUserToDb(user, connection);
            response += "register successfully.\n";
        }
        return response;
    }

}
