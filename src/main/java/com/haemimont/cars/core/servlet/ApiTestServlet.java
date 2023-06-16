package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.tools.ApiRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.checkerframework.common.reflection.qual.GetMethod;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;

@WebServlet("/ApiTest")
public class ApiTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ApiRequest.validate(req)) {//todo do the api request and get the jwttoken and test the thing
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String userTypes = req.getParameter("userType");
            String[] userTypesSplit = userTypes.trim().split(",");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", name.trim());
            jsonObject.put("email", email.trim());
            jsonObject.put("password", password.trim());
            jsonObject.put("role", userTypesSplit);
            URL url = new URL("http://192.168.250.206:8080/api/auth/signup");
            System.out.println(postRequest(url, jsonObject));

        } else {
            System.out.println("error");
        }
    }

    public String postRequest(URL url, JSONObject jsonObject) {
        String response = null;
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            System.out.println(jsonObject.toString());
            StringEntity requestEntity = new StringEntity(
                    jsonObject.toString(),
                    ContentType.APPLICATION_JSON);
            System.out.println(requestEntity);
            HttpPost postRequest = new HttpPost(url.toString());
            postRequest.setEntity(requestEntity);
            postRequest.setHeader("Content-type", "application/json");
            System.out.println(postRequest);
            HttpResponse rawResponse = httpClient.execute(postRequest);
            System.out.println(rawResponse);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}