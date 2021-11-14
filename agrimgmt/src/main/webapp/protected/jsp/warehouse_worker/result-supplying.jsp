<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Result Supplying</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <div id="content">
                <h1>Supplying</h1>
                <c:choose>
                    <c:when test="${message.isError}">
                        <c:import url="/jsp/include/show-message.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <p><c:out value="${message.message}"/></p>
                        <h2>Details of the order:</h2>
                        <ul>
                            <li> Id of the new order : ${materialOrderId} </li>
                            <li>Total price of order : ${price}</li>
                            <br>
                            <li>Id of raw material ordered : ${materialId}</li>
                            <li>Quantity of material raw ordered : ${quantity}</li>
                            <li>Supplier : ${supplierName}</li>
                        </ul>
                    </c:otherwise>
                </c:choose>
                    <br>
                    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
            </div>
        </div>
        <c:import url="/html/footer.html"/>
	</body>
</html>