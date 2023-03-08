<hr/>
<form action="CarsServlet" method="get">
<input type="hidden" name="criteria" value="all"/>
<input type="submit" value="back"/>
</form>
<center>
<h3>Add form </h3>
<%
String profile_msg=(String)request.getAttribute("profile_msg");
if(profile_msg!=null){
out.print(profile_msg);
}
String login_msg=(String)request.getAttribute("login_msg");
if(login_msg!=null){
out.print(login_msg);
}
 %>
 <br/>
<form action="CarServlet" method="post">
Height:<input type="text" name="height"/> CityMpg:<input type="text" name="cityMpg"/><br/><br/>
Width:<input type="text" name="width"/> FuelType:<input type="text" name="fuelType"/><br/><br/>
Length:<input type="text" name="length"/> HighwayMpg:<input type="text" name="highwayMpg"/><br/><br/>
DriveLine:<input type="text" name="driveLine"/> Classification:<input type="text" name="classification"/><br/><br/>
EngineType:<input type="text" name="engineType"/> Id:<input type="text" name="id"/><br/><br/>
Hybrid:<input type="checkbox" name="hybrid"/> Make:<input type="text" name="make"/><br/><br/>
Number of forward gears:<input type="text" name="numberOfForwardGears"/> ModelYear:<input type="text" name="modelYear"/><br/><br/>
Transmission:<input type="text" name="transmission"/> Year:<input type="text" name="year"/><br/><br/>
Horsepower:<input type="text" name="horsepower"/> Color:<input type="text" name="color"/><br/><br/>
Torque:<input type="text" name="torque"/> Price:<input type="text" name="price"/><br/><br/>
<input type="submit" value="add"/>
</form>
</center>