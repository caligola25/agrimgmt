<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 15/04/21
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Employee deletion result</title>
    </head>

    <body>
        <h1>Employee management page</h1>
        <hr/>
        <hr/>

        <!-- Visualize the old employee entity and confirm the deletion -->

        <p>The following employee has been successfully removed</p>
        <table allign="center" border="1">
            <tr>
                <th>ID</th><td>Name</th><th>Surname</th><th>Role</th><th>Salary</th><th>Number of assigned operations</th>
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

        <hr/>
        <hr/>


        <!-- Show the relative message -->
        <c:import url="/jsp/include/show-message.jsp"/>

        <br/>
        <!-- GO BACK TO THE HOME -->
        <a href="<c:url value="/protected/employee-management"/>"><button>Go back to the insert page</button></a>
    </body>
</html>
