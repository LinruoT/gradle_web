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
    <h1>Bittle详情</h1>    <a href="javascript:history.go(-1);">返回</a>
    <ul class="bittleList">
        <li id="bittle_<c:out value="bittle.id"/>">
            <div class="bittleMessage"><c:out value="${bittle.message}" /></div>
            <div>
                <span class="bittleId"><c:out value="${bittle.id}" /></span>
                <span class="bitterName"><c:out value="${bittle.bitter.firstName} ${bittle.bitter.lastName}" /></span>
                <span class="bittleTime"><c:out value="${bittle.time}" /></span>
                <span class="bittleLocation">(<c:out value="${bittle.latitude}" />, <c:out value="${bittle.longitude}" />)</span>
                <span class="bittleDelete">
                    <a href="/bittles/forcedel/<c:out value="${bittle.id}" />">永久删除</a>
                </span>
            </div>
            <div>
                <input type="text" id="href"> <button onclick="local()">添加评论</button>
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
            <div>
                <c:forEach var="comment" items="${bittle.comments}">
                    <div>
                        <span>${comment.bitter.firstName} ${comment.bitter.lastName}：${comment.content} </span>
                        <span>评论时间：${comment.time}</span>
                    </div>
                </c:forEach>
            </div>
            <br/>
        </li>
    </ul>
</div>
</body>
<script>
    function local(){
        location.href = "/bittles/addComment/<c:out value="${bittle.id}" />?comment=" + document.getElementById("href").value
    }
</script>
</html>
