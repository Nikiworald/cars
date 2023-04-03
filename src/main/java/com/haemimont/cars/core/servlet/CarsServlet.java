package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.service.CarService;
import com.haemimont.cars.core.service.CrudService;
import com.haemimont.cars.core.sql.CarSearchStatements;
import com.haemimont.cars.core.tools.DbUtil;
import com.haemimont.cars.core.view.CarsView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

@WebServlet("/CarsServlet")
public class CarsServlet extends HttpServlet {
    CrudService crudService = new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String token = (String) req.getAttribute("token");
        HashMap<String, String> criteriaMap = new HashMap<>();
        String make = req.getParameter("make");
        String classification = req.getParameter("classification");
        String minYear = req.getParameter("minYear");
        String maxYear = req.getParameter("maxYear");
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
        criteriaMap.put("make", make);
        criteriaMap.put("classification", classification);
        criteriaMap.put("minYear", minYear);
        criteriaMap.put("maxYear", maxYear);
        criteriaMap.put("minPrice", minPrice);
        criteriaMap.put("maxPrice", maxPrice);
        CarSearchStatements statements = new CarSearchStatements();
        if (make == null && classification == null && minPrice == null
                && maxPrice == null && maxYear == null && minYear == null) {
            CarsView.viewCars(req, resp, crudService.get("all", ""));

        } else {
            CarsView.viewCars(req, resp, statements.searchCarsByMap(criteriaMap, DbUtil.getConnection()));
        }

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
