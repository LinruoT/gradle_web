<%--这是jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--这是spring form的jsp库--%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--这是spring标签--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<meta charset="UTF-8">
<html>
<body>
<security:authorize access="isAnonymous()">
    未登录，<a href="/bitter/register">注册</a> <a href="/login">登陆</a>
</security:authorize>
<security:authorize access="isAuthenticated()">
    <security:authentication property="principal.username" var="loginId"/>
    已经登陆了！！！  <a href="/logout">退出</a>
</security:authorize>
<h1>add Picture </h1>

<sf:form method="post" enctype="multipart/form-data" accept-charset="utf-8">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <label> Profile Picture:</label>
    <input type="file" name="upPicture" accept="image/jpeg,image/png,image/gif"><br><br>
    <input type="submit" value="Add" />
</sf:form>
</body>
</html>
