<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import = "com.haemimont.cars.core.model.Joke"%>
<%@ page import = "com.haemimont.cars.core.loger.CustomLogger"%>
<%@ page import="java.io.IOException"%>
<center>
<h3>Jokes</h3>
<%!private void requestJokesFromServlet(HttpServletRequest request,HttpServletResponse response)   {
   RequestDispatcher dispatcher =
      getServletContext().getRequestDispatcher("/jokeServlet");
 try {
     dispatcher.forward(request, response);
   } catch (ServletException e) {
     CustomLogger.logError("something went wrong:"+e);
   }catch (IOException e){
     CustomLogger.logError("something went wrong:"+e);
   }
 }
%>
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
 String error = (String) request.getAttribute("error");
 if(error==null){
        if(jokes==null){
            requestJokesFromServlet(request,response);
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
 }
else{out.println("something went wrong:"+error);}
 %>
 <form action="jokes.jsp" method="get">
  <input type="submit" value="get 10 random jokes"/>
 </center>