package com.haemimont.cars.core.apitest.integrationtests;

import com.haemimont.cars.core.jwttapi.ApiOperations;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.jwttapiresult.ApiResult;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class ApiIntegrationTest {
    public static ApiIntegrationTestResult registerLoginAndAuthTest(URL url, JSONObject jsonObject) throws MalformedURLException {//todo clean up
        URL registerUrl = new URL(url.toString() + "api/auth/signup");
        URL loginUrl = new URL(url + "api/auth/signin");
        URL allTestUrl = new URL(url + "api/test/all");
        URL userTestUrl = new URL(url + "api/test/user");//todo put in config
        URL modTestUrl = new URL(url + "api/test/mod");
        URL adminTestUrl = new URL(url + "api/test/admin");
        ApiIntegrationTestResult apiIntegrationTestResult = new ApiIntegrationTestResult();
        boolean register;
        boolean login;
        boolean all;
        boolean user;
        boolean mod;
        boolean admin;
//        if (ConnectToApiUnitTest.test(url)) {
        if (ApiOperations.connect(url).isSuccessful()) {
            ApiResult apiRegisterResult = ApiOperations.register(registerUrl, jsonObject);//register test
            register = apiRegisterResult.isSuccessful();
            apiIntegrationTestResult.addApiResultToList(apiRegisterResult);
            ApiResult apiLogInResult = ApiOperations.login(loginUrl, jsonObject);//login test
            login = apiLogInResult.isSuccessful();
            apiIntegrationTestResult.addApiResultToList(apiLogInResult);
            if (register || login) {
                JSONObject loginJsonMessage = new JSONObject(apiLogInResult.getMessage());
                String token = (String) loginJsonMessage.get("accessToken");
                String tokenType = (String) loginJsonMessage.get("tokenType");
                String jwttoken = tokenType + " " + token;
                ApiResult allTestApiResult = ApiOperations.authorizationTest(allTestUrl, jwttoken);
                all = allTestApiResult.isSuccessful();
                ApiResult userTestApiResult = ApiOperations.authorizationTest(userTestUrl, jwttoken);
                user = userTestApiResult.isSuccessful();
                ApiResult modTestApiResult = ApiOperations.authorizationTest(modTestUrl, jwttoken);
                mod = modTestApiResult.isSuccessful();
                ApiResult adminTestApiResult = ApiOperations.authorizationTest(adminTestUrl, jwttoken);
                admin = adminTestApiResult.isSuccessful();
                if (all || user || mod || admin) {
                    apiIntegrationTestResult.setSuccessful(true);
                }
            }
        }
        return apiIntegrationTestResult;
    }
}
