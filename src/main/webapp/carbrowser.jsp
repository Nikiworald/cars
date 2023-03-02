<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import="com.haemimont.cars.core.model.Car"%>
<center>
Cars
<form action=search.jsp method=get><input type=submit value=search>
</form>

<table border ="1"  >
  <thead>
    <tr>
      <th>Vin</th>
      <th>Model</th>
      <th>Model year</th>
      <th>Classification</th>
      <th>Fuel type</th>
      <th>Transmission</th>
      <th>DriveLine</th>
      <th>Engine type</th>
      <th>Price</th>
    </tr>
  </thead>
  <tbody>
<%
        ArrayList<Object> cars = (ArrayList<Object>) request.getAttribute("cars");
try {
    for (Object object : cars) {
        Car car = (Car) object;
        out.println("<tr>");
        out.println("<td>" + car.getIdentification().getVin() + "</td>");
        out.println("<td>" + car.getIdentification().getMake() + "</td>");
        out.println("<td>" + car.getIdentification().getModelYear() + "</td>");
        out.println("<td>" + car.getIdentification().getClassification() + "</td>");
        out.println("<td>" + car.getFuelInformation().getFuelType() + "</td>");
        out.println("<td>" + car.getEngineInformation().getTransmission() + "</td>");
        out.println("<td>" + car.getEngineInformation().getDriveLine() + "</td>");
        out.println("<td>" + car.getEngineInformation().getEngineType() + "</td>");
        out.println("<td>" + car.getIdentification().getPrice() + "</td>");
        out.println("<td>");
        out.println( "<form action="+"VinServlet"+" method="+"get"+">"+
                "<input type="+"hidden"+" name="+"vin"+" value="+car.getIdentification().getVin()+" />"+
                "<input type="+"submit"+" value=view>"+
                "</form>");
        out.println("</td>");
        out.println("</tr>");
    }
}catch (Exception e){
out.println("no cars found("+e.toString()+")");
}
%>
  </tbody>

</table>
</center>