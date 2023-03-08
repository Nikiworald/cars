<hr/>
<form action="CarsServlet" method="get">
<input type="hidden" name="criteria" value="all"/>
<input type="submit" value="back"/>
</form>
<center>
<h3>Search form </h3>
 <br/>
<form action="SearchServlet" method="get">
Model:<input type="text" name="make" value="BMW"/><br/><br/>
Classification:<input type="text" name="classification"value="Manual transmission"/> <br/><br/>
Year:<input type="text" name="minYear" value=1999 />-<input type="text" name="maxYear"value=2022 /> <br/><br/>
Price:<input type="text" name="minPrice" value=0 />-<input type="text" name="maxPrice"value=10000 /> <br/><br/>
<input type="submit" value="search"/>
</form>

</center>