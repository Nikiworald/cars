<hr/>

<h3>Login Form</h3>
<%
String profile_msg=(String)request.getAttribute("profile_msg");
if(profile_msg!=null){
out.print(profile_msg);
}
String login_msg=(String)request.getAttribute("login_msg");
login_msg = "AAAAAAA";
if(login_msg!=null){
out.print(login_msg);
}
 %>
 <br/>
 <center>
<form action="registerServlet" method="post">
Name:<input type="text" name="name"/><br/><br/>
Email:<input type="text" name="email"/><br/><br/>
Password:<input type="password" name="password"/><br/><br/>
<input type="submit"/>
</form>
</center>