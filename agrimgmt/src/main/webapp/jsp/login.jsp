<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 18/05/2021
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <title>Login</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/login.css"/>" rel="stylesheet" type="text/css"/>

        <!-- Jquery import -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

    </head>

    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>

            <div id="contentLog">
                <h1 id="titleLog">Login</h1>
                <div id="errorDiv"></div>

                <form method="POST" id="input_form" action="" >

                    <div id="input_label">
                        <label for="username"><i class="fas fa-portrait"></i></label>
                        <input id="username" type="text" name="username" placeholder="Username" required><br/>

                        <div id="inputPsw">
                            <label for="psw"><i class="fas fa-key"></i></label>
                            <input id="psw" type="password" name="password" placeholder="Password" required><br/>
                        </div>
                    </div>
                    <button id="login_btn" type="button"> Login </button>
                    <button type="reset">Reset</button>
                </form>
            </div>


        </div>

        <c:import url="/html/footer.html"/>

        <!-- Js-->
        <script type="text/javascript" src="<c:url value="/js/login.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/enter-to-submit-login.js"/>"></script>
    </body>
</html>
