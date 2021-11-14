<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 15/04/21
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Employee insertion result</title>
    </head>

    <body>
        <h1>Employee management page</h1>
        <hr/>
        <hr/>

        <!-- Visualize the just inserted employee -->
        <c:choose>
            <c:when test="${not empty employee}">
                <p>The following employee has been successfully inserted in the system</p>
                <table allign="center" border="1">
                    <tr>
                        <th>ID</th><th>Name</th><th>Surname</th><th>Role</th><th>Salary</th><th>Number of available operations</th>
                    </tr>

                    <tr>
                        <td>${employee.employeeId}</td>
                        <td>${employee.name}</td>
                        <td>${employee.surname}</td>
                        <td>${employee.role}</td>
                        <td>${employee.salary} €</td>
                        <td>${employee.nOperation}</td>
                    </tr>
                </table>

                <br/>

                <!-- GO BACK TO THE INSERT PAGE -->

                <a href="<c:url value="/protected/employee-management"/>"><button>Go back to the insert page</button></a>
            </c:when>
            <c:otherwise>
                <br/>

                <!-- GO BACK TO THE INSERT PAGE -->

                <a href="<c:url value="/protected/employee-management"/>"><button>Go back to the insert page</button></a>
            </c:otherwise>
        </c:choose>

        <hr/>
        <hr/>


        <!-- Show the relative message -->
        <c:import url="/jsp/include/show-message.jsp"/>
    </body>
</html>