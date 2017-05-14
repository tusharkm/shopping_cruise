<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
   </head>
   <body>
       <h1>Login</h1>
       <form action="j_security_check" method="post">
           Username : <input type="text" name="j_username"><br/>
           Password : <input type="password" name="j_password"><br/>
           <input type="submit" value="Login"><br/>
           <input type="hidden" name="action" value="login">
       </form>
   </body>
</html>