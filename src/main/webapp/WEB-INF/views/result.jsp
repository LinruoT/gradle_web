<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <title>Bitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
    <meta http-equiv="refresh" content="3;url=/bittles">
</head>
<body>

<h1>结果:<c:out value="${result}" /></h1> <br/>


</body>
</html>
