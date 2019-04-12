<%--
  Created by IntelliJ IDEA.
  User: billy
  Date: 2019-04-12
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>

<body>
<security:authorize access="isAnonymous()">
    未登录，<a href="/bitter/register">注册</a> <a href="/login">登陆</a>
</security:authorize>
<security:authorize access="isAuthenticated()">
    <security:authentication property="principal.username" var="loginId"/>
    已经登陆了！！！  <a href="/logout">退出</a>
</security:authorize>
<security:authorize access="hasRole('ROLE_BITTER')">
    <div class="bittle">
        <h1>Bit it out...</h1>
        <h3>Hello ${loginId}!</h3>
        <form method="POST" name="bittleForm" accept-charset="UTF-8">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="latitude">
            <input type="hidden" name="longitude">
            <textarea name="message" cols="80" rows="5"></textarea><br/>
            <input type="submit" value="Add" />
        </form>
    </div>
</security:authorize>
<security:authorize access="isAuthenticated() and principal.username=='billy'">
    <div>
        只有username是billy才显示的：高端黑比利登陆啦！
        <a href="/bittles/1">bittleId=1</a>
    </div>
</security:authorize>

</body>
</html>