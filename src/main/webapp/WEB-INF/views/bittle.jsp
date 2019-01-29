<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2019/1/20
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>One Bittle</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>

<div class="listTitle">
    <h1>One Bittle</h1>
    <ul class="bittleList">
        <li id="bittle_<c:out value="bittle.id"/>">
            <div class="bittleMessage"><c:out value="${bittle.message}" /></div>
            <div>
                <span class="bittleTime"><c:out value="${bittle.time}" /></span>
                <span class="bittleLocation">(<c:out value="${bittle.latitude}" />, <c:out value="${bittle.longitude}" />)</span>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
