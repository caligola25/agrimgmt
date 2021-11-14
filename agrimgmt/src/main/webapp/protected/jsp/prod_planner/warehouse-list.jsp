<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Warehouse list</title>
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

	<h1>Warehouse List</h1>


	<c:import url="/jsp/include/show-message.jsp"/>

	<c:if test="${!message.isError}">
	    <div class="left-half-col">
            <h2>Raw Materials</h2>
            <form method="GET" action="<c:url value="/protected/prod_planner/search-stored-materials"/>">
                <input name="word" type="text" class="multiple" placeholder=" " required/><br>
                Search by:
                <input type="radio" id="id" name="type_search" value="id" checked/>
                <label for="id">Material UUID</label>
                <input type="radio" id="name" name="type_search" value="name"/>
                <label for="name">Name</label><br>
                <button type="submit">Search</button><br>
            </form>

            <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Material ID</th>
                        <th scope="col">Material</th>
                        <th scope="col">Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${materials}" var="item">
                        <tr>
                            <td>${item[0]}</td>
                            <td>${item[1]}</td>
                            <td>${item[2]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
        </div>

        <div class="right-half-col">
            <h2>Unsold Items</h2>
            <form method="GET" action="<c:url value="/protected/prod_planner/search-unsold-items"/>">
                <input name="word" type="text" class="multiple" placeholder=" " required/><br>
                Search by:
                <input type="radio" id="id" name="type_search" value="id" checked/>
                <label for="id">Product UUID</label>
                <input type="radio" id="name" name="type_search" value="name"/>
                <label for="name">Name</label><br>
                <button type="submit">Search</button><br>
            </form>

            <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Item ID</th>
                        <th scope="col">Product ID</th>
                        <th scope="col">Product</th>
                        <th scope="col">Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${items}" var="item">
                        <tr>
                            <td>${item[0]}</td>
                            <td>${item[1]}</td>
                            <td>${item[2]}</td>
                            <td>${item[3]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
        </div>
    </c:if>

    <div class="clear">
        <br/>

        <a href="<c:url value="/protected/jsp/prod_planner/manage-warehouse.jsp"/>"><button>Back</button></a><br>
        <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
    </div>

    </div>

    <c:import url="/html/footer.html"/>
    <script src="<c:url value="/protected/js/form-validation-multiple.js"/>"></script>

	</body>
</html>