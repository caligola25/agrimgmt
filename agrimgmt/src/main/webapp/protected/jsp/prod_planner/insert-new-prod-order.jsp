<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Insert new production order</title>
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

	<h1>New production order: step 1</h1>

	<form method="POST" action="<c:url value="/protected/prod_planner/po-add-customer-nprod"/>">
		<label for="customerName">Customer:</label>
		<input name="customerName" type="text" placeholder="Name" required/><br/>
        <label for="numProd">Number of products:</label>
        <input name="numProd" type="number" min="1" placeholder="Quantity" required/><br/>
		<button type="submit">Next</button><br/>
	</form>

    </div>

    <c:import url="/html/footer.html"/>

  <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	<script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>