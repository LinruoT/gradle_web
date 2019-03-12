<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
  <head>
    <title>Bitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Your Profile</h1>
    username:<c:out value="${bitter.username}" /><br/>
    <c:out value="${bitter.firstName}" /> <c:out value="${bitter.lastName}" /><br/>
    <c:out value="${bitter.email}" />

    <c:forEach var="image" items="${imageList}">
      <a href="/${image}" target="_blank"><img  src="/${image}" /></a>
    </c:forEach>

  </body>
</html>
