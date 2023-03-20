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
 <table border ="1" >
<tbody>
<form action="CarServlet" method="post">
<tr>
<td>Height:<input type="text" name="height" maxlength="3"/> </td><td>CityMpg:<input type="text" name="cityMpg" maxlength="3"/><br/><br/></td>
</tr>
<tr>
<td>Width:<input type="text" name="width" maxlength="3"/> </td><td>FuelType:<input type="text" name="fuelType" maxlength="50"/><br/><br/></td>
</tr>
<tr>
<td>Length:<input type="text" name="length" maxlength="3"/> </td><td>HighwayMpg:<input type="text" name="highwayMpg" maxlength="3"/><br/><br/></td>
</tr>
<tr>
<td>DriveLine:<input type="text" name="driveLine" maxlength="50"/> </td><td>Classification:<input type="text" name="classification" maxlength="50"/><br/><br/></td>
</tr>
<tr>
<td>EngineType:<input type="text" name="engineType" maxlength="50"/> </td><td>Id:<input type="text" name="id" maxlength="50"/><br/><br/></td>
</tr>
<tr>
<td>Hybrid:<input type="checkbox" name="hybrid"/> </td><td>Make:<input type="text" name="make" maxlength="50"/><br/><br/></td>
</tr>
<tr>
<td>Number of forward gears:<input type="text" name="numberOfForwardGears" maxlength="2"/> </td><td>ModelYear:<input type="text" name="modelYear" maxlength="50"/><br/><br/></td>
</tr>
<tr>
<td>Transmission:<input type="text" name="transmission" maxlength="50"/> </td><td>Year:<input type="text" name="year" maxlength="4"/><br/><br/></td>
</tr>
<tr>
<td>Horsepower:<input type="text" name="horsepower" maxlength="4"/> </td><td>Color:<input type="text" name="color" maxlength="50"/><br/><br/></td>
</tr>
<tr>
<td>Torque:<input type="text" name="torque" maxlength="4"/> </td><td>Price:<input type="text" name="price" maxlength="10"/><br/><br/></td>
</tr>
<tr>
<td><input type="submit" value="add"/></td>
</tr>
</form>
</tbody>
</table>
</center>