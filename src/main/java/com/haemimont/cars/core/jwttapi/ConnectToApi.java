package com.haemimont.cars.core.jwttapi;

import com.haemimont.cars.core.jwttapiresult.ApiConnectionResult;

import java.net.URL;
import java.net.URLConnection;
@Deprecated//new class ApiOperations
public class ConnectToApi {
    public static ApiConnectionResult connect(URL url){
        ApiConnectionResult apiConnectionResult = new ApiConnectionResult();
        try {
            URLConnection connection = url.openConnection();
            connection.connect();
            apiConnectionResult.setSuccessful(true);
//            apiConnectionResult.setResponseCode(connection.getResponseCode);
        } catch (Exception e) {
            apiConnectionResult.appendMessage(e.toString());
        }
        return apiConnectionResult;
    }
}
