package com.haemimont.cars.core.service;

import com.haemimont.cars.core.model.Car;
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

    public Boolean matchingNameAndPasswordInDb(String name,String password){
        boolean check = userStatements.checkForMatchingNameAndPassword(name,password,this.connection);
        return check;
    }

}
