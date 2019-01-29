<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Bitter</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Welcome to Bittler</h1>
    <h2>HomeController's Time: ${time}</h2>
    <a href="<c:url value="/bittles" />">Bittles</a> |
    <a href="<c:url value="/bittles/2333" />">BittleID=2333</a>
    <a href="<c:url value="/bitter/register" />">Bitter Register</a>
  </body>
</html>
