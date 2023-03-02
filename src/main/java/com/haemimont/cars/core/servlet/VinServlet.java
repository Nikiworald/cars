package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.model.Car;
import com.haemimont.cars.core.sql.CarStatements;
import com.haemimont.cars.core.tools.DbUtil;
import com.haemimont.cars.core.view.CarsView;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/VinServlet")
public class VinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String vin = req.getParameter("vin");
        if(vin!=null){
            CarStatements carStatements = new CarStatements();
           int id = carStatements.getIdByVin(vin, DbUtil.getConnection());
          Car car = carStatements.getCarById(id,DbUtil.getConnection());
            CarsView.viewCar(req,resp,car);

        }
        else {sendResponse(resp,"no vin");}
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
