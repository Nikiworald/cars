package com.haemimont.cars.core.jwttapi;

import com.haemimont.cars.core.jwttapiresult.ApiRegisterResult;

import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
@Deprecated//new class ApiOperations
public class RegisterToApi {
    public static ApiRegisterResult register(URL url, JSONObject jsonObject) {
        ApiRegisterResult apiRegisterResult = new ApiRegisterResult();
        HttpPost httpPost = new HttpPost(url.toString());
        HttpClient httpClient = HttpClientBuilder.create().build();
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(jsonObject.toString());
            stringEntity.setContentType("application/json");
        } catch (UnsupportedEncodingException e) {
            apiRegisterResult.setSuccessful(false);
            apiRegisterResult.appendMessage(e +"--\n");
        }
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("Accept", "application/json");
        httpPost.setEntity(stringEntity);
        try (CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost)) {
            StatusLine statusLine = response.getStatusLine();
            apiRegisterResult.setSuccessful(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK);
            apiRegisterResult.setResponseCode(statusLine.getStatusCode());
            apiRegisterResult.appendMessage(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));//response Body

        } catch (Exception e) {
            apiRegisterResult.setSuccessful(false);
            apiRegisterResult.appendMessage(e.toString());
        }
        return apiRegisterResult;
    }
}
