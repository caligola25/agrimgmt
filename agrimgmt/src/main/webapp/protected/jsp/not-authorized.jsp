<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Not Authorized</title>
    <title>Add Process Form</title>
    <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<body>
<div id="container">
    <c:import url="/jsp/header.jsp"/>
    <c:import url="../../jsp/sidebar.jsp" />

    <div id="content">
        <h1>Not Authorized</h1>
            <c:choose>
                <c:when test='${not empty sessionScope.employee}'>
                    <h2>You are <c:out value="${sessionScope.employee.surname}"/> <c:out value="${sessionScope.employee.name}"/>, <c:out value="${sessionScope.employee.role}"/>, so you are not authorized to access this page</h2>
                    <p><img class="images" src="<c:url value="/media/cant-access.jpg"/>"></p>
                    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Go to your home page</button></a>
                </c:when>
                <c:otherwise>
                    <c:import url="/jsp/include/show-message.jsp"/>
                </c:otherwise>
            </c:choose>

    </div>
</div>
<c:import url="/html/footer.html"/>
</body>
</html>
