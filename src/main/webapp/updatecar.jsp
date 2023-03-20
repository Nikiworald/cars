<hr/>
<form action="CarsServlet" method="get">
<input type="hidden" name="criteria" value="all"/>
<input type="submit" value="back"/>
</form>
<center>
<h3>Update form </h3>
<%

 %>
 <br/>
<table border ="1" >
<tbody>
<form action="editServlet" method="post">
<input type="hidden" name ="vin" value=${vin}>
<tr>
<td>Height:<input type="text" name="height" value=${height}> </td><td>CityMpg:<input type="text" name="cityMpg"value=${cityMpg}><br/><br/></td>
</tr>
<tr>
<td>Width:<input type="text" name="width"value=${width}> </td><td>FuelType:<input type="text" name="fuelType"value=${fuelType}><br/><br/></td>
</tr>
<tr>
<td>Length:<input type="text" name="length"value=${length}> </td><td>HighwayMpg:<input type="text" name="highwayMpg"value=${highwayMpg}><br/><br/></td>
</tr>
<tr>
<td>DriveLine:<input type="text" name="driveLine"value=${driveLine}> </td><td>Classification:<input type="text" name="classification"value=${classification}><br/><br/></td>
</tr>
<tr>
<td>EngineType:<input type="text" name="engineType"value=${engineType}> </td><td>Id:<input type="text" name="id"value=${id}><br/><br/></td>
</tr>
<tr>
<td>Hybrid:<input type="checkbox" name="hybrid" checked=&{hybridCheckBox}> </td><td>Make:<input type="text" name="make"value=${make}><br/><br/></td>
</tr>
<tr>
<td>Number of forward gears:<input type="text" name="numberOfForwardGears"/value=${numberOfForwardGears} </td><td>ModelYear:<input type="text" name="modelYear"value=${modelYear}><br/><br/></td>
</tr>
<tr>
<td>Transmission:<input type="text" name="transmission"value=${transmission}> </td><td>Year:<input type="text" name="year"value=${year}><br/><br/></td>
</tr>
<tr>
<td>Horsepower:<input type="text" name="horsepower"value=${horsepower}> </td><td>Color:<input type="text" name="color"value=${color}><br/><br/></td>
</tr>
<tr>
<td>Torque:<input type="text" name="torque"value=${torque}> </td><td>Price:<input type="text" name="price"value=${price}><br/><br/></td>
</tr>
<tr>
<td><input type="submit" value="update"/></td>
</tr>
</form>
</tbody>
</table>
</center>