<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import = "com.haemimont.cars.core.model.Joke"%>
<%@ page import = "com.haemimont.cars.core.loger.CustomLogger"%>
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
 ArrayList<Joke> jokes = (ArrayList<Joke>) request.getAttribute("jokes");
        if(jokes==null){
            out.println("something went wrong");
            }else{
            int i =1;
            for(Joke joke:jokes){
            out.println(i);
            i++;
            out.println("<br>");
            out.println(joke.getSetUp());
            out.println("<br>");
            out.println(joke.getPunchLine());
            out.println("<br>");
           }
          }

 %>
 <form action="jokeServlet" method="get">
  <input type="submit" value="get 10 random jokes"/>
 </center>