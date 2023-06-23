package com.haemimont.cars.core.apitest.integrationtests;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.jwttapi.ApiOperations;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.jwttapiresult.ApiResult;
import org.json.JSONObject;

public class ApiIntegrationTest {
    public static ApiIntegrationTestResult registerLoginAndAuthTest(String url, JSONObject jsonObject) {
        ApiIntegrationTestResult apiIntegrationTestResult = new ApiIntegrationTestResult();
        boolean register;
        boolean login;
        boolean all;
        boolean user;
        boolean mod;
        boolean admin;
        if (ApiOperations.connect(url).isSuccessful()) {
            ApiResult apiRegisterResult = ApiOperations.register(url + Config.getPropertyByName("registerUrl"), jsonObject);//register test
            apiRegisterResult.setName("registration test");
            register = apiRegisterResult.isSuccessful();
            apiIntegrationTestResult.addApiResultToList(apiRegisterResult);
            ApiResult apiLogInResult = ApiOperations.login(url + Config.getPropertyByName("loginUrl"), jsonObject);//login test
            apiLogInResult.setName("login test");
            login = apiLogInResult.isSuccessful();
            apiIntegrationTestResult.addApiResultToList(apiLogInResult);
            if (register || login) {
                JSONObject loginJsonMessage = new JSONObject(apiLogInResult.getMessage());
                String jwttoken = loginJsonMessage.get("tokenType")  + " " + loginJsonMessage.get("accessToken");
                ApiResult allTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("allTestUrl"), jwttoken);
                allTestApiResult.setName("public access test");
                all = allTestApiResult.isSuccessful();
                apiIntegrationTestResult.addApiResultToList(allTestApiResult);
                ApiResult userTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("userTestUrl"), jwttoken);
                userTestApiResult.setName("user access test");
                user = userTestApiResult.isSuccessful();
                apiIntegrationTestResult.addApiResultToList(userTestApiResult);
                ApiResult modTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("modTestUrl"), jwttoken);
                modTestApiResult.setName("mod access test");
                mod = modTestApiResult.isSuccessful();
                apiIntegrationTestResult.addApiResultToList(modTestApiResult);
                ApiResult adminTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("adminTestUrl"), jwttoken);
                adminTestApiResult.setName("admin access test");
                admin = adminTestApiResult.isSuccessful();
                apiIntegrationTestResult.addApiResultToList(adminTestApiResult);
            }
        }
        return apiIntegrationTestResult;
    }
}
