package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        boolean loggedIn = userService.get(name, password);
        if (loggedIn) {
            int currentTimeInHours = LocalDateTime.now().getHour();
            int currentTimeInMinutes = LocalDateTime.now().getMinute();
            int timeSum=currentTimeInHours+currentTimeInMinutes;
            int timeForExpire = timeSum+1;

            String token = name + "--"+"user"+"--"+timeForExpire;
            req.setAttribute("token",token);
            System.out.println(token);
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } else {
            sendResponse(resp, "wrong user name or password");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
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
