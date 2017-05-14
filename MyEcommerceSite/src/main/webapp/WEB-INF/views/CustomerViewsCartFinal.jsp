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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Product in MyCart</title>
</head>
<body>
<h1><b>View Cart</b></h1>

<table>

<tr>
                   <td>Product in your Cart:</td></tr>
                   <tr><td>
                       
                           <c:forEach var="customerCart" items="${CustomerCartlist}">
                           
</br><tr> Product Name :</tr><tr><a >${customerCart.prodName}</a></br>
<tr>Quantity :</tr><a >${customerCart.qty}</a></br>
  
                           </c:forEach>
                      
                   </td></tr>


</body>
</html>