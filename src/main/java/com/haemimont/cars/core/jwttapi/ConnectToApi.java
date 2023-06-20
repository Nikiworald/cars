package com.haemimont.cars.core.jwttapi;

import com.haemimont.cars.core.jwttresult.ApiConnectionResult;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectToApi {
    public static ApiConnectionResult connect(URL url){
        ApiConnectionResult apiConnectionResult = new ApiConnectionResult();
        try {
            URLConnection connection = url.openConnection();
            connection.connect();
            apiConnectionResult.setSuccessful(true);
//            apiConnectionResult.setResponseCode(connection.getResponseCode);
        } catch (Exception e) {
            apiConnectionResult.setMessage(e.toString());
        }
        return apiConnectionResult;
    }
}
