<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

  <body>
    <article>
      <security:authorize access="isAnonymous()">
        未登录，<a href="/bitter/register">注册</a>     <a href="/login">登陆</a>
      </security:authorize>
      <security:authorize access="isAuthenticated()">
        <security:authentication property="principal.username" var="loginId"/>
        <h3>Hello
          <a href="/bitter/${loginId}">${loginId}</a>
          <a href="/logout" style='text-align: right;'>退出</a>
        </h3>
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
      </security:authorize>
      <h1>Welcome to Bittler高端黑</h1>
      <h2>HomeController's Time: ${time}</h2>
      <h4>总用户数：${bitterCount} 总推文数：${bittleCount}</h4>
    </article>
    <aside>
      <h2>相关链接</h2>
      <a href="<c:url value="/bittles/23" />">BittleID=23</a>
      <a href="<c:url value="/homestomp" />">test STOMP webSocket</a>
      <a href="<c:url value="/bittles/testapi/130" />">BittleID=130(test api)</a>
      <a href="<c:url value="/test/mybatis" />">test myBatis</a>
      <a href="<c:url value="/test/mq" />">test MQ</a>
    </aside>

  </body>

