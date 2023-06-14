<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import = "com.haemimont.cars.core.model.Joke"%>
<%@ page import = "com.haemimont.cars.core.logger.CustomLogger"%>
<%@ page import = "com.haemimont.cars.core.tools.FromJsonToJokes"%>
<%@ page import = "com.haemimont.cars.core.jokesapi.JokesApi"%>

<center>
<h3>Jokes</h3>
<%//user validation
String userName = null;
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
 String jsonJoke = JokesApi.getOneJoke();
String stringJoke = FromJsonToJokes.convert(jsonJoke);
request.setAttribute("joke", stringJoke);
 %>
 <div id ="mainDiv" >hello</div>
 <script type="text/javascript" src="test.js"></script>
  <input type="submit" value="get 10 random jokes" onclick="op('mainDiv','${joke}');" />
 </center>