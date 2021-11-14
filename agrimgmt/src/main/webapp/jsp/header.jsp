<%--
  Created by IntelliJ IDEA.
  User: matteocali
  Date: 12/05/21
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <div id="logo-area"><a href="<c:url value="/jsp/homepage.jsp"/>"><img src="<c:url value="/media/name-logo.png"/>" id="logo"/></a></div>

    <c:choose>
        <c:when test="${empty sessionScope.employee.role}">
            <div class="not-logged-header">
                <a href="<c:url value="/jsp/about.jsp"/>" class="bar-item">About us</a>
                <a href="<c:url value="/jsp/contacts.jsp"/>" class="bar-item">Contact us</a>
                <a href="<c:url value="/show-catalogue"/>" class="bar-item">Our products</a>
                <a href="<c:url value="/jsp/login.jsp"/>" class="bar-item">Login</a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="logged-header">
                <a href="<c:url value="/jsp/about.jsp"/>" class="bar-item">About us</a>
                <a href="<c:url value="/jsp/contacts.jsp"/>" class="bar-item">Contact us</a>
                <a href="<c:url value="/show-catalogue"/>" class="bar-item">Our products</a>
                <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>" class="bar-item">User home</a>
                <a id="logout-btn" href="<c:url value="/protected/logout"/>" class="bar-item">Logout</a>
            </div>
        </c:otherwise>
    </c:choose>

</header>
