<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
  <head>
    <title>Bitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
  <aside>
    <h1>Your Profile</h1>
    <a href="/bitter/${loginId}" style='float:right; '>
      <figure>


        <img title="icon" alt="icon"
                <c:if test="${empty bitter.icon.name}"> src="<c:url value="/resources/images/user.png" />" </c:if>
                <c:if test="${not empty bitter.icon.name}"> src="http://vm.linruotian.com:9000/bitter-dev-img/${bitter.icon.name}" </c:if>
             style="width: 80px; height: auto;"
        />
        <figcaption>头像</figcaption>
      </figure>
    </a>
    <table>
      <tr>
        <th>username: </th><td><c:out value="${bitter.username}" /></td>
      </tr>
      <tr>
        <th>name: </th><td><c:out value="${bitter.firstName}" /> <c:out value="${bitter.lastName}" /></td>
      </tr>
      <tr>
        <th>email: </th><td><c:out value="${bitter.email}" /></td>
      </tr>
    </table>

    <div>
      <a href="/bitter/${bitter.username}/addpic" >上传图片</a>
    </div>
    <div>
      <a href="/bitter/${bitter.username}/changepwd" >修改密码</a>
    </div>
  </aside>
  <article>
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
  </article>


  </body>
</html>
