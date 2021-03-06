<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
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
        <title>Create/View Product</title>
    </head>
    <body>
      
    
<form:form action="addproduct.htm" commandName="product" method="post" >

<table>

<tr>
                    <td>Select Category:</td>
                    <td>
                        <form:select path="categoryName">
                            <c:forEach var="categ" items="${categories}">
                                <form:option value="${categ.categoryName}"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>

<tr>
    <td>Product Name:</td>
    <td><form:input path="productName" size="30" /> <font color="red"><form:errors path="productName"/></font></td>
</tr>

<tr>
    <td>Product Description:</td>
    <td><form:input path="productDescription" size="30" /> <font color="red"><form:errors path="productDescription"/></font></td>
</tr>

<tr>
    <td>Select photo (Max size: 5 MB)</td>
    <td><form:input path="productImage"  size="30" /> <font color="red"><form:errors path="productImage"/></font></td>
</tr>

<tr>
    <td>Select photoOrginal (Max size: 5 MB)</td>
    <td><form:input path="photo"  size="30" /> <font color="red"><form:errors path="photo"/></font></td>
</tr>

<tr>
    <td colspan="2"><input type="submit" value="Create Product" /></td>
</tr>
</table>

</form:form>

</body>
</html>
