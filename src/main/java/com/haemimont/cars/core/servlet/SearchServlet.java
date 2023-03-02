package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.service.CarService;
import com.haemimont.cars.core.service.CrudService;
import com.haemimont.cars.core.view.CarsView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    CrudService crudService = new CarService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
String criteria = req.getParameter("criteria");
String value = req.getParameter("value");
String maxValue = req.getParameter("maxValue");
if(criteria.equals("")&&criteria!=null){
    CarsView.viewCars(req,resp,crudService.get("all",""));

}
if(criteria.equals("price")){
    String combined = value+"--"+maxValue;
    CarsView.viewCars(req,resp,crudService.get(criteria,combined));
}
else{
    CarsView.viewCars(req,resp,crudService.get(criteria,value));}

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
