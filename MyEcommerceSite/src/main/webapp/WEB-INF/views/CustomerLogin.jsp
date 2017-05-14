<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.neu.edu.pojo.Customer"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Supplier Role</title>
    </head>
    <body>
 <%Customer customer=(Customer)request.getAttribute("customer");
    String customer1=customer.getFirstName();
    long m=customer.getPersonID();
    session.setAttribute("customer", customer);
    %>
    
               Hello ${sessionScope.CustomerName}
        
        <a href="ViewCat.htm" >BUY PRODUCTS</a><br>
           
    </body>
</html>