<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2019/1/20
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>

<body>

<div class="listTitle">
    <h1>一个Bittle</h1>
    <ul class="bittleList">
        <li id="bittle_<c:out value="bittle.id"/>">
            <div class="bittleMessage"><c:out value="${bittle.message}" /></div>
            <div>
                <span class="bittleId"><c:out value="${bittle.id}" /></span>
                <span class="bittleTime"><c:out value="${bittle.time}" /></span>
                <span class="bittleLocation">(<c:out value="${bittle.latitude}" />, <c:out value="${bittle.longitude}" />)</span>
                <span class="bittleDelete">
                        <a href="/bittles/forcedel/<c:out value="${bittle.id}" />">永久删除</a>
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
    </ul>
</div>
</body>
</html>
