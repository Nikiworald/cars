package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.apitest.integrationtests.RealTimeUpdateMultiThreadApiIntegrationTest;
import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.livetestupdate.LiveTests;
import com.haemimont.cars.core.tools.ApiCredentials;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet("/ApiTest")
public class ApiTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (ApiCredentials.validate(req)) {
            int id = ThreadLocalRandom.current().nextInt(0, 9999);
            while (true) {
                try {
                    LiveTests.addLiveTest(String.valueOf(id), ApiIntegrationTestResult.generateDefaultResult());
                    break;
                } catch (IllegalArgumentException e) {
                    id = ThreadLocalRandom.current().nextInt(0, 9999);
                }
            }
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
            RealTimeUpdateMultiThreadApiIntegrationTest.registerLoginAndAuthTest(String.valueOf(id), Config.getPropertyByName("apiUrl"), jsonObject);
            req.setAttribute("tId", String.valueOf(id));
            req.getRequestDispatcher("apitestmanager.jsp").forward(req, resp);
        } else {
            sendResponse(resp, "incorrect inputs");
        }
    }

    private void sendResponse(HttpServletResponse response, @SuppressWarnings("SameParameterValue") String payload) {
        try {
            OutputStream out = response.getOutputStream();
            out.write(payload.getBytes());
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
    }
}