<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Insert items</title>
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
            <h1>Error in step 1</h1>

            <c:import url="/jsp/include/show-message.jsp"/>

            <a href="<c:url value="/protected/jsp/prod_planner/insert-new-customer.jsp"/>"><button>Add Customer</button></a><br>
            <a href="<c:url value="/protected/jsp/prod_planner/manage-prod-orders.jsp"/>"><button>Back</button></a><br>
            <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
        </c:when>
        <c:otherwise>
            <h1>Insert items</h1>

            <c:import url="/jsp/include/show-message.jsp"/>
            <form method="POST" action="<c:url value="/protected/prod_planner/add-product-order"/>">
                <input type="hidden" value="${customer}" name="customerName"/>
                <c:forEach var="i" begin="1" end="${numProd}">
                    <h2>Item <c:out value="${i}"/></h2>
                    <label for="product_id">Product Name:</label>
                    <select name="product_id" id="product_id">
                        <c:forEach items="${products}" var="prod">
                            <option value="${prod.productId}"><c:out value="${prod.productName}"/></option>
                        </c:forEach>
                    </select>
                    <br/>
                    <label for="quantity">Quantity:</label>
                    <input name="quantity" type="number" min="1" placeholder="Quantity" required/><br/>
                </c:forEach>

                <button type="submit">Add Order</button><br/>
                <button type="reset">Reset the form</button>
            </form>
        </c:otherwise>
    </c:choose>

    </div>

    <c:import url="/html/footer.html"/>

  <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>