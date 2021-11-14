<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 08/04/21
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Time difference analysis page</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>

        <!-- For font awesome -->
        <script src="https://kit.fontawesome.com/0683b468ce.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <h1>Time difference analysis</h1>

            <c:if test="${not empty timeDifference}">
                <p>In the following table is possible to analyze the difference between the estimated production time and the actual average production time for every process</p>
                <br/>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Process name</th>
                                <th scope="col">Estimated time</th>
                                <th scope="col">Average actual time</th>
                                <th scope="col">Time difference</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="td" items="${timeDifference}">
                                <tr>
                                    <td>${td.processName}</td>
                                    <td>${td.estimatedTime}</td>
                                    <td>${td.averageActualTime}</td>
                                    <td>${td.timeDifference}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <!-- Show the relative message -->
            <br/>
            <c:if test="${message.isError}">
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:if>

            <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
        </div>

        <c:import url="/html/footer.html"/>

        <!-- JS scripts -->
        <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/logout.js"/>"></script>
    </body>
</html>
