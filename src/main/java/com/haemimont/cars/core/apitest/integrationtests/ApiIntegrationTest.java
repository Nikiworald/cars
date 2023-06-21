package com.haemimont.cars.core.apitest.integrationtests;

import com.haemimont.cars.core.apitest.unittests.ConnectToApiUnitTest;
import com.haemimont.cars.core.jwttapi.ApiOperations;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.jwttapiresult.ApiLogInResult;
import com.haemimont.cars.core.jwttapiresult.ApiRegisterResult;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class ApiIntegrationTest {
    public static ApiIntegrationTestResult test(URL url, JSONObject jsonObject) throws MalformedURLException {//todo
        URL registerUrl = new URL(url.toString() + "api/auth/signup");
        URL loginUrl = new URL(url + "api/auth/signin");
        ApiIntegrationTestResult apiIntegrationTestResult = new ApiIntegrationTestResult();
        boolean register = false;
        boolean login = false;
        if (ConnectToApiUnitTest.test(url)) {
            ApiRegisterResult apiRegisterResult = ApiOperations.register(registerUrl, jsonObject);//register test
            register = apiRegisterResult.isSuccessful();
            apiIntegrationTestResult.addApiResultToList(apiRegisterResult);
            ApiLogInResult apiLogInResult = ApiOperations.login(loginUrl,jsonObject);
            login = apiLogInResult.isSuccessful();
            apiIntegrationTestResult.addApiResultToList(apiLogInResult);


        }

return apiIntegrationTestResult;
    }


}
