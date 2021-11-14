<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Customers list</title>
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

	<h1>Customers List</h1>


	<c:import url="/jsp/include/show-message.jsp"/>

	<c:if test="${!message.isError}">
        <h2>Customers</h2>

        <form method="GET" action="<c:url value="/protected/prod_planner/search-customer"/>">
            <input name="word" type="text" class="multiple" placeholder=" " required/><br>
            Search by:
            <input type="radio" id="id" name="type_search" value="id" checked/>
            <label for="id">UUID</label>
            <input type="radio" id="name" name="type_search" value="name"/>
            <label for="name">Name</label><br>
            <button type="submit">Search</button><br>
        </form>

        <div class="table-responsive">
        <table class="table">
        <thead>
            <tr>
                <th>Customer ID</th><th>Name</th><th>Country</th><th>City</th><th>Street</th><th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${customers}" var="item">
                <tr>
                    <td><c:out value="${item.customerId}"/></td>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.country}"/></td>
                    <td><c:out value="${item.city}"/></td>
                    <td><c:out value="${item.street}"/></td>
                    <td>
                        <div class="button-container">
                        <form action="<c:url value="/protected/prod_planner/update-customer"/>" method="GET">
                            <input type="hidden" name="id" value="${item.customerId}">
                            <input type="hidden" name="name" value="${item.name}">
                            <input type="hidden" name="country" value="${item.country}">
                            <input type="hidden" name="city" value="${item.city}">
                            <input type="hidden" name="street" value="${item.street}">
                            <button type="submit"><i class="far fa-edit fa-lg update"></i></button>
                        </form>
                        <form action="<c:url value="/protected/prod_planner/delete-customer"/>" name="delete-form" id="elm--${item.name}" method="POST">
                            <input type="hidden" name="id" value="${item.customerId}">
                            <input type="hidden" name="name" value="${item.name}">
                            <input type="hidden" name="country" value="${item.country}">
                            <input type="hidden" name="city" value="${item.city}">
                            <input type="hidden" name="street" value="${item.street}">
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



    <a href="<c:url value="/protected/jsp/prod_planner/manage-customers.jsp"/>"><button>Back</button></a><br>
    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

    </div>

    <c:import url="/html/footer.html"/>
    <script type="text/javascript" src="<c:url value="/protected/js/create-tooltips.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/protected/js/prod_planner/delete-form.js"/>"></script>
    <script src="<c:url value="/protected/js/form-validation-multiple.js"/>"></script>

	</body>
</html>