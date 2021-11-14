<%--
  Created by IntelliJ IDEA.
  User: matteo
  Date: 09/04/21
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Monthly report management page</title>

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

            <h1>Monthly report management page</h1>

            <!-- UPLOAD NEW REPORT -->
            <h2>Complete the following form to upload a new report</h2>

            <form method="POST" action="<c:url value="/protected/accountant/report-management"/>" enctype="multipart/form-data">
                <label for="report-date">Report date:</label>
                <input name="date" id="report-date" type="date" placeholder="yyyy-mm-dd" required/><br/>
                <label for="file">Upload the PDF file of the monthly report: </label>
                <input name="file" id="file" type="file" placeholder="file" accept="application/pdf" required/><br/>

                <button type="submit">Upload</button>
                <button type="reset">Clear</button>
            </form>


            <!-- SHOW RESULTS OF THE UPLOAD -->
            <c:if test="${not empty message}">
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:if>

            <div class="clear"/>
            <br/>
            <hr class="split"/>

            <!-- SHOW OLD REPORT -->
            <h2>Old report downloader</h2>
            <div class="left-half-col">
                <p>Select the year of the report that you want to download:</p>
                <form method="GET" action="<c:url value="/protected/accountant/report-management"/>">
                    <label for="selectYear">Year: </label>
                    <select name="selectYear" id="selectYear">
                        <c:forEach var="year" items="${posYears}">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                    <button type="submit">Search</button>
                </form>
            </div>

            <span class="right-half-col">
                <c:if test="${not empty reportList}">
                    <p>In the year ${selectedYear} has been produced the following reports:</p>
                    <br/>
                    <ul>
                        <c:forEach var="r" items="${reportList}">
                            <li>
                                    ${r.date} report:
                                        <span>
                                            <form id="inline-download" method="GET" action="<c:url value="/protected/accountant/download-report"/>" target="_blank">
                                                <input name="selDate" id="selDate" type=hidden value="${r.date}"/>
                                                <button type="submit">Download</button>
                                            </form>
                                        </span>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>

            <!-- SHOW ERROR MESSAGE -->
            <c:if test="${messageList[0].isError}">
                <br class="clear"/>
                <c:import url="/jsp/include/show-message-list.jsp"/>
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
