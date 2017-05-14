<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login</h2>

<%-- <c:choose>
            <c:when test="${!empty sessionScope.CustomerName}">
            <%System.out.println("CustomerScope"); %>
                <c:redirect url="login.htm?action=login"/>                    
            </c:when>
            <c:when test="${!empty sessionScope.DealerName}">
               <%System.out.println("DealerScope"); %>
            <c:redirect url="login.htm?action=login" />
             <!--    <c:redirect url="Login.htm?action=login"></c:redirect>   -->                  
            </c:when>
            <c:otherwise> --%>

<form:form action="login.htm" commandName="login" method="post">

<table>


<tr>
    <td>User Name:</td>
    <td><form:input path="name" size="30"  required="required"/> <font color="red"><form:errors path="name"/></font></td>
</tr>

<tr>
    <td>Password:</td>
    <td><form:password path="password" size="30"  required="required" /> <font color="red"><form:errors path="password"/></font></td>
</tr>


<tr>
    <td colspan="2"><input type="submit" value="Submit" /></td>
</tr>
</table>

 </form:form>                  
      <%--       </c:otherwise>
        </c:choose> --%>

</body>

</html>
