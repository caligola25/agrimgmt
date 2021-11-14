<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Search Process</title>
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
            <h1>Search Process</h1>
            <c:choose>
                <c:when test="${message.isError}">
                    <c:import url="/jsp/include/show-message.jsp"/>
                    <hr class="split"/>
                    <a href="<c:url value="/protected/jsp/designer/search-process.jsp"/>"><button>Retry</button></a><br>
                </c:when>
                <c:otherwise>
                    <h2>Search sequence of processes to build a product</h2>
                    <form method="GET" action="<c:url value="/protected/designer/search-process"/>">
                        <label for="product_id" id="product_id">Product ID:</label>
                        <input name="product_id" class="UUID" type="text" placeholder="uuid" required/><br/>
                        <button type="submit">Search</button><br>
                    </form>
                    <hr class="split"/>
                    <table class="table table-responsive">
                        <thead>
                        <tr>
                            <th scope="col">Sequence Number</th>
                            <th scope="col">Process Name</th>
                            <th scope="col">Estimated Time</th>
                            <th scope="col">Material Name</th>
                            <th scope="col">Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${processes}" var="process">
                            <tr>
                                <td><c:out value="${process.sequenceNumber}"/></td>
                                <td><c:out value="${process.name}"/></td>
                                <td><c:out value="${process.estimatedTime}"/></td>
                                <td><c:out value="${process.materialName}"/></td>
                                <td><c:out value="${process.quantity}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <br>
                </c:otherwise>
            </c:choose>
            <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

        </div>
        <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
    </div>
    <c:import url="/html/footer.html"/>
    </body>
    <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/protected/js/form-validation-uuid.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>
</html>