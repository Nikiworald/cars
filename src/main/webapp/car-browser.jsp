<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import="com.haemimont.cars.core.model.Car"%>
 <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
  <form name="search" action=search.jsp method=get><input name="searchButton" type=submit value=search></form>
</div>
<center>
Cars<br />
<form name="add" action=addcar.jsp method=get><input name="addButton" type=submit value=add></form>
<table border="2" style=
"border-collapse: collapse;"
"overflow: hidden;"
"box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);"
>
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
  <html>
 <%
 String userName = null;
 //allow access only if session exists
 if(session.getAttribute("user") == null){
 	response.sendRedirect("login.jsp");
 }else userName = (String) session.getAttribute("user");
 String sessionID = null;
 Cookie[] cookies = request.getCookies();
 if(cookies !=null){
 for(Cookie cookie : cookies){
 	if(cookie.getName().equals("user")) userName = cookie.getValue();
 }
 }
 %>
  <body>
  <p>Click the button to display an alert box:</p>
  <button onclick="myFunction()">Try it</button>
  <script>
  function myFunction() {
  alert("I am an alert box!");
  }
  </script>
  </body>
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
         out.println("<td>");
         out.println( "<form action="+"editServlet"+" method="+"get"+">"+
                        "<input type="+"hidden"+" name="+"vin"+" value="+car.getIdentification().getVin()+" />"+
                        "<input type="+"submit"+" value=edit>"+
                        "</form>");
          out.println("</td>");
          out.println("<td>");
                   out.println( "<form action="+"DeleteServlet"+" method="+"get"+">"+
                                  "<input type="+"hidden"+" name="+"vin"+" value="+car.getIdentification().getVin()+" />"+
                                  "<input type="+"submit"+" value=delete>"+
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