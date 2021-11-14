<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Control Production</title>
    <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="<c:url value="/css/table-template.css"/>">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <!--For font awesome-->
    <script src="https://kit.fontawesome.com/0683b468ce.js" crossorigin="anonymous"></script>
</head>

<body>
    <div id="container">

    <c:import url="/jsp/header.jsp"/>
    <c:import url="/jsp/sidebar.jsp"/>

    <div>
        <h1>Control Production</h1>
    </div>
    <div>
        <div class="row">
            <span class="button-menu"><a href="<c:url value="/protected/prod_planner/control-order-in-prod"/>"><button><p><i class="fas fa-tasks fa-3x"></i></p><p>Order in Production</p></button></a></span>
            <span class="button-menu"><a href="<c:url value="/protected/prod_planner/employee-list"/>"><button><p><i class="far fa-list-alt fa-3x"></i></p><p>Employee List</p></button></a></span>
            <span class="button-menu"><a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button><p><i class="fas fa-house-user fa-3x"></i></p><p>Back to home</p></button></a></span>
        </div>
    </div>

    </div>

    <c:import url="/html/footer.html"/>

<script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>