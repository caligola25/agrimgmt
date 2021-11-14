<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Insert new customer</title>
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

	<h1>New customer</h1>

	<form method="POST" action="<c:url value="/protected/prod_planner/add-customer"/>">
		<label for="name">Customer name:</label>
		<input name="name" type="text" placeholder="Name" required/></br>
        <label for="street">Street:</label>
        <input name="street" type="text" placeholder="Street" required/></br>
        <label for="city">City:</label>
        <input name="city" type="text" placeholder="City" required/></br>
        <label for="country">Country:</label>
        <input name="country" type="text" placeholder="Country" required/></br>
		<button type="submit">Submit</button><br/>
	</form>

	<a href="<c:url value="/protected/jsp/prod_planner/manage-customers.jsp"/>"><button>Back to Customers</button></a><br>
    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

    </div>

    <c:import url="/html/footer.html"/>

    <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
    <script src="<c:url value="../../js/form-validation-text.js"/>"></script>

	</body>
</html>