package com.haemimont.cars.core.apitest.integrationtests;

import com.haemimont.cars.core.apitest.callables.AuthTestCallable;
import com.haemimont.cars.core.apitest.callables.LogInCallable;
import com.haemimont.cars.core.apitest.callables.RegistrationCallable;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.jwttapiresult.ApiResult;
import com.haemimont.cars.core.livetestupdate.LiveTests;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RealTimeUpdateMultiThreadApiIntegrationTest {
    public static void registerLoginAndAuthTest(String id, String url, JSONObject jsonObject) {
        //add delay
        ApiIntegrationTestResult apiIntegrationTestResult = LiveTests.getLiveTest(id);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        RegistrationCallable registrationCallable = new RegistrationCallable(id, apiIntegrationTestResult, url, jsonObject);
        LogInCallable logInCallable = new LogInCallable(id, apiIntegrationTestResult, url, jsonObject);
        executorService.submit(registrationCallable);
        Future<ApiResult> loginFuture = executorService.submit(logInCallable);//because we need the JWTToken from the login
        try {
            if (loginFuture.get().isSuccessful()) {
                JSONObject loginJsonMessage = new JSONObject(loginFuture.get().getMessage());
                String JWTToken = loginJsonMessage.get("tokenType") + " " + loginJsonMessage.get("accessToken");
                AuthTestCallable allTest = new AuthTestCallable(id, 3, apiIntegrationTestResult, url, JWTToken, "allTestUrl", "public access test");
                AuthTestCallable userTest = new AuthTestCallable(id, 4, apiIntegrationTestResult, url, JWTToken, "userTestUrl", "user access test");
                AuthTestCallable modTest = new AuthTestCallable(id, 5, apiIntegrationTestResult, url, JWTToken, "modTestUrl", "mod access test");
                AuthTestCallable adminTest = new AuthTestCallable(id, 6, apiIntegrationTestResult, url, JWTToken, "adminTestUrl", "admin access test");
                executorService.submit(allTest);
                executorService.submit(userTest);
                executorService.submit(modTest);
                executorService.submit(adminTest);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
