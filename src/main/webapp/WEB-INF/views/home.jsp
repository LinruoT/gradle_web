<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

  <body>
    <h1>Welcome to Bittler高端黑</h1>
    <h2>HomeController's Time: ${time}</h2>
    <a href="<c:url value="/bittles" />">Bittles</a> |
    <a href="<c:url value="/bittles/2333" />">BittleID=2333</a>
    <a href="<c:url value="/bitter/register" />">Bitter Register</a>
  </body>

