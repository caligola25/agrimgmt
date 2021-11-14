
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Supplying</title>

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>


    </head>
    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <div id="content">

                <h1>Supplying of raw material</h1>

                <form method="POST" action="<c:url value="/protected/warehouse_worker/supplying"/>">

                    <label for="material_id" >Material id number:</label>
                    <input id="material_id" name="material_id" type="text" placeholder="UUID"  class="UUID"required/>
                    <br/>

                    <label for="quantity">Quantity:</label>
                    <input id="quantity" name="quantity" type="number" min="1" placeholder="Quantity" required/><br/>

                    <label for="supplier" >Supplier:</label>
                    <input id="supplier" name="supplier" type="text" placeholder="Name" required/><br/>

                    <label for="price">Total price for order:</label>
                    <input  id="price" name="price" type="text" placeholder="Price" required pattern="^[0-9]{1,10}([,.][0-9]{1,4})?$" required/><br/>

                    <button type="submit">Submit</button>
                    <button type="reset">Reset the form</button>
                </form>
                    <br>
                    <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
            </div>
        </div>
        <c:import url="/html/footer.html"/>

        <script src="<c:url value="../../js/form-validation-text.js"/>"></script>
        <script src="<c:url value="../../js/form-validation-price.js"/>"></script>
        <script src="<c:url value="../../js/form-validation-uuid.js"/>"></script>
        <script src="<c:url value="../../../js/label_width.js" />"></script>
    </body>
</html>