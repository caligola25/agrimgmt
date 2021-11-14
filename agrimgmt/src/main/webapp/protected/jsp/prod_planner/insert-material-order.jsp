<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Add Material Order</title>
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

        <h1>Add Material Order</h1>
        <p>Insert the data for the new Material Order:</p>
        <form method="POST" action="<c:url value="/protected/prod_planner/add-material-order"/>">
            <label for="price">Total price:</label>
            <input name="price" id="price" type="text" placeholder="Price" required><br/>

            <label for="number">Total number of different raw materials:</label>
            <input name="number" id="number" type="number" min="1" placeholder="Number" required><br/>

            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
        </form>



        <a href="<c:url value="/protected/jsp/prod_planner/manage-warehouse.jsp"/>"><button>Back to Warehouse</button></a><br>
        <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

        </div>

        <c:import url="/html/footer.html"/>

        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
        <script src="<c:url value="/protected/js/form-validation-price.js"/>"></script>

    </body>
</html>