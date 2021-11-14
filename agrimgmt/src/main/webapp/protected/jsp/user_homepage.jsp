<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>User Homepage</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/profile.css"/>" rel="stylesheet" type="text/css"/>

        <!-- JS scripts -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

    </head>
    <body>
    <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="../../jsp/sidebar.jsp" />

            <div id="content">

                <c:choose>
                    <c:when test='${not empty sessionScope.employee}'>
                        <c:import url="/protected/jsp/profile.jsp"/>
                    </c:when>
                </c:choose>

            </div>
    </div>
    <c:import url="/html/footer.html"/>

    <!-- JS scripts -->
    <script type="text/javascript" src="<c:url value="/js/logout.js"/>"></script>
	</body>
</html>