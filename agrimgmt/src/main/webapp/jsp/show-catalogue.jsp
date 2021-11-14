<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Catalogue</title>

        <meta HTTP-EQUIV="keywords" content="Agrobe macchina agricola macchine agricole prodotto prodotti product products agricultural machine agrticultural machines produttore manufacturer">

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

        <c:choose>
            <c:when test="${not empty sessionScope.employee}">
                <c:import url="/jsp/sidebar.jsp"/>
            </c:when>
        </c:choose>

        <div id="content">
            <h1>What We Sell</h1>

            <c:choose>
                <c:when test="${message.isError}">
                    <c:import url="/jsp/include/show-message.jsp"/>
                </c:when>
                <c:otherwise>
                    <table class="table table-responsive">
                        <thead>
                            <tr>
                                <th> <c:out value="Product ID"/> </th>
                                <th> <c:out value="Product Name"/> </th>
                                <th> <c:out value="Price"/> </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="e" items="${elements}">
                        <tr>
                            <td>${e.productId}</td>
                            <td>${e.productName}</td>
                            <td>${e.price}</td>
                        </tr>
                        </c:forEach>
                        <tbody/>
                    </table>
                </c:otherwise>
            </c:choose>

            <br>
            <c:choose>
                <c:when test="${empty sessionScope.employee}">
                    <a href="<c:url value="/jsp/homepage.jsp"/>"><button>Back to home</button></a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <c:import url="/html/footer.html"/>
    <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>
	</body>
</html>