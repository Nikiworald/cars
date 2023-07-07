package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.jwttapiresult.ApiIntegrationTestResult;
import com.haemimont.cars.core.jwttapiresult.ApiResult;
import com.haemimont.cars.core.livetestupdate.LiveTests;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/LiveTestUpdateServlet")

public class LiveTestUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String testId = req.getParameter("testId");
        if (testId != null) {
            ApiIntegrationTestResult apiIntegrationTestResult = LiveTests.getLiveTest(testId);
            if (apiIntegrationTestResult == null || apiIntegrationTestResult.getApiResults() == null) {
                resp.getWriter().write("no test/s with that id");
            } else {
                for (ApiResult a : apiIntegrationTestResult.getApiResults()
                ) {
                    resp.getWriter().print("");//todo null message != no test running get the id and populate a table idk
                    if (a.getMessage() != null) {
                        resp.getWriter().write(a.getMessage());
                    } else {
                        resp.getWriter().write("no message");
                    }
                }
            }
        } else {
            sendResponse(resp, "no test id");

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
