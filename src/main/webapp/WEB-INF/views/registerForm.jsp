<%--这是jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--这是spring form的jsp库--%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--这是spring标签--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<meta charset="UTF-8">
<html>
  <body>
    <h1>Register </h1>
<%--这是普通的form--%>
    <%--<form method="POST">--%>
      <%--First Name: <input type="text" name="firstName" /><br/>--%>
      <%--Last Name: <input type="text" name="lastName" /><br/>--%>
      <%--Email: <input type="email" name="email" /><br/>--%>
      <%--Username: <input type="text" name="username" /><br/>--%>
      <%--Password: <input type="password" name="password" /><br/>--%>
      <%--<input type="submit" value="Register" />--%>
    <%--</form>--%>
<%--这是spring jsp库的form，填充key=bitter的属性--%>
  <sf:form method="post" commandName="bitter" enctype="multipart/form-data" accept-charset="utf-8">
    <sf:errors path="*" element="div" cssClass="errors" />
    <sf:label path="firstName"
              cssErrorClass="error">First Name</sf:label>:
    <sf:input path="firstName" cssErrorClass="error" /><br/>
    <sf:label path="lastName"
              cssErrorClass="error">Last Name</sf:label>:
    <sf:input path="lastName" cssErrorClass="error" /><br/>
    <sf:label path="email"
              cssErrorClass="error">Email</sf:label>:
    <sf:input path="email" cssErrorClass="error" /><br/>
    <sf:label path="username"
              cssErrorClass="error">Username</sf:label>:
    <sf:input path="username" cssErrorClass="error" /><br/>
    <sf:label path="password"
              cssErrorClass="error">Password</sf:label>:
    <sf:password path="password" cssErrorClass="error" /><br/>
    <label> Profile Picture:</label>
    <input type="file" name="profilePicture" accept="image/jpeg,image/png,image/gif"><br><br>
    <input type="submit" value="Register" />
  </sf:form>
  </body>
</html>
