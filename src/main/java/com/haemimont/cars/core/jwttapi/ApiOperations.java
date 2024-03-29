package com.haemimont.cars.core.jwttapi;

import com.haemimont.cars.core.jwttapiresult.ApiResult;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class ApiOperations {
    private static final HttpClient HTTP_CLIENT = HttpClientBuilder.create().build();

    public static ApiResult connect(String url) {
        ApiResult apiConnectionResult = new ApiResult();
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.connect();
            apiConnectionResult.setSuccessful(true);
        } catch (Exception e) {
            apiConnectionResult.appendMessage(e.toString());
        }
        return apiConnectionResult;
    }

    public static ApiResult register(String url, JSONObject jsonObject) {
        ApiResult apiRegisterResult = new ApiResult();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(jsonObject.toString());
            stringEntity.setContentType("application/json");
        } catch (UnsupportedEncodingException e) {
            apiRegisterResult.setSuccessful(false);
            apiRegisterResult.appendMessage(e + "--\n");
        }
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("Accept", "application/json");
        httpPost.setEntity(stringEntity);
        try (CloseableHttpResponse response = (CloseableHttpResponse) HTTP_CLIENT.execute(httpPost)) {
            StatusLine statusLine = response.getStatusLine();
            apiRegisterResult.setSuccessful(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK);
            apiRegisterResult.appendMessage(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));//response Body

        } catch (Exception e) {
            apiRegisterResult.setSuccessful(false);
            apiRegisterResult.appendMessage(e.toString());
        }
        return apiRegisterResult;
    }

    public static ApiResult login(String url, JSONObject jsonObject) {
        JSONObject loginJson = new JSONObject();
        loginJson.put("username", jsonObject.get("username"));
        loginJson.put("password", jsonObject.get("password"));
        ApiResult apiLogInResult = new ApiResult();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(loginJson.toString());
            stringEntity.setContentType("application/json");
        } catch (UnsupportedEncodingException e) {
            apiLogInResult.setSuccessful(false);
            apiLogInResult.appendMessage(e + "--\n");
        }
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("Accept", "application/json");
        httpPost.setEntity(stringEntity);
        try (CloseableHttpResponse response = (CloseableHttpResponse) HTTP_CLIENT.execute(httpPost)) {
            StatusLine statusLine = response.getStatusLine();
            apiLogInResult.setSuccessful(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK);
            apiLogInResult.appendMessage(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));//response Body

        } catch (Exception e) {
            apiLogInResult.setSuccessful(false);
            apiLogInResult.appendMessage(e.toString());
        }
        return apiLogInResult;
    }

    public static ApiResult authorizationTest(String url, String JWTToken) {
        ApiResult apiResult = new ApiResult();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", JWTToken);
        try (CloseableHttpResponse response = (CloseableHttpResponse) HTTP_CLIENT.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            apiResult.setSuccessful(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK);
            apiResult.appendMessage(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));//response Body

        } catch (Exception e) {
            apiResult.setSuccessful(false);
            apiResult.appendMessage(e.toString());
        }
        return apiResult;
    }
}
