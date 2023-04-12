package com.haemimont.cars.core.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    //    CrudService crudService = new CarService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {//search is done by carsServlet
//        String make = req.getParameter("make").trim();
//        String classification = req.getParameter("classification").trim();
//        String minYear = req.getParameter("minYear").trim();
//        String maxYear = req.getParameter("maxYear").trim();
//        String minPrice = req.getParameter("minPrice").trim();
//        String maxPrice = req.getParameter("maxPrice").trim();
//        HashMap<String, String> criteriaMap = new HashMap<>();
//        criteriaMap.put("make", make);
//        criteriaMap.put("classification", classification);
//        criteriaMap.put("minYear", minYear);
//        criteriaMap.put("maxYear", maxYear);
//        criteriaMap.put("minPrice", minPrice);
//        criteriaMap.put("maxPrice", maxPrice);
//        CarSearchStatements statements = new CarSearchStatements();
//        statements.searchCarsByMap(criteriaMap, DbUtil.getConnection());
//        CarsView.viewCars(req, resp, statements.searchCarsByMap(criteriaMap, DbUtil.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
