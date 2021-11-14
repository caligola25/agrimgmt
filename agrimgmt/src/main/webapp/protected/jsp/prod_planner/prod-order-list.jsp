<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Production orders list</title>
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

	<h1>Production Orders List</h1>


	<c:import url="/jsp/include/show-message.jsp"/>

	<c:if test="${!message.isError}">
        <h2>Find Production Orders</h2>
        <form method="GET" action="<c:url value="/protected/prod_planner/search-prod-order"/>">
            <input name="word" type="text" class="multiple" placeholder=" " required/><br>
            Search by:
            <input type="radio" id="id" name="type_search" value="id" checked/>
            <label for="id">UUID</label>
            <input type="radio" id="customer" name="type_search" value="customer"/>
            <label for="customer">Customer</label>
            <input type="radio" id="status" name="type_search" value="status"/>
            <label for="status">Status</label>
            <input type="radio" id="date" name="type_search" value="date"/>
            <label for="date">Date</label><br>
            <button type="submit">Search</button><br>
        </form>

        <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Product Order ID</th><th>Date</th><th>Customer</th><th>Status</th><th>Price</th><th>Report Date</th><th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${prodOrders}" var="prod">
                <tr>
                    <td><c:out value="${prod[0]}"/></td>
                    <td><c:out value="${prod[1]}"/></td>
                    <td><c:out value="${prod[2]}"/></td>
                    <td><c:out value="${prod[3]}"/></td>
                    <td><c:out value="${prod[4]}"/></td>
                    <td><c:out value="${prod[5]}"/></td>
                    <td>
                        <div class="button-container">
                        <form action="<c:url value="/protected/prod_planner/show-related-info"/>" method="GET">
                        <input type="hidden" value="${prod[0]}" name="prodOrderId">
                        <button type="submit"><i class="fas fa-search fa-lg check"></i></button>
                        </form>
                        
                        <form action="<c:url value="/protected/prod_planner/delete-prod-order"/>" name="delete-form" id="elm--${prod[0]}" method="POST">
                        <input type="hidden" value="${prod[0]}" name="prodOrderId">
                        <input type="hidden" value="${prod[1]}" name="date">
                        <input type="hidden" value="${prod[2]}" name="customer">
                        <input type="hidden" value="${prod[3]}" name="status">
                        <input type="hidden" value="${prod[4]}" name="price">
                        <input type="hidden" value="${prod[5]}" name="reportDate">
                        <button type="submit"><i class="far fa-trash-alt fa-lg delete"></i></button>
                        </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </c:if>



    <a href="<c:url value="/protected/jsp/prod_planner/manage-prod-orders.jsp"/>"><button>Back</button></a><br>
    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

    </div>
    
    <c:import url="/html/footer.html"/>

    <script type="text/javascript" src="<c:url value="/protected/js/create-tooltips.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/protected/js/prod_planner/delete-form.js"/>"></script>
    <script src="<c:url value="/protected/js/form-validation-multiple.js"/>"></script>

	</body>
</html>