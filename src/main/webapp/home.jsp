<hr/>
<h3>Home form </h3>
<%
String profile_msg=(String)request.getAttribute("profile_msg");
if(profile_msg!=null){
out.print(profile_msg);
}
String login_msg=(String)request.getAttribute("login_msg");
if(login_msg!=null){
out.print(login_msg);
}
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
 <br/>
<form action="CarsServlet" method="get">
<input type="submit" value="view all"/>
</form>
<form action="LogoutServlet" method="post">
<input type="submit" value="log out"/>
</form>