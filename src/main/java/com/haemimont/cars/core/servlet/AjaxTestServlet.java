package com.haemimont.cars.core.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AjaxTestServlet")
public class AjaxTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dobre");
        if (req.getParameter("test") != null) {
            resp.getWriter().write("op");
            System.out.println("op");
        } else {
            resp.getWriter().write("aaa");
            System.out.println("aaa");
        }

    }

}
