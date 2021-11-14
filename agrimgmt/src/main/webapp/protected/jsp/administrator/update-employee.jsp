<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 15/04/21
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Employee update page</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>

        <!-- Jquery import -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    </head>

    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <h1>Employee management page</h1>

            <!-- display the information of the researched fixed cost, if any -->
            <c:if test="${not empty employee}">
                <h2>Update</h2>
                <p>Insert the data that you want to change:</p>
                <form method="POST" action="<c:url value="/protected/administrator/update-employee"/>">
                    <input name="id" id="id" type="hidden" value="${employee.employeeId}">
                    <label for="name">Name:</label>
                    <input name="name" id="name" type="text" placeholder="Name"><br/>
                    <label for="surname">Surname:</label>
                    <input name="surname" id="surname" type="text" placeholder="Surname"><br/>
                    <label for="role">Role:</label>
                    <select name="role" id="role">
                        <option value="Production line worker" selected="selected">Production line worker</option>
                        <option value="Warehouse worker">Warehouse worker</option>
                        <option value="Accountant">Accountant</option>
                        <option value="Designer">Designer</option>
                        <option value="Production planner">Production planner</option>
                        <option value="Administrator">Administrator</option>
                    </select><br/>
                    <label for="salary">Salary:</label>
                    <input name="salary" id="salary" type="text" placeholder="Salary"><br/>

                    <button type="submit">Submit</button>
                    <button type="reset">Reset the form</button>
                </form>

                <br/>

                <p>According to the selection made the employee to be modified is:</p>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th><th>Name</th><th>Surname</th><th>Role</th><th>Salary</th><th>Number of assigned operations</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${employee.employeeId}</td>
                            <td>${employee.name}</td>
                            <td>${employee.surname}</td>
                            <td>${employee.role}</td>
                            <td>${employee.salary} €</td>
                            <td>${employee.nOperation}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <c:choose>
                <c:when test="${not empty newEmployee}">
                    <br/>

                    <p>The updated employee is:</p>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th><th>Name</th><th>Surname</th><th>Role</th><th>Salary</th><th>Number of assigned operations</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${newEmployee.employeeId}</td>
                                    <td>${newEmployee.name}</td>
                                    <td>${newEmployee.surname}</td>
                                    <td>${newEmployee.role}</td>
                                    <td>${newEmployee.salary} €</td>
                                    <td>${newEmployee.nOperation}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <br/>
                </c:when>
                <c:otherwise>
                    <br/>
                </c:otherwise>
            </c:choose>

            <!-- Show the relative message -->
            <c:import url="/jsp/include/show-message.jsp"/>

            <br/>

            <!-- GO BACK TO THE INSERTION PAGE -->
            <a href="<c:url value="/protected/jsp/administrator/employee-list.jsp"/>"><button>Go back to the insertion page</button></a>

            <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
        </div>

        <c:import url="/html/footer.html"/>

        <!-- JS scripts -->
        <script type="text/javascript" src="<c:url value="/protected/js/create-tooltips.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
        <script src="<c:url value="/protected/js/form-validation-text.js"/>"></script>
        <script src="<c:url value="/protected/js/form-validation-price.js"/>"></script>
    </body>
</html>
