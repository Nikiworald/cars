<hr/>
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
<form action="CarsServlet" method="get">
<input type="hidden" name="criteria" value="all"/>
<input type="submit" value="back"/>
</form>
<center>
<h3>Search form </h3>
 <br/>
<form action="CarsServlet" method="get">
Model:<input type="text" name="make" /><br/><br/>
Classification:<input type="text" name="classification"/> <br/><br/>
Year:<input type="text" name="minYear" />-<input type="text" name="maxYear" /> <br/><br/>
Price:<input type="text" name="minPrice"  />-<input type="text" name="maxPrice" /> <br/><br/>
<input type="submit" value="search"/>
</form>

</center>