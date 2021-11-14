<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Change Process Estimated Time</title>
        <meta charset="utf-8">
        <title>Add Process Form</title>
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    </head>

    <body>
    <div id="container">

        <c:import url="/jsp/header.jsp"/>
        <c:import url="/jsp/sidebar.jsp"/>
        <div id="content">
            <h1>Change process estimated time</h1>

            <form method="POST" action="<c:url value="/protected/designer/change-process-estimated-time"/>">
                <h2>Insert process information:</h2>

                <label for="processId" id="processId">Process id:</label>
                <input name="processId" type="text" class="UUID" placeholder="uuid" value="${processId}" required/><br/>

                <label for="estimatedTime">New Estimated Time (HH:MM:SS) :</label>
                <input name="estimatedTime" type="time" min="00:00:01" id="estimatedTime" step="1"required/><br/>

                <br/>
                <button type="submit">Submit</button>
                <button type="reset">Reset the form</button>
            </form>
            <br/>
            <a href="<c:url value="/protected/designer/process-list"/>"><button>Back to process list</button></a><br>
            <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
        </div>
    </div>
    <c:import url="/html/footer.html"/>
    </body>
    <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/protected/js/form-validation-uuid.js"/>"></script>
</html>