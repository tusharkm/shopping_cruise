<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.util.List"%>
<%@page import="com.neu.edu.pojo.Customer"%>
<%@page import="com.neu.edu.pojo.Product"%>
<%@page import="java.util.ArrayList"%>
<%
//get the category list
        com.neu.edu.dao.CatDAO categorydao = new com.neu.edu.dao.CatDAO();
        java.util.List categoryList = categorydao.list();
        pageContext.setAttribute("categories", categoryList);
%>
<!DOCTYPE html>
<html>
   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
       <meta name="viewport" content="width=device-width, initial-scale=1">
       
       <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
       
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
       
       <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
       
       <title>View Product</title>
   </head>
   <body>
     <div class="container-fluid">

           <div class="row">

               <div align="left" class="col-sm-2">
               
   <!-- display for view -->
<form:form action="ViewCat.htm.htm" commandName="product" method="post" >

<table>

<tr>
                   <td>Product Category:</td></tr>
                   <tr><td>
                       <form:label path="categoryName">
                           <c:forEach var="categ" items="${categories}">
                           <% /*   <form:option value="${categ.categoryName}"/> */ %>
 <a href="ViewProd.htm?catName=${categ.categoryName}">${categ.categoryName}</a></br>
                           </c:forEach>
                       </form:label>
                   </td></tr>
               
               
               <tr>
   <td colspan="2">
   <!--  <input type="submit" value="View Cart" />-->
 
   </td>
</tr>

</table>

</form:form>



<!-- display for add -->
<form action="ViewProdInCart.htm" method="GET">

  <button type="submit" >View Cart</button>
</form>


</div>
                <div class="col-sm-10">

                    
                <form action="selectProd.htm" method="get">
                
                <c:forEach items="${prodSet}" var="proditem">
                          <c:if test="${proditem.qty>0}">
                      </br>
<input type="checkbox" name="prodId"  value=<c:out value="${proditem.productId}"></c:out> />

</br><tr> Product Name :</tr>${proditem.productName}</br>

<%-- ${proditem.productImage}</br> --%>

<tr>Description :</tr>${proditem.productDescription}</br> 

<tr>Quantity :</tr> ${proditem.qty}</br>

<tr>Price :</tr>${proditem.price}</br>

 <input type="number" name=<c:out value="${proditem.productId}"/> value="0" max="${proditem.qty}" min=0>>> Enter Required Qty;
           </c:if>
            </c:forEach>  	
                   <%  if(request.getAttribute("customer")!=null){   
                Customer customer = (Customer)request.getAttribute("customer");
       long customerId = customer.getPersonID();
       session.setAttribute("customerId",customerId );} %>  <!-- id sent -->
                   
                    </br></br><button id="merge_button" type="submit" >Add to Cart</button>
                    
                    </form>
                </div>
               
           </div>
       </div>

</body>
</html>