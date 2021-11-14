<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
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

	<h1>Employees List</h1>


	<c:import url="/jsp/include/show-message.jsp"/>

	<c:if test="${!message.isError}">
        <h2>Employee</h2>


        <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Employee ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Salary</th>
                <th>Operations</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td><c:out value="${employee.employeeId}"/></td>
                    <td><c:out value="${employee.name}"/></td>
                    <td><c:out value="${employee.surname}"/></td>
                    <td><c:out value="${employee.salary}"/></td>
                    <td><c:out value="${employee.nOperation}"/></td>
                    <td>
                        <div class="button-container">
                        <form method="GET" action="<c:url value="/protected/prod_planner/control-employee"/>">
                            <input type="hidden" name="employeeId" value="${employee.employeeId}"/><br>
                            <button type="submit"><i class="fas fa-search fa-lg check"></i></button><br>
                        </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </c:if>



    <a href="<c:url value="/protected/jsp/prod_planner/control-production.jsp"/>"><button>Back</button></a><br>
    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

    </div>

    <c:import url="/html/footer.html"/>
    <script type="text/javascript" src="<c:url value="/protected/js/create-tooltips.js"/>"></script>

  <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>