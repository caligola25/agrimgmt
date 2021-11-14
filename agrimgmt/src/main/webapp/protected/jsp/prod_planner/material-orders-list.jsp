<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Material orders list</title>
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

	<h1>Material Orders List</h1>


	<c:import url="/jsp/include/show-message.jsp"/>

	<c:if test="${!message.isError}">
        <h2>Find Material Orders</h2>
        <form method="GET" action="<c:url value="/protected/prod_planner/search-material-order"/>">
            <input name="word" type="text" class="multiple" placeholder=" " required/><br>
            Search by:
            <input type="radio" id="id" name="type_search" value="id" checked/>
            <label for="id">UUID</label>
            <input type="radio" id="supplier" name="type_search" value="supplier"/>
            <label for="supplier">Supplier</label>
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
                    <th>Material Order ID</th>
                    <th>Supplier</th>
                    <th>Status</th>
                    <th>Date</th>
                    <th>Price</th>
                    <th>Report Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${materialOrders}" var="item">
                    <tr>
                        <td><c:out value="${item[0]}"/></td>
                        <td><c:out value="${item[1]}"/></td>
                        <td><c:out value="${item[2]}"/></td>
                        <td><c:out value="${item[3]}"/></td>
                        <td><c:out value="${item[4]}"/></td>
                        <td><c:out value="${item[5]}"/></td>
                        <td>
                            <div class="button-container">
                            <form action="<c:url value="/protected/prod_planner/delete-material-order"/>" name="delete-form" id="elm--${item[0]}" method="POST">
                                <input type="hidden" name="materialOrderId" value ="${item[0]}">
                                <button type="submit" name="caller" value="delete"><i class="far fa-trash-alt fa-lg delete"></i></button>
                            </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
    </c:if>



    <a href="<c:url value="/protected/jsp/prod_planner/manage-warehouse.jsp"/>"><button>Back</button></a><br>
    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

    </div>

    <c:import url="/html/footer.html"/>
    <script type="text/javascript" src="<c:url value="/protected/js/create-tooltips.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/protected/js/prod_planner/delete-form.js"/>"></script>
    <script src="<c:url value="/protected/js/form-validation-multiple.js"/>"></script>

	</body>
</html>