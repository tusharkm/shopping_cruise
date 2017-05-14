
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.util.List"%>
<%@page import="com.neu.edu.pojo.Product"%>
<%@page import="com.neu.edu.pojo.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>


<!DOCTYPE html>
<html>
<script>
    history.forward();
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Added in Cart</title>
</head>
<body>
<h1><b>View Final Cart</b></h1>
<form:form action="chkout.htm" commandName="login" method="post">
<table>

<tr>
                   Product in your Cart:</tr>
                 
                       
<c:forEach var="customerCart" items="${CustomerCartinprod}">
</br><tr> Product Name :</tr><tr><a >${customerCart.prodName}</a></br></tr>
<tr>Quantity :</tr> <tr><a >${customerCart.qty}</a></br></tr>
<tr>Price :</tr><tr><a >${customerCart.price}</a></br></tr>
</c:forEach>
           
          Total Sum :<a>${sum}</a></br>   
 
</tr>
<input type=submit value="Checkout">

 </form:form>  
</body>
</html>