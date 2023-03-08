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
<form action="UpdateServlet" method="post">
Height:<input type="text" name="height" value=${height} /> CityMpg:<input type="text" name="cityMpg" value=${cityMpg} /><br/><br/>
Width:<input type="text" name="width" value=${width} /> FuelType:<input type="text" name="fuelType" value=${fuelType} /><br/><br/>
Length:<input type="text" name="length" value=${length} /> HighwayMpg:<input type="text" name="highwayMpg" value=${highwayMpg} /><br/><br/>
DriveLine:<input type="text" name="driveLine" value=${driveLine} /> Classification:<input type="text" name="classification" value=${classification} /><br/><br/>
EngineType:<input type="text" name="engineType" value=${engineType} /> Id:<input type="text" name="id" value=${id} /><br/><br/>
Hybrid:<input type="checkbox" name="hybrid" value=${hybrid} /> Make:<input type="text" name="make" value=${make} /><br/><br/>
Number of forward gears:<input type="text" name="numberOfForwardGears" value=${numberOfForwardGears} /> ModelYear:<input type="text" name="modelYear" value=${modelYear} /><br/><br/>
Transmission:<input type="text" name="transmission" value=${transmission} /> Year:<input type="text" name="year" value=${year} /><br/><br/>
Horsepower:<input type="text" name="horsepower" value=${horsepower} /> Color:<input type="text" name="color" value=${color} /><br/><br/>
Torque:<input type="text" name="torque" value=${torque} /> Price:<input type="text" name="price" value=${price} /><br/><br/>
<input type="submit" value="add"/>
</form>
</center>