<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Control Production</title>
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

	<h1>Control Production</h1>


	<c:import url="/jsp/include/show-message.jsp"/>

	<c:if test="${!message.isError}">
        <div class="table-responsive">
        <table class="table">
        <thead>
            <tr>
                <th>Production Phase ID</th><th>Process ID</th><th>Process</th><th>Employee iD</th><th>Employee</th><th>Status</th><th>Estimated Time</th><th>Actual Time</th><th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${prodPhases}" var="phase">
                <tr>
                    <td><c:out value="${phase[0]}"/></td>
                    <td><c:out value="${phase[1]}"/></td>
                    <td><c:out value="${phase[2]}"/></td>
                    <td><c:out value="${phase[3]}"/></td>
                    <td><c:out value="${phase[4]}"/></td>
                    <td><c:out value="${phase[5]}"/></td>
                    <td><c:out value="${phase[6]}"/></td>
                    <td><c:out value="${phase[7]}"/></td>
                    <td>
                        <div class="button-container">
                        <form action="<c:url value="/protected/prod_planner/control-employee"/>" method="GET">
                        <input type="hidden" value="${phase[3]}" name="employeeId">
                        <button type="submit"><i class="fas fa-search fa-lg check"></i></button>
                        </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        </table>
        </div>
    </c:if>



    <a href="<c:url value="/protected/jsp/prod_planner/control-production.jsp"/>"><button>Back to Control Production</button></a><br>
    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

    </div>

    <c:import url="/html/footer.html"/>
    <script type="text/javascript" src="<c:url value="/protected/js/create-tooltips.js"/>"></script>

  <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>