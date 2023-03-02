<hr/>

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
Width:<input type="text" name="width"/> FuelType:<input type="text" name="fuelType"/><br/><br/><br/><br/>
Length:<input type="text" name="length"/> HighwayMpg:<input type="text" name="highwayMpg"/><br/><br/><br/><br/>
DriveLine:<input type="text" name="driveLine"/> Classification:<input type="text" name="classification"/><br/><br/><br/><br/>
EngineType:<input type="text" name="engineType"/> Id:<input type="text" name="id"/><br/><br/><br/><br/>
Hybrid:<input type="checkbox" name="hybrid"/> Make:<input type="text" name="make"/><br/><br/><br/><br/>
Number of forward gears:<input type="text" name="numberOfForwardGears"/> ModelYear:<input type="text" name="modelYear"/><br/><br/><br/><br/>
Transmission:<input type="text" name="transmission"/> Year:<input type="text" name="year"/><br/><br/><br/><br/>
Horsepower:<input type="text" name="horsepower"/> Color:<input type="text" name="color"/><br/><br/><br/><br/>
Torque:<input type="text" name="torque"/> Price:<input type="text" name="price"/><br/><br/><br/><br/>

<input type="submit" value="add"/>
</form>