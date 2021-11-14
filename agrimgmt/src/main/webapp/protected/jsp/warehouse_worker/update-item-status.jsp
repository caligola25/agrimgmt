<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update status item</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>


    </head>
    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <div id="content">
                <h1>Update status of item</h1>

                <form method="POST" action="<c:url value="/protected/warehouse_worker/update-item-status"/>">

                    <label for="item_id">Item id :</label>
                    <input name="item_id" type="text" id="item_id" placeholder="UUID" class="UUID" required/>
                    <p id="errorTag" ></p>
                    <br/>
                    <label>Item status:</label>
                    <select name="status">
                        <option value="in_construction">In construction</option>
                        <option value="stored">Stored</option>
                        <option value="shipped">Shipped</option>
                    </select>

                    <br/>

                    <button type="submit">Submit</button>
                    <button type="reset">Reset the form</button>
                </form>

                <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

            </div>
        </div>
        <c:import url="/html/footer.html"/>

        <script src="<c:url value="../../js/form-validation-uuid.js"/>"></script>
        <script src="<c:url value="../../../js/label_width.js" />"></script>
    </body>
</html>