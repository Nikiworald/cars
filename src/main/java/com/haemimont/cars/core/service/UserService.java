package com.haemimont.cars.core.service;

import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.model.User;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.sql.UserStatements;
import com.haemimont.cars.core.tools.DbUtil;

import java.sql.Connection;
import java.util.ArrayList;

public class UserService {
    Connection connection ;
    UserStatements userStatements;

    public UserService() {
        this.connection = DbUtil.getConnection();
        UserStatements userStatements = new UserStatements();
        this.userStatements = userStatements;
    }


    public String put(String name,String password,String email)
    { String response = "";
        if(userStatements.checkForMatchingName(name,connection)){
            response +="Name is already taken.\n";
        }
        if(userStatements.checkForMatchingEmail(email,connection)){
            response +="Email is already taken.\n";
        }
        User user = new User(name,password,email);
        userStatements.insertUserToDb(user,connection);



        return response;
    }

}
