package com.haemimont.cars.core.apitest.integrationtests;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.jwttapi.ApiOperations;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.jwttapiresult.ApiResult;
import org.json.JSONObject;

@Deprecated//replaced by multiThread
@SuppressWarnings("all")

public class ApiIntegrationTest {
    public static ApiIntegrationTestResult registerLoginAndAuthTest(String url, JSONObject jsonObject) {//todo multi treating /live update
        ApiIntegrationTestResult apiIntegrationTestResult = new ApiIntegrationTestResult();
        JSONObject loginJsonMessage;
        if (ApiOperations.connect(url).isSuccessful()) {
            ApiResult registerResult = registrationTest(apiIntegrationTestResult, url, jsonObject);
            ApiResult loginResult = loginTest(apiIntegrationTestResult, url, jsonObject);
            if (registerResult.isSuccessful() || loginResult.isSuccessful()) {
                loginJsonMessage = new JSONObject(loginResult.getMessage());
                String JWTToken = loginJsonMessage.get("tokenType") + " " + loginJsonMessage.get("accessToken");
                publicTest(apiIntegrationTestResult, url);
                userTest(apiIntegrationTestResult, url, JWTToken);
                modTest(apiIntegrationTestResult, url, JWTToken);
                adminTest(apiIntegrationTestResult, url, JWTToken);
            }
        }
        return apiIntegrationTestResult;
    }

    private static ApiResult registrationTest(ApiIntegrationTestResult apiIntegrationTestResult, String url, JSONObject jsonObject) {
        ApiResult apiRegisterResult = ApiOperations.register(url + Config.getPropertyByName("registerUrl"), jsonObject);
        apiRegisterResult.setName("registration test");
        apiIntegrationTestResult.addApiResultToList(apiRegisterResult);
        return apiRegisterResult;
    }

    private static ApiResult loginTest(ApiIntegrationTestResult apiIntegrationTestResult, String url, JSONObject jsonObject) {
        ApiResult apiLogInResult = ApiOperations.login(url + Config.getPropertyByName("loginUrl"), jsonObject);
        apiLogInResult.setName("login test");
        apiIntegrationTestResult.addApiResultToList(apiLogInResult);
        return apiLogInResult;
    }

    private static void publicTest(ApiIntegrationTestResult apiIntegrationTestResult, String url) {
        ApiResult allTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("allTestUrl"), "");//no token needed for public access
        allTestApiResult.setName("public access test");
        apiIntegrationTestResult.addApiResultToList(allTestApiResult);
    }

    private static void userTest(ApiIntegrationTestResult apiIntegrationTestResult, String url, String JWTToken) {
        ApiResult userTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("userTestUrl"), JWTToken);
        userTestApiResult.setName("user access test");
        apiIntegrationTestResult.addApiResultToList(userTestApiResult);
    }

    private static void modTest(ApiIntegrationTestResult apiIntegrationTestResult, String url, String JWTToken) {
        ApiResult modTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("modTestUrl"), JWTToken);
        modTestApiResult.setName("mod access test");
        apiIntegrationTestResult.addApiResultToList(modTestApiResult);

    }

    private static void adminTest(ApiIntegrationTestResult apiIntegrationTestResult, String url, String JWTToken) {
        ApiResult adminTestApiResult = ApiOperations.authorizationTest(url + Config.getPropertyByName("adminTestUrl"), JWTToken);
        adminTestApiResult.setName("admin access test");
        apiIntegrationTestResult.addApiResultToList(adminTestApiResult);
    }
}

