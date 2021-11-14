<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Add Production Order</title>
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

	<h1>Add Production Order: Result</h1>


	<c:import url="/jsp/include/show-message.jsp"/>



    <c:choose>
        <c:when test="${message.isError}">
            <c:if test="${message.errorCode=='E800'}">
                <h2>Missing Material</h2>
                <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Material ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${matMissing}" var="mat">
                            <tr>
                                <td>${mat[0]}</td>
                                <td>${mat[1]}</td>
                                <td>${mat[2]}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                </div>
                
                <a href="<c:url value="/protected/jsp/prod_planner/insert-material-order.jsp"/>"><button>Add Material Order</button></a><br>
                <a href="<c:url value="/protected/prod_planner/warehouse-list"/>"><button>Warehouse List</button></a><br>

            </c:if>
        </c:when>
        <c:otherwise>
            <h2>Summary</h2>
            <p>Production Order "${prodOrder.prodOrderId}" for customer ${customer}</p>
            <p><strong>Total price:</strong> € ${prodOrder.price}</p>
            <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Product ID</th>
                        <th scope="col">Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${products}" var="prod" varStatus="loop">
                        <tr>
                            <td>${prod}</td>
                            <td>${quantity[loop.index]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
            </br>



        </c:otherwise>
    </c:choose>

    <a href="<c:url value="/protected/jsp/prod_planner/manage-prod-orders.jsp"/>"><button>Back</button></a><br>
    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

    </div>

    <c:import url="/html/footer.html"/>

  <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>