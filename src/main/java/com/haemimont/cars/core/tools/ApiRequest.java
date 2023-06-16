package com.haemimont.cars.core.tools;
import jakarta.servlet.http.HttpServletRequest;

public class ApiRequest {
    public static boolean validate(HttpServletRequest request){
        boolean check = false;
       String name= (String) request.getParameter("name");
        String password= (String) request.getParameter("password");
        String email= (String) request.getParameter("email");
        String userType= (String) request.getParameter("userType");

       if(name!=null&&password!=null&&email!=null&&userType!=null) {
           if(name.trim().length()>0&&password.trim().length()>0&&email.trim().length()>0&&userType.trim().length()>0){
               check=true;
           }
       }
       return check;
    }
}
