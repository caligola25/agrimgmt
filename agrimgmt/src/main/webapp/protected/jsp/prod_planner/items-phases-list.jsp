<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Show Related Info</title>
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <!--For font awesome-->
        <script src="https://kit.fontawesome.com/0683b468ce.js" crossorigin="anonymous"></script>
    </head>

    <body>

    <div id="container">

    <c:import url="/jsp/header.jsp"/>
    <c:import url="/jsp/sidebar.jsp"/>

    <c:choose>
        <c:when test="${message.isError}">
            <c:import url="/jsp/include/show-message.jsp"/>
        </c:when>
        <c:otherwise>
            <h1>Show related Info</h1>

        <c:forEach items="${info}" var="pair">
            <h2>Item</h2><br>
            <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th>Item ID</th><th>Item Status</th><th>Product Order ID</th><th>Product ID</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><c:out value="${pair.key.itemId}"/></td>
                    <td><c:out value="${pair.key.itemStatus}"/></td>
                    <td><c:out value="${pair.key.productOrderId}"/></td>
                    <td><c:out value="${pair.key.productId}"/></td>
                </tr>
                </tbody>
            </table>
            </div>

            <h2>Production Phases</h2><br>
            
            <div class="table-responsive large-table">
            <table class="table">
                <thead>
                <tr>
                    <th>Phase ID</th><th>Phase Status</th><th>Actual Time</th><th>Item ID</th><th>Process ID</th><th>Employee ID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pair.value}" var="phase">
                    <tr>
                        <td><c:out value="${phase.prodPhaseId}"/></td>
                        <td><c:out value="${phase.status}"/></td>
                        <td><c:out value="${phase.actualTime}"/></td>
                        <td><c:out value="${phase.itemId}"/></td>
                        <td><c:out value="${phase.processId}"/></td>
                        <td><c:out value="${phase.employeeId}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>

        </c:forEach>


        </c:otherwise>

    </c:choose>
    <a href="<c:url value="/protected/jsp/prod_planner/manage-prod-orders.jsp"/>"><button>Back to Product Orders</button></a><br>
    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

    <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
    </div>

    <c:import url="/html/footer.html"/>

    <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>

	</body>
</html>