<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Employee Management</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>

        <!-- For font awesome -->
        <script src="https://kit.fontawesome.com/0683b468ce.js" crossorigin="anonymous"></script>

        <!-- Jquery import -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    </head>

    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <h1>Employee Management</h1>

            <h2>Insert new employee</h2>

            <p>Use the following form to insert a new employee in the system</p>
            <form id="form">
                <label for="name">Name:</label>
                <input name="name" id="name" type="text" placeholder="Name" required><br/>
                <label for="surname">Surname:</label>
                <input name="surname" id="surname" type="text" placeholder="Surname" required><br/>
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
                <input name="salary" id="salary" type="text" placeholder="Salary" required><br/>

                <button type="button" id="add_emp">Submit</button>
                <button type="reset">Reset the form</button>
            </form>

            <div id="result_add">

            </div>

            <div id="results">

            </div>

            <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
        </div>

        <c:import url="/html/footer.html"/>

        <!-- AJAX JS scripts -->
        <script type="text/javascript" src="<c:url value="/protected/js/administrator/ajax_employee_list.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/administrator/add_employee.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/administrator/delete_employee.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>

        <!-- JS scripts -->
        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>
        <script src="<c:url value="/protected/js/form-validation-text.js"/>"></script>
        <script src="<c:url value="/protected/js/form-validation-price.js"/>"></script>
    </body>
</html>