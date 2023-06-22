package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.apitest.integrationtests.ApiIntegrationTest;
import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.tools.ApiRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

@WebServlet("/ApiTest")
public class ApiTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
          ApiIntegrationTestResult apiIntegrationTestResult = ApiIntegrationTest.registerLoginAndAuthTest(Config.getPropertyByName("apiUrl"),jsonObject);
            apiIntegrationTestResult.getApiResults().forEach(System.out::println);
          if(apiIntegrationTestResult.isSuccessful()){
              sendResponse(resp,"nomer 1");
          }
        } else {
            sendResponse(resp,"incorrect inputs");
        }
    }
    private void sendResponse(HttpServletResponse response, String payload) {
        try {
            OutputStream out = response.getOutputStream();
            out.write(payload.getBytes());
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
    }
}