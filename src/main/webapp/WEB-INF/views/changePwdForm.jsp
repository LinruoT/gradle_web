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

    <h1>修改密码 </h1>

    <sf:form method="post" accept-charset="utf-8">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<P>输入密码： <input type="password" name="pwd" id="pwd"/></P>
<p>确认密码： <input type="password" name="pw" id="pw2" onkeyup="validate()"/><span id="tishi"></span></p>
<p><input type="submit" value="修改密码" id="submit"/>
    </sf:form>
    <script>
        function validate() {
            var pw1 = document.getElementById("pwd").value;
            var pw2 = document.getElementById("pw2").value;
            if(pw1 == pw2) {
                document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
                document.getElementById("submit").disabled = false;
            }
            else {
                document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
                document.getElementById("submit").disabled = true;
            }
        }
    </script>
</security:authorize>
</body>
</html>
