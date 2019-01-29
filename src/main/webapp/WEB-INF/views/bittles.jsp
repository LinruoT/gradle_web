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

<html>
<head>
    <title>Bitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
<div class="bittle">
    <h1>Bit it out...</h1>
    <form method="POST" name="bittleForm">
        <input type="hidden" name="latitude">
        <input type="hidden" name="longitude">
        <textarea name="message" cols="80" rows="5"></textarea><br/>
        <input type="submit" value="Add" />
    </form>
</div>
<div class="listTitle">
    <h1>Recent Bittles</h1>
    <ul class="bittleList">
        <c:forEach items="${bittleList}" var="bittle" >
            <li id="bittle_<c:out value="bittle.id"/>">
                <div class="bittleMessage"><c:out value="${bittle.message}" /></div>
                <div>
                    <span class="bittleTime"><c:out value="${bittle.time}" /></span>
                    <span class="bittleLocation">(<c:out value="${bittle.latitude}" />, <c:out value="${bittle.longitude}" />)</span>
                </div>
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
