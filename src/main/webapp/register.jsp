<hr/>

<h3>Register Form</h3>
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
 <center>
<form action="registerServlet" method="post">
Name:<input type="text" name="name"  maxlength = "15"/><br/><br/>
Email:<input type="text" name="email"/><br/><br/>
Password:<input type="password" name="password" maxlength = "32"/><br/><br/>
Phone number:<input type="text" name="phoneNumber" maxlength = "11"/><br/><br/>
<input type="submit" value="register"/>
</form>
 <form action="login.jsp" method="post">
 <input type="submit" value="login"/>
 </form>
</center>