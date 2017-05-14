<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Add Dealer Form</title>
</head>
<body>

<h2>Register a New Dealer</h2>

<form:form action="DealerAdded.htm" commandName="dealer" method="post">

<table>
<tr>
    <td>First Name:</td>
    <td><form:input path="firstName" size="30"  required="required"/> <font color="red"><form:errors path="firstName"/></font></td>
</tr>

<tr>
    <td>Last Name:</td>
    <td><form:input path="lastName" size="30"  required="required"/> <font color="red"><form:errors path="lastName"/></font></td>
</tr>


<tr>
    <td>User Name:</td>
    <td><form:input path="name" size="30"  required="required" /> <font color="red"><form:errors path="name"/></font></td>
</tr>

<tr>
    <td>Password:</td>
    <td><form:password path="password" size="30"  required="required"/> <font color="red"><form:errors path="password"/></font></td>
</tr>

 <tr>
    <td>Email Id:</td>
    <td><form:input type = "email"  path="email" size="30"  required="required"/> <font color="red"><form:errors path="email"/></font></td>
</tr> 
 <tr>
    <td>Cell:</td>
    <td><form:input type="number"  path="cell" size="30" required="required"/> <font color="red"><form:errors path="cell"/></font></td>
</tr> 

 <tr>
    <td>Address:</td>
    <td><form:input path="address" size="30" /> <font color="red"><form:errors path="address"/></font></td>
</tr> 

 <tr>
    <td>Company:</td>
    <td><form:input path="company" size="30" /> <font color="red"><form:errors path="company"/></font></td>
</tr> 

<tr>
    <td colspan="2"><input type="submit" value="Create Dealer" /></td>
</tr>
</table>

</form:form>

</body>
</html>