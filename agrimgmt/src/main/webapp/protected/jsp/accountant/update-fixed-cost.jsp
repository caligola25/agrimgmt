<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 06/04/21
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Fixed cost update page</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>

        <!-- Jquery import -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    </head>

    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <h1>Fixed cost management page</h1>

            <!-- display the information of the researched fixed cost, if any -->
            <c:if test="${not empty fixedCost}">
                <h2>Update</h2>
                <p>Insert the data that you want to change:</p>
                <form method="POST" action="<c:url value="/protected/accountant/update-fixed-cost"/>">
                    <label for="fc-type">Type:</label>
                    <select name="type" id="fc-type" datatype="text">
                        <option value=""></option>
                        <c:forEach var="t" items="${types}">
                            <option value="${t}">${t}</option>
                        </c:forEach>
                    </select><br/>
                    <label for="fc-price">Price:</label>
                    <input name="price" id="fc-price" type="text" placeholder="price" pattern="^[0-9]{1,10}([,.][0-9]{1,4})?$"><br/>
                    <label for="fc-date">Date:</label>
                    <input name="date" id="fc-date" type="date" placeholder="yyyy-mm-dd" ><br/>
                    <label for="report-date">Report date:</label>
                    <select name="report_date" id="report-date">
                        <option value=""></option>
                        <c:forEach var="date" items="${reportDates}">
                            <option value="${date}">${date}</option>
                        </c:forEach>
                    </select><br/>
                    <c:choose>
                        <c:when test="${not empty newFixedCost}">
                            <input name="oldType" id="oldType" type=hidden value="${newFixedCost.type}"/>
                            <input name="oldDate" id="oldDate" type=hidden value="${newFixedCost.date}"/>
                        </c:when>
                        <c:otherwise>
                            <input name="oldType" id="oldType" type=hidden value="${fixedCost.type}"/>
                            <input name="oldDate" id="oldDate" type=hidden value="${fixedCost.date}"/>
                        </c:otherwise>
                    </c:choose>

                    <button type="submit">Submit</button>
                    <button type="reset">Reset the form</button>
                </form>

                <br/>

                <div class="left-half-col">
                    <p class="center">According to the inserted value the fixed cost to be modified is:</p>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Type</th>
                                <th scope="col">Date</th>
                                <th scope="col">Price</th>
                                <th scope="col">Report date</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${fixedCost.type}</td>
                                <td>${fixedCost.date}</td>
                                <td>${fixedCost.price} €</td>
                                <td>${fixedCost.report_date}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>

            <div class="right-half-col">
                <c:if test="${not empty newFixedCost}">
                    <p class="center">The updated fixed cost entity is:</p>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Type</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Report date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${newFixedCost.type}</td>
                                    <td>${newFixedCost.date}</td>
                                    <td>${newFixedCost.price} €</td>
                                    <td>${newFixedCost.report_date}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>


            <!-- Show the relative message -->
            <c:if test="${message.isError}">ù
                <br class="clear"/>
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:if>

            <br class="clear"/>

            <!-- GO BACK TO THE INSERT PAGE -->
            <a href="<c:url value="/protected/accountant/fixed-cost-management"/>"><button>Go back to the insert page</button></a>
        </div>

        <c:import url="/html/footer.html"/>

        <!-- JS scripts -->
        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/logout.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/form-validation-price.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/enter-to-submit-login.js"/>"></script>
    </body>
</html>
