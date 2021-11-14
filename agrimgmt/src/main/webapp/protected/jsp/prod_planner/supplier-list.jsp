<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Supplier list</title>
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

	<h1>Supplier List</h1>


	<c:import url="/jsp/include/show-message.jsp"/>

	<c:if test="${!message.isError}">

        <div class="table-responsive">
        <table class="table">
        <thead>
            <tr>
                <th>Supplier Name</th><th>Country</th><th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${suppliers}" var="sup">
                <tr>
                    <td><c:out value="${sup.name}"/></td>
                    <td><c:out value="${sup.country}"/></td>
                    <td>
                        <div class="button-container">
                            <form action="<c:url value="/protected/prod_planner/update-supplier"/>" method="GET">
                                <input type="hidden" value="${sup.name}" name="name">
                                <input type="hidden" value="${sup.country}" name="country">
                                <button type="submit"><i class="far fa-edit fa-lg update"></i></button>
                            </form>
                            <form action="<c:url value="/protected/prod_planner/delete-supplier"/>" name="delete-form" id="elm--${sup.name}" method="POST">
                                <input type="hidden" value="${sup.name}" name="name">
                                <input type="hidden" value="${sup.country}" name="country">
                                <button type="submit"><i class="far fa-trash-alt fa-lg delete"></i></button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        </table>
        </div>
    </c:if>



    <a href="<c:url value="/protected/jsp/prod_planner/manage-warehouse.jsp"/>"><button>Back</button></a><br>
    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

    </div>

    <c:import url="/html/footer.html"/>
    <script type="text/javascript" src="<c:url value="/protected/js/create-tooltips.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/protected/js/prod_planner/delete-form.js"/>"></script>

  <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

	</body>
</html>