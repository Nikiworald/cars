<hr/>
<h3>Car (${vin}) </h3>
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
 <br/>
<form action="home.jsp">
Height:${height}<br>
Width:${width}<br>
Length:${length}<br>
DriveLine:${driveLine}<br>
EngineType:${engineType}<br>
Hybrid:${hybrid}<br>
Number of forward gears${numberOfForwardGears}<br>
Transmission${transmission}<br>
Horsepower:${horsepower}<br>
Torque:${torque}<br>
City Mpg:${cityMpg}<br>
Fuel Type:${fuelType}<br>
Highway Mpg:${highwayMpg}<br>
Classification:${classification}<br>
Id:${id}<br>
Make:${make}<br>
ModelYear:${modelYear}<br>
Year:${year}<br>
Color:${color}<br>
Price:${price}<br>
<br>
<input type="submit" value="home" />
</form>