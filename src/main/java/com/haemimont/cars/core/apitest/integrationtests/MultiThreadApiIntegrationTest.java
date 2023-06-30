package com.haemimont.cars.core.apitest.integrationtests;

import com.haemimont.cars.core.apitest.callables.AuthTestCallable;
import com.haemimont.cars.core.apitest.callables.LogInCallable;
import com.haemimont.cars.core.apitest.callables.RegistrationCallable;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.jwttapiresult.ApiResult;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadApiIntegrationTest {

    public static ApiIntegrationTestResult registerLoginAndAuthTest(String url, JSONObject jsonObject) {//todo locked? or wait with while
        ApiIntegrationTestResult apiIntegrationTestResult = new ApiIntegrationTestResult();
        int processorCnt = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processorCnt);
        List<Future<ApiResult>> futureList = new ArrayList<>();
        futureList.add(executorService.submit(new RegistrationCallable(url, jsonObject)));// start the register first
        Future<ApiResult> LoginFuture = executorService.submit(new LogInCallable(url, jsonObject));//after register is done we try logging in
        futureList.add(LoginFuture);
        try {
            if (LoginFuture.get().isSuccessful()) {//if logging was successful test else do nothing
                JSONObject loginJsonMessage = new JSONObject(LoginFuture.get().getMessage());
                String JWTToken = loginJsonMessage.get("tokenType") + " " + loginJsonMessage.get("accessToken");
                AuthTestCallable allTest = new AuthTestCallable(url, JWTToken, "allTestUrl", "public access test");
                AuthTestCallable userTest = new AuthTestCallable(url, JWTToken, "userTestUrl", "user access test");
                AuthTestCallable modTest = new AuthTestCallable(url, JWTToken, "modTestUrl", "mod access test");
                AuthTestCallable adminTest = new AuthTestCallable(url, JWTToken, "adminTestUrl", "admin access test");
                futureList.add(executorService.submit(allTest));
                futureList.add(executorService.submit(userTest));
                futureList.add(executorService.submit(modTest));
                futureList.add(executorService.submit(adminTest));
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
        for (Future<ApiResult> future : futureList
        ) {
            try {
                apiIntegrationTestResult.addApiResultToList(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

        }
        return apiIntegrationTestResult;


    }

}