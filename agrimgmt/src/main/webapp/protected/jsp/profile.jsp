<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">

		<title>ViewCredential</title>
    </head>
    <body>
        <c:choose>
            <c:when test='${not empty sessionScope.employee}'>

                <h1>User homepage</h1>
                <div class="row">
                    <div class="column">
                        <div class="card">
                            <img src="<c:url value="/media/user.png"/>" alt="user">
                            <div class="content">
                                <h2><c:out value="${sessionScope.employee.surname}"/> <c:out value="${sessionScope.employee.name}"/></h2>
                                <p class="title"><c:out value="${sessionScope.employee.role}"/></p>
                                <p>ID_Number: <c:out value="${employee.employeeId}"/></p>
                                <p>Salary: <c:out value="${employee.salary}"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:otherwise>
        </c:choose>

	</body>
</html>