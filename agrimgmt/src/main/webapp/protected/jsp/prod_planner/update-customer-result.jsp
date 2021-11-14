<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Customer update result</title><link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
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

        <h1>Customer Update</h1>

        <c:choose>
            <c:when test="${message.isError}">
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:when>
        <c:otherwise>

                <div class="left-half-col">
                    <!-- original customer -->
                    <div class="table-responsive">
                    <table class="table">
                        <caption>Original customer entity</caption>
                        <thead>
                        <tr>
                            <th>Name</th><th>Country</th><th>City</th><th>Street</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${oldCustomer.name}</td>
                            <td>${oldCustomer.country}</td>
                            <td>${oldCustomer.city}</td>
                            <td>${oldCustomer.street}</td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>

                <div class="right-half-col"> 
                    <!-- updated customer -->
                    <div class="table-responsive">
                    <table class="table">
                        <caption>Updated customer entity</caption>
                        <thead>
                        <tr>
                            <th>Name</th><th>Country</th><th>City</th><th>Street</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${updatedCustomer.name}</td>
                            <td>${updatedCustomer.country}</td>
                            <td>${updatedCustomer.city}</td>
                            <td>${updatedCustomer.street}</td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>


        </c:otherwise>
        </c:choose>

        <a href="<c:url value="/protected/prod_planner/customer-list"/>"><button>Back to Customer List</button></a><br>
        <a href="<c:url value="/protected/jsp/prod_planner/manage-customers.jsp"/>"><button>Back to Customers</button></a><br>
        <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

        </div>

        <c:import url="/html/footer.html"/>

     <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>