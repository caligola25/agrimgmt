<%--
  Created by IntelliJ IDEA.
  User: matteocali
  Date: 31/03/21
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Financial overview</title>

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

            <h1>Financial overview</h1>

            <!-- REQUEST FORM -->
            <p>Insert the start and end date of the period you want to analyze</p>
            <form method="GET" action="<c:url value="/protected/accountant/financial-overview"/>">
                <label for="start-date">Start date:</label>
                <input name="start-date" id="start-date" type="date" placeholder="yyyy-mm-dd" required/><br/>
                <label for="end-date">End date:</label>
                <input name="end-date" id="end-date" type="date" placeholder="yyyy-mm-dd" required/><br/>

                <button type="submit">Submit</button>
                <button type="reset">Reset the form</button>
            </form>

            <!-- SHOW THE MESSAGE -->
            <div class="clear">
                <c:if test="${not empty messageList}">
                    <c:import url="/jsp/include/show-message-list.jsp"/>
                </c:if>
            </div>


            <!-- SHOW THE SEARCH RESULTS -->
            <c:if test="${not empty employeeList}">

                <hr class="split"/>

                <!-- OUTCOME OF THE COMPANY -->
                <h2>Company outcome in the period from ${startDate} to ${endDate}</h2>

                <div class="first-three-col">
                    <!-- FIXED COST VIEW -->

                    <!-- display the list of fixed cost, if any -->
                    <c:choose>
                        <c:when test="${not empty fixedCostList}">
                            <span class="center"><h3>Fixed cost overview</h3></span>
                            <span class="center"><h3>(<c:out value="from ${startDate} to ${endDate}"/>)</h3></span>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Type</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="fc" items="${fixedCostList}">
                                            <tr>
                                                <td>${fc.type}</td>
                                                <td>${fc.date}</td>
                                                <td>${fc.price} €</td>
                                            </tr>
                                        </c:forEach>
                                    <tbody/>
                                </table>
                            </div>

                            <br/>

                            <span class="center"><p class="bottom-col-p"><b>Total fixed cost price:</b> ${totalFixedPrice} €</p></span>
                        </c:when>
                        <c:otherwise>
                            <span class="center"><p class="bottom-col-p">No fixed costs found in the inserted interval of time (from ${startDate} to ${endDate})</p></span>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="second-three-col">
                    <!-- EMPLOYEE COST VIEW -->

                    <!-- display the list of employee, if any -->
                    <c:if test="${not empty employeeList}">
                        <span class="center"><h3>Employee overview</h3></span>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Name</th>
                                        <th scope="col">Surname</th>
                                        <th scope="col">Salary</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="e" items="${employeeList}">
                                        <tr>
                                            <td>${e.name}</td>
                                            <td>${e.surname}</td>
                                            <td>${e.salary} €</td>
                                        </tr>
                                    </c:forEach>
                                <tbody/>
                            </table>
                        </div>

                        <br/>

                        <span class="center"><p class="bottom-col-p"><b>Total salary cost:</b> ${totalSalary} €</p></span>
                    </c:if>
                </div>

                <div class="third-three-col">
                    <!-- MATERIAL ORDER COST VIEW -->

                    <!-- display the list of orders, if any -->
                    <c:choose>
                        <c:when test="${not empty matOrderList}">
                            <span class="center"><h3>Received material order overview</h3></span>
                            <span class="center"><h3>(<c:out value="from ${startDate} to ${endDate}"/>)</h3></span>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Supplier name</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="mo" items="${matOrderList}">
                                            <tr>
                                                <td>${mo.supplierName}</td>
                                                <td>${mo.date}</td>
                                                <td>${mo.price} €</td>
                                            </tr>
                                        </c:forEach>
                                    <tbody/>
                                </table>
                            </div>

                            <br/>

                            <span class="center"><p class="bottom-col-p"><b>Total material order cost:</b> ${totalMatOrderPrice} €</p></span>
                        </c:when>
                        <c:otherwise>
                            <span class="center"><p class="bottom-col-p">No received material order found in the inserted interval of time (from ${startDate} to ${endDate})</p>></span>
                        </c:otherwise>
                    </c:choose>
                </div>

                <br/>

                <c:set var="totalOutcome" value="${totalFixedPrice + totalSalary + totalMatOrderPrice}"/>
                <div class="clear"/>


                <hr class="split"/>


                <!-- INCOME OF THE COMPANY -->
                <h2>Company income in the period from ${startDate} to ${endDate}</h2>

                <div class="first-three-col">
                    <!-- PRODUCT ORDER COST VIEW -->

                    <!-- display the list of orders, if any -->
                    <c:choose>
                        <c:when test="${not empty prodOrderList}">
                            <span class="center"><h3>Paid and shipped product order overview</h3></span>
                            <span class="center"><h3>(<c:out value="from ${startDate} to ${endDate}"/>)</h3></span>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Customer name</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="po" items="${prodOrderList}">
                                            <tr>
                                                <td>${po.customerName}</td>
                                                <td>${po.date}</td>
                                                <td>${po.price} €</td>
                                            </tr>
                                        </c:forEach>
                                    <tbody/>
                                </table>
                            </div>

                            <br/>

                            <span class="center"><p class="bottom-col-p"><b>Total product order cost:</b> ${totalProdOrderPrice} €</p></span>
                        </c:when>
                        <c:otherwise>
                            <span class="center"><p class="bottom-col-p">No paid product order found in the inserted interval of time (from ${startDate} to ${endDate})</p></span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="clear"/>


                <hr class="split"/>


                <!-- SUMMARY VIEW -->
                <h2>Earnings overview in the period from ${startDate} to ${endDate}</h2>

                <c:set var="earnings" value="${totalProdOrderPrice - totalOutcome}"/>

                <div class="first-three-col">
                    <p class="left-text"><b>Total company's income:</b> ${totalProdOrderPrice} €</p>
                    <p class="left-text"><b>Total company's outcome:</b> ${totalOutcome} €</p>
                    <p class="bottom-col-p left-text"><b>Total company's earning:</b> <c:choose><c:when test="${earnings > 0}"><span id="pos-earning">${earnings} €</span></c:when><c:otherwise><span id="neg-earning">${earnings} €</span></c:otherwise></c:choose></p>
                </div>

                <div class="clear"/>
            </c:if>

            <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
        </div>

        <c:import url="/html/footer.html"/>

        <!-- JS scripts -->
        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/logout.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/enter-to-submit-login.js"/>"></script>
    </body>
</html>