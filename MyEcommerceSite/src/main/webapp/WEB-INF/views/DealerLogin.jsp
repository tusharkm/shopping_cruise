<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.neu.edu.pojo.Dealer"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dealer Role</title>
    </head>
    <body>
    <%Dealer dealer=(Dealer)request.getAttribute("dealer");
    String dealer1=dealer.getFirstName();
    long m=dealer.getPersonID();
    session.setAttribute("dealer", dealer);
    %>
    
<h4>Welcome <jsp:expression>m</jsp:expression>!!</h4>
        Hello ${sessionScope.DealerName}</br>
        <% %>
        <a href="addproduct.htm" >Create Product</a><br>
        
    </body>
</html>