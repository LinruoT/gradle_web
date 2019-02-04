<%--这是jstl--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--这是spring的jsp库--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<%@ page session="false" %>
<html>
  <head>
    <title>Bitter</title>
    <link rel="stylesheet" type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Register</h1>
<%--这是普通的form--%>
    <%--<form method="POST">--%>
      <%--First Name: <input type="text" name="firstName" /><br/>--%>
      <%--Last Name: <input type="text" name="lastName" /><br/>--%>
      <%--Email: <input type="email" name="email" /><br/>--%>
      <%--Username: <input type="text" name="username" /><br/>--%>
      <%--Password: <input type="password" name="password" /><br/>--%>
      <%--<input type="submit" value="Register" />--%>
    <%--</form>--%>
<%--这是spring jsp库的form--%>
  <sf:form method="post" commandName="bitter">
    First Name: <sf:input path="firstName" /><br/>
    Last Name: <sf:input path="lastName" /><br/>
    Email: <sf:input path="email" /><br/>
    Username: <sf:input path="username" /><br/>
    Password: <sf:password path="password" /><br/>
    <input type="submit" value="Register" />
  </sf:form>
  </body>
</html>
