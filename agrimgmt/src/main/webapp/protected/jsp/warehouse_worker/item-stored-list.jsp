<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Item stored</title>

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
                <h1>Show item stored list</h1>
                <c:choose>
                    <c:when test="${message.isError}">
                        <c:import url="/jsp/include/show-message.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-responsive">
                            <thead>
                                <tr>
                                    <th> <c:out value="Item ID"/> </th>
                                    <th> <c:out value="Product Order ID"/> </th>
                                    <th> <c:out value="Product ID"/> </th>
                                    <th> <c:out value="Product Name"/> </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="e" items="${elements}">
                                    <tr>
                                        <td>${e[0]}</td>
                                        <td>${e[1]}</td>
                                        <td>${e[2]}</td>
                                        <td>${e[3]}</td>
                                    </tr>
                                </c:forEach>
                            <tbody/>
                        </table>
                    </c:otherwise>
                </c:choose>

                <br>
                <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
            </div>
        </div>
        <c:import url="/html/footer.html"/>
	</body>
</html>