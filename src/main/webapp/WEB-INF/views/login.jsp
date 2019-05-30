<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: home
  Date: 2019/4/8
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<a href="<c:url value="/bitter/register" />">注册</a>
<form name='f' action='<c:url value="/login" />' method='POST'>
    <table>
        <tr><td>User:</td><td>
            <input type='text' name='username' value='' /></td></tr>
        <tr><td>Password:</td>
            <td><input type='password' name='password'/></td></tr>
        <tr><td colspan='2'>
            <input id="remember_me" name="remember-me" type="checkbox"/>
            <label for="remember_me" class="inline">Remember me</label></td></tr>
        <tr><td colspan='2'>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <input name="submit" type="submit" value="Login"/></td></tr>
    </table>
</form>

</body>
</html>
