<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <a href="<s:url value="/" />">
    <img src="<s:url value="/resources/images/bitter-logo-50.png" />"
    border="0" alt="bitter"/>
    </a>
    <h2>billy's twitter style website</h2>
    <nav>
    <ul>
    <li>
    <a href="<c:url value="/" />">Home</a>
    </li>
    <li>
    <a href="<c:url value="/bittles" />">Bittles</a>
    </li>
    <li>
    <a href="<c:url value="/bitter/mine" />">Bitter</a>
    </li>
    <li>
    <a href="<c:url value="/products" />">Products</a>
    </li>
    <li>
    <a href="<c:url value="/orders" />">Orders</a>
    </li>
    </ul>
    </nav>