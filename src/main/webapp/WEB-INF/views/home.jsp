<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

  <body>
    <h1>Welcome to Bittler高端黑</h1>
    <h2>HomeController's Time: ${time}</h2>
    <h4>总用户数：${bitterCount} 总推文数：${bittleCount}</h4>
    <a href="<c:url value="/bittles" />">Bittles</a> |
    <a href="<c:url value="/bittles/2333" />">BittleID=2333</a>
    <a href="<c:url value="/bitter/register" />">Bitter Register</a>
    <a href="<c:url value="/orders" />">Orders</a>
    <a href="<c:url value="/products" />">Products</a>
  </body>

