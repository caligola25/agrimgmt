<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 15/04/21
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Employee management page</title>
    </head>

    <body>
        <h1>Employee management page</h1>
        <hr/>
        <hr/>


        <!-- New employee insertion form -->
        <h2>Insert a new employee in the system</h2>
        <p>Use the following form to insert a new employee in the system</p>
        <form method="POST" action="<c:url value="/protected/administrator/add-employee"/>">
            <label for="name">Name:</label>
            <input name="name" id="name" type="text"><br/>
            <label for="surname">Surname:</label>
            <input name="surname" id="surname" type="text"><br/>
            <label for="role">Role:</label>
            <select name="role" id="role">
                <c:forEach var="r" items="${roles}">
                    <option value="${r}">${r}</option>
                </c:forEach>
            </select><br/>
            <label for="salary">Salary:</label>
            <input name="salary" id="salary" type="text"><br/>

            <button type="submit">Submit</button>
            <button type="reset">Reset the form</button>
        </form>

        <br/>
        <br/>

        <!-- Update/remove employee form -->
        <h2>Update or remove an already existing employee</h2>
        <p>Select from the following table the employee that you want to modify or remove</p>

        <br/>

        <table allign="center" border="1">
            <caption>List of all the employees in the company</caption>
            <tr>
                <th>ID</th><td>Name</th><th>Surname</th><th>Role</th><th>Salary</th><th>Number of assigned operations</th><th>Actions</th>
            </tr>

            <c:forEach var="e" items="${employees}">
                <tr>
                    <td>${e.employeeId}</td>
                    <td>${e.name}</td>
                    <td>${e.surname}</td>
                    <td>${e.role}</td>
                    <td>${e.salary} €</td>
                    <td>${e.nOperation}</td>
                    <td>
                        <form method="GET" action="<c:url value="/protected/administrator/update-employee"/>">
                            <input name="id" id="id" type="hidden" value="${e.employeeId}">
                            <button type="submit">Update</button>
                        </form>
                        <form method="POST" action="<c:url value="/protected/administrator/delete-employee"/>">
                            <input name="id" id="id" type="hidden" value="${e.employeeId}">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br/>
        <!-- GO BACK HOME -->
        <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Go back home</button></a>
    </body>
</html>
