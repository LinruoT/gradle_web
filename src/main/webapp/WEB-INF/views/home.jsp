<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

  <body>
  <security:authorize access="isAnonymous()">
    未登录，<a href="/bitter/register">注册</a>     <a href="/login">登陆</a>
  </security:authorize>
  <security:authorize access="isAuthenticated()">
    <security:authentication property="principal.username" var="loginId"/>
    <h3>Hello
      <a href="/bitter/${loginId}">${loginId}!</a>
    </h3>
    <a href="/logout">退出</a>
  </security:authorize>
    <h1>Welcome to Bittler高端黑</h1>
    <h2>HomeController's Time: ${time}</h2>
    <h4>总用户数：${bitterCount} 总推文数：${bittleCount}</h4>
    <a href="<c:url value="/bittles" />">Bittles</a> |
    <a href="<c:url value="/bittles/23" />">BittleID=23</a>
    <a href="<c:url value="/bitter/register" />">Bitter Register</a>
    <a href="<c:url value="/orders" />">Orders</a>
    <a href="<c:url value="/products" />">Products</a>
    <a href="<c:url value="/products" />">Products</a>
    <a href="<c:url value="/homestomp" />">test STOMP webSocket</a>
    <a href="<c:url value="/bittles/testapi/130" />">BittleID=130(test api)</a>
    <a href="<c:url value="/test/mybatis" />">test myBatis</a>
  </body>

