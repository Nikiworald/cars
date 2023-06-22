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
            register = apiRegisterResult.isSuccessful();
            apiIntegrationTestResult.addApiResultToList(apiRegisterResult);
            ApiResult apiLogInResult = ApiOperations.login(url + Config.getPropertyByName("loginUrl"), jsonObject);//login test
            login = apiLogInResult.isSuccessful();
            apiIntegrationTestResult.addApiResultToList(apiLogInResult);
            if (register || login) {
                JSONObject loginJsonMessage = new JSONObject(apiLogInResult.getMessage());
                String token = (String) loginJsonMessage.get("accessToken");
                String tokenType = (String) loginJsonMessage.get("tokenType");
                String jwttoken = tokenType + " " + token;
                ApiResult allTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("allTestUrl"), jwttoken);
                all = allTestApiResult.isSuccessful();
                ApiResult userTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("userTestUrl"), jwttoken);
                user = userTestApiResult.isSuccessful();
                ApiResult modTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("modTestUrl"), jwttoken);
                mod = modTestApiResult.isSuccessful();
                ApiResult adminTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("adminTestUrl"), jwttoken);
                admin = adminTestApiResult.isSuccessful();
                if (all || user || mod || admin) {
                    apiIntegrationTestResult.setSuccessful(true);
                }
            }
        }
        return apiIntegrationTestResult;
    }
}
