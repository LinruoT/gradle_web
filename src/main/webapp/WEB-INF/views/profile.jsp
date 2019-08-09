<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
  <head>
    <title>Bitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Your Profile</h1>
    username:<c:out value="" /><br/>
    <c:out value="${bitter.firstName}" /> <c:out value="${bitter.lastName}" /><br/>
    <c:out value="${bitter.email}" />

    <div>
      <a href="/bitter/${bitter.username}/addpic" >上传图片</a>
    </div>
    <c:forEach var="image" items="${imageList}">
      <a href="/${image}" target="_blank"><img src="/${image}" style="
                              width: 20%;
                              height: auto;"/>
      </a>
    </c:forEach>

    <c:forEach var="picture" items="${pictureList}">
      <a href="http://vm.linruotian.com:9000/bitter-dev-img/${picture.name}" target="_blank">
        <img src="http://vm.linruotian.com:9000/bitter-dev-img/${picture.name}" style="
                              width: 20%;
                              height: auto;"/>
      </a>
    </c:forEach>

  </body>
</html>
