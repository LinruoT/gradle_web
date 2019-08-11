<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2019/1/18
  Time: 0:13
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
        <h3>Hello
            <a href="/bitter/${loginId}">${loginId}!</a>
        </h3>
        <form method="POST" name="bittleForm" enctype="multipart/form-data" accept-charset="UTF-8">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="latitude">
            <input type="hidden" name="longitude">
            <textarea name="message" cols="80" rows="5"></textarea><br/>
            <p>选择文件:<input type="file" name="files" accept="image/jpeg,image/png,image/gif"></p>
            <p>选择文件:<input type="file" name="files" accept="image/jpeg,image/png,image/gif"></p>
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

<div class="listTitle">
    <h1>Recent Bittles</h1>
    <ul class="bittleList">
        <c:forEach items="${bittleList}" var="bittle" >
            <li id="bittle_<c:out value="bittle.id"/>">
                <div class="bittleMessage"><c:out value="${bittle.message}" /></div>
                <div>
                    <span class="bittleEnter">
                        <a href="/bittles/<c:out value="${bittle.id}" />">${bittle.id} </a>
                    </span>
                    <span class="bitterName"><c:out value="${bittle.bitter.firstName} ${bittle.bitter.lastName}" /></span>
                    <span class="bittleTime"><c:out value="${bittle.time}" /></span>
                    <span class="bittleLocation">(<c:out value="${bittle.latitude}" />, <c:out value="${bittle.longitude}" />)</span>
                    <span class="bittleDelete">
                        <a href="/bittles/forcedel/<c:out value="${bittle.id}" />">永久删除</a>
                    </span>
                    <span class="bittleComment">
                            评论数量：${bittle.comments.size()}
                    </span>
                </div>
                <div>
                    <c:forEach var="picture" items="${bittle.pictures}">
                        <a href="http://vm.linruotian.com:9000/bitter-dev-img/${picture.name}" target="_blank">
                            <img src="http://vm.linruotian.com:9000/bitter-dev-img/${picture.name}" style="
                              width: 20%;
                              height: auto;"/>
                        </a>
                    </c:forEach>
                </div>
                <br/>
            </li>
        </c:forEach>
    </ul>
    <c:if test="${fn:length(bittleList) gt 20}">
        <hr />
        <s:url value="/bittles?count=${nextCount}" var="more_url" />
        <a href="${more_url}">Show more</a>
    </c:if>
</div>
</body>
</html>
