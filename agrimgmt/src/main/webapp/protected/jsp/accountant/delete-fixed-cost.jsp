<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 06/04/21
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Fixed cost deletion result</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <h1>Fixed cost management page</h1>

            <!-- Visualize the old fixed cost entity and confirm the deletion -->
            <p>The following fixed cost entity has been successfully removed</p>
            <div class="table-responsive">
                <table class="table" id="left-table">
                    <thead>
                        <tr>
                            <th scope="col">Type</th>
                            <th scope="col">Date</th>
                            <th scope="col">Price</th>
                            <th scope="col">Report date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${fixedCost.type}</td>
                            <td>${fixedCost.date}</td>
                            <td>${fixedCost.price} €</td>
                            <td>${fixedCost.report_date}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <br/>

            <!-- Show the relative message -->
            <c:import url="/jsp/include/show-message.jsp"/>

            <br/>

            <a href="<c:url value="/protected/accountant/fixed-cost-management"/>"><button>Go back to the insert page</button></a>
        </div>

        <c:import url="/html/footer.html"/>

        <!-- JS scripts -->
        <script type="text/javascript" src="<c:url value="/js/logout.js"/>"></script>
    </body>
</html>