<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Add Material Order Result</title>
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

    <h1>Add Material Order Result</h1>

    <c:choose>
        <c:when test="${message.isError}">
            <c:import url="/jsp/include/show-message.jsp"/>

            <a href="<c:url value="/protected/jsp/prod_planner/manage-warehouse.jsp"/>"><button>Back to Warehouse</button></a><br>
            <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
        </c:when>
        <c:otherwise>
        <div class="table-responsive">
        <table class="table">
            <caption>New Material Order</caption>
            <thead>
            <tr>
                <th>ID</th><th>Price</th><th>Date</th><th>Status</th><th>Report date</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${materialOrder.materialOrderId}</td>
                <td>${materialOrder.price}</td>
                <td>${materialOrder.materialOrderDate}</td>
                <td>${materialOrder.orderStatus}</td>
                <td>${materialOrder.reportDate}</td>
            </tr>
            </tbody>
        </table>
        </div>


        <form id="main_form" method="POST" action="<c:url value="/protected/prod_planner/add-supplying-material-order"/>">
            <input type="hidden" value="${materialOrder.materialOrderId}" name="materialOrderId"/>
            <label for="supplier">Supplier:</label>
            <select name="supplier" id="supplier">
                <c:forEach var="sup" items="${supp_names}">
                    <option value="${sup}"><c:out value="${sup}"/></option>
                </c:forEach>
            </select>
            <br/>
            <c:forEach var="i" begin="1" end="${num}">
                <h4>Supplying <c:out value="${i}"/></h4>
                <label for="material_id">Raw Material name:</label>
                <select name="material_id" id="material_id">
                    <c:forEach var="mat" items="${raw_materials}">
                        <option value="${mat.materialId}"><c:out value="${mat.materialName}"/></option>
                    </c:forEach>
                </select><br/>

                <label for="quantity">Quantity:</label>
                <input name="quantity" type="number" min=placeholder="Quantity" required/><br/>

            </c:forEach>

            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
        </form>

        <form id="undo_form" method="POST" action="<c:url value="/protected/prod_planner/delete-material-order"/>">
            <input type="hidden" value="${materialOrder.materialOrderId}" name="materialOrderId"/>
            <button type="submit" name="caller" value="undo">Undo changes</button><br>
        </form>
        </c:otherwise>
    </c:choose>

    </div>

    <c:import url="/html/footer.html"/>

    <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>