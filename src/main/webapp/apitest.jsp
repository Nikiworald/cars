<%@ page import = "com.haemimont.cars.core.jwttapiresult.ApiResult"%>
<%@ page import ="java.util.List"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<center>
<form action="ApiTest" method="post">
<table border=1>
<tbody>
<tr>API test<td>name</td><td><input type="text" name="name" maxlength="12"></input></td></tr>
<tr><td>e-mail(@ needed)</td><td><input type="text" name="email" maxlength="24"></input></td></tr>
<tr><td>password</td><td><input type="password" name="password" maxlength="16"></input></td></tr>
<tr><td>user type(user,admin,mod)</td><td><input type="text" name="userType" maxlength="15"></input></td></tr>
</tbody>
</table>
<input type="submit" value="test"/>
</form>
</center>