<%--
  Created by IntelliJ IDEA.
  User: home
  Date: 2019/4/4
  Time: 0:40
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
    未登录，<a href="/bitter/register">注册</a>
</security:authorize>
<security:authorize access="isAuthenticated()">
    <security:authentication property="principal.username" var="loginId"/>
    已经登陆了！！！
</security:authorize>

<div class="listTitle">
    <h1>Orders</h1>
    <ul class="bittleList">
        <c:forEach items="${orderList}" var="order" >
            <li id="order_<c:out value="order.id"/>">
                <div>
                    <span class="orderCustomer"><c:out value="${order.customer}" /></span>
                    <span class="orderType">(<c:out value="${order.type}" />)</span>
                </div>
                <div class="orderId" style="font-size:12px"><c:out value="${order.id}" /></div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
