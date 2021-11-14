<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Update Supplier</title>
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

        <h1>Update Supplier Result</h1>

        <c:choose>
            <c:when test="${message.isError}">
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:when>
        <c:otherwise>

                <div class="left-half-col">
                    <!-- original supplier -->
                    <div class="table-responsive">
                    <table class="table">
                        <caption>Original Supplier entity</caption>
                        <thead>
                        <tr>
                            <th>Name</th><th>Country</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><c:out value="${oldSupplier.name}"/></td>
                            <td><c:out value="${oldSupplier.country}"/></td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>

                <div class="right-half-col"> 
                    <!-- updated supplier -->
                    <div class="table-responsive">
                    <table class="table">
                        <caption>Updated Supplier entity</caption>
                        <thead>
                        <tr>
                            <th>Name</th><th>Country</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><c:out value="${updatedSupplier.name}"/></td>
                            <td><c:out value="${updatedSupplier.country}"/></td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>



        </c:otherwise>
        </c:choose>

        <a href="<c:url value="/protected/prod_planner/supplier-list"/>"><button>Back to Supplier List</button></a><br>
        <a href="<c:url value="/protected/jsp/prod_planner/manage-warehouse.jsp"/>"><button>Back to Warehouse</button></a><br>
        <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

        </div>

        <c:import url="/html/footer.html"/>

     <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>