<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title> WorkerOperations </title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>

        <!--For font awesome-->
        <script src="https://kit.fontawesome.com/0683b468ce.js" crossorigin="anonymous"></script>



	</head>

	<body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>

            <c:if test="${message.isError}">
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:if>
            <div>
                <c:if test='${not empty sessionScope.employee}'>
                    <h1>Operations Viewer</h1>
                    <h2>Worker: <c:out value="${sessionScope.employee.surname}"/> <c:out value="${sessionScope.employee.name}"/> </h2>
                    <p>You have: </p>
                        <ul>
                            <li>
                                <c:out value="${sessionScope.nOpRunn}"/> running op.
                            </li>
                            <li>
                                <c:out value="${sessionScope.nQueued}"/> queued op.
                            </li>
                        </ul>

                </c:if>
            </div>
            <div>
                <c:if test='${ sessionScope.nOpRunn != 0}'>
                    <h2> Operation to do now: </h2>

                    <form id="input_time_form" method="POST" action="<c:url value="/protected/lineprod_worker/insert-time"/>">
                        <label> Enter the time taken:</label><br/>
                        <input id="h" name="h" type="number" min="0" placeholder="0" /> <label>HH</label><br/>
                        <input id="m" name="m" type="number" min="0" max="59" placeholder="0" /><label>MM</label> <br/>
                        <input id="s" name="s" type="number" min="0" max="59" placeholder="0" /><label> SS</label> <br/>
                        <button type="submit">Send</button>
                        <button type="reset">Reset the form</button>
                    </form>

                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Phase ID</th>
                                <th>Item ID</th>
                                <th>Process ID</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><c:out value="${sessionScope.operationRunning.prodPhaseId}"/></td>
                                    <td><c:out value="${sessionScope.operationRunning.itemId}"/></td>
                                    <td><c:out value="${sessionScope.operationRunning.processId}"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>


                </c:if>
            </div>
            <div>
                <c:if test='${not empty sessionScope.operationsQueued}'>
                    <hr class="split"/>
                    <h2> Operations queued: </h2>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Phase ID</th>
                                    <th>Item ID</th>
                                    <th>Process ID</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="operationQ" items="${sessionScope.operationsQueued}">
                                    <tr>
                                        <td><c:out value="${operationQ.prodPhaseId}"/></td>
                                        <td><c:out value="${operationQ.itemId}"/></td>
                                        <td><c:out value="${operationQ.processId}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
            <div>
                <c:if test='${not empty sessionScope.operationsCompleted}'>
                    <h2> Operations completed: </h2>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Phase ID</th>
                                    <th>Item ID</th>
                                    <th>Process ID</th>
                                    <th>Actual time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="operationC" items="${sessionScope.operationsCompleted}">
                                    <tr>
                                        <td><c:out value="${operationC.prodPhaseId}"/></td>
                                        <td><c:out value="${operationC.itemId}"/></td>
                                        <td><c:out value="${operationC.processId}"/></td>
                                        <td><c:out value="${operationC.actualTime}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
            <br><br>
            <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to user homepage</button></a>
            <button id="top-btn"><i class="fas fa-caret-square-up"></i></button>
        </div>

        <c:import url="/html/footer.html"/>
        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/back-top.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/form-validation-time.js"/>"></script>
	</body>
</html>




