<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 06/04/21
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Fixed cost management</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>

        <!-- Jquery import -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

        <!-- For font awesome -->
        <script src="https://kit.fontawesome.com/0683b468ce.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <h1>Fixed cost management page</h1>

            <!-- New fixed cost insertion form -->
            <div class="left-half-col">
                <h2>Insert a new fixed cost entity in the system</h2>
                <p>Use the following form to insert a new fixed cost entity in the system</p>

                <form name="validate" method="POST" action="<c:url value="/protected/accountant/add-fixed-cost"/>">
                    <label for="type">Type:</label>
                    <select name="type" id="type" datatype="text" required>
                        <c:forEach var="t" items="${types}">
                            <option value="${t}">${t}</option>
                        </c:forEach>
                    </select><br/>
                    <label for="fc-price">Price:</label>
                    <input name="price" id="fc-price" type="text" placeholder="price" required pattern="^[0-9]{1,10}([,.][0-9]{1,4})?$"><br/>
                    <label for="date">Date:</label>
                    <input name="date" id="date" type="date" placeholder="yyyy-mm-dd" required><br/>
                    <label for="report_date">Report date:</label>
                    <select name="report_date" id="report_date" datatype="text">
                        <option value=""></option>
                        <c:forEach var="rd" items="${reportDates}">
                            <option value="${rd}">${rd}</option>
                        </c:forEach>
                    </select><br/>

                    <button type="submit">Submit</button>
                    <button type="reset">Reset the form</button>
                </form>

                <!-- Show the relative message -->
                <c:if test="${not empty message}">
                    <br/>
                    <c:import url="/jsp/include/show-message.jsp"/>
                </c:if>
            </div>

            <!-- Update/remove fixed cost form -->
            <div class="right-half-col">
                <h2>Update or remove an existing fixed cost element</h2>
                <p>Select from the following table the fixed cost entity that you want to modify or remove (only fixed cost without a linked report are shown)</p>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Type</th>
                                <th scope="col">Date</th>
                                <th scope="col">Price</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="fc" items="${fixedCost}">
                            <tr>
                                <td>${fc.type}</td>
                                <td>${fc.date}</td>
                                <td>${fc.price} €</td>
                                <td>
                                    <div class="button-container">
                                        <form method="GET" action="<c:url value="/protected/accountant/update-fixed-cost"/>">
                                            <input name="type_search" id="type_search" type="hidden" value="${fc.type}">
                                            <input name="date_search" id="date_search" type="hidden" value="${fc.date}">
                                            <button type="submit"><i class="far fa-edit fa-lg update"></i></button>
                                        </form>
                                        <form method="POST" action="<c:url value="/protected/accountant/delete-fixed-cost"/>" name="delete-form" id="elm-${fc.type}-${fc.date}">
                                            <input name="type_search" id="type_search" type="hidden" value="${fc.type}">
                                            <input name="date_search" id="date_search" type="hidden" value="${fc.date}">
                                            <button type="submit"><i class="far fa-trash-alt fa-lg delete"></i></button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
        </div>

        <c:import url="/html/footer.html"/>

        <!-- JS scripts -->
        <script type="text/javascript" src="<c:url value="/protected/js/accountant/delete-alert.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/create-tooltips.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/logout.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/form-validation-price.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/enter-to-submit-login.js"/>"></script>
    </body>
</html>
