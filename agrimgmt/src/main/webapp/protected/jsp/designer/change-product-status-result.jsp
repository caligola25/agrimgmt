<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Change Product Status</title>
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    </head>

    <body>
    <div id="container">
        <c:import url="/jsp/header.jsp"/>
        <c:import url="/jsp/sidebar.jsp"/>
        <div id="content">
            <h1>Change Product Status</h1>
            <c:choose>
                <c:when test="${message.isError}">
                    <c:import url="/jsp/include/show-message.jsp"/>
                </c:when>
                <c:otherwise>
                    <p><c:out value="${message.message}"/></p>
                    <h2>Result:</h2>
                    <ul>
                        <li> Product Id : ${productId} </li>
                        <li> Is it available? : ${available}</li>
                    </ul>
                </c:otherwise>
            </c:choose>
            <br>
            <a href="<c:url value="/protected/designer/product-list"/>"><button>Back to product list</button></a>
            <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
        </div>
    </div>
    <c:import url="/html/footer.html"/>
    </body>
</html>