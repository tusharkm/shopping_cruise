<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add Category Form</title>
</head>
<body>

<h2>Add a New Category</h2>

<form:form action="CatAdd.htm" commandName="category" method="post">

<table>
<tr>
    <td>Category Name:</td>
    <td><form:input path="categoryName" size="30" /> <font color="red"> <form:errors path="categoryName"/></font></td>
</tr>


<tr>
    <td colspan="2"><input type="submit" value="Create Category" /></td>
</tr>
</table>

</form:form>

</body>
</html>