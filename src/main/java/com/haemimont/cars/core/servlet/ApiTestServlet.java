package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.tools.ApiRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

    public String postRequest(URL url, JSONObject jsonObject) throws IOException {
        HttpPost httpPost = new HttpPost(url.toString());
        HttpClient httpClient = HttpClientBuilder.create().build();
        StringEntity a = new StringEntity(jsonObject.toString());
        a.setContentType("application/json");
//        httpRequest.addHeader("content-type", "application/json");
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("Accept", "application/json");
        httpPost.setEntity(a);
        try (CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost)) {
            StatusLine statusLine = response.getStatusLine();
//            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
            //            System.out.println("Response body: " + responseBody);
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "error" + e;
        }
    }
}