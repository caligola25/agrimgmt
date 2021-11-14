<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Homepage Production Planner</title>
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

    <div>
        <h1>Homepage Production Planner</h1>
    </div>
    <div>
        <h2>Menu</h2>
        <ul>
            <li><a href="<c:url value="/protected/jsp/prod_planner/control-production.jsp"/>">Control Production</a></li>
            <li><a href="<c:url value="/protected/jsp/prod_planner/manage-prod-orders.jsp"/>">Manage Production Orders</a></li>
            <li><a href="<c:url value="/protected/jsp/prod_planner/manage-warehouse.jsp"/>">Manage Warehouse</a></li>
            <li><a href="<c:url value="/protected/jsp/prod_planner/manage-customers.jsp"/>">Manage Customers</a></li>
            <li><a href="<c:url value="/protected/jsp/user_homepage.jsp"/>">Personal Profile</a></li>
        </ul>
    </div>

    </div>

    <c:import url="/html/footer.html"/>

<script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>