<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Add Process Form</title>
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/designer.css"/>" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    </head>

    <body>

        <div id="container">
            <c:import url="/jsp/header.jsp"/>
            <c:import url="/jsp/sidebar.jsp"/>

            <div id="content">
                <h1>Add Process</h1>

                <form method="POST" action="<c:url value="/protected/designer/add-process"/>">
                    <div class="first-three-col">
                        <h2>Process information</h2>

                        <label for="process_name" id="process_name">Process name*:</label>
                        <input name="process_name" type="text" placeholder="name" required/><br/>

                        <label for="sequence_number" id="sequence_number">Sequence number*:</label>
                        <input name="sequence_number" type="number" min="1" placeholder="sequence number" required/><br/>

                        <label for="quantity" id="quantity">Quantity of raw material*:</label>
                        <input name="quantity" type="number" min="1" placeholder="quantity" required/><br/>

                        <label for="estimated_time">Estimated time (HH:MM:SS)*:</label>
                        <input name="estimated_time" type="time" id="estimated_time" min="00:00:01" step="1" required/><br/>
                    </div>

                    <div class="second-three-col">
                        <h2>Related product information</h2>

                        <label for="product_id" id="product_id">Product id number*:</label>
                        <input name="product_id" class="UUID" type="text" placeholder="uuid" required/><br/><br/>

                        If the product is not yet in the database, check the box to add the details: <input id="product-details" class="product-details" type="checkbox"/>
                        <div class="not-mandatory-pr">
                            <label for="product_name" >Product name**:</label>
                            <input name="product_name" id="product_name" placeholder="name" type="text"/><br/>

                            <label for="price" >Price [€]**:</label>
                            <input name="price" id="price" type="text" placeholder="price (e.g. 20.50)"/><br/>

                            <label>Product status:</label>
                            <select name="available">
                                <option value="true">Available</option>
                                <option value="false">Not available</option>
                            </select>
                        </div>
                    </div>

                    <div class="third-three-col">
                        <h2>Related material information</h2>

                        <label for="material_id" id="material_id">Material id number*:</label>
                        <input name="material_id" type="text" class="UUID" placeholder="uuid" required/><br/><br/>

                        If the material is not yet in the database, check the box to add the details: <input id="material-details" class="material-details" type="checkbox"/>
                        <div class="not-mandatory-ma">
                            <label for="material_name" >Material name**:</label>
                            <input name="material_name" id="material_name" placeholder="name" type="text"/><br/><br/>
                        </div>
                    </div>

                    <div class="clear">
                        <p class="footnotes">
                            * mandatory field<br/>
                            ** mandatory only if not present in the database<br/><br/>
                        </p>
                        <button type="submit">Submit</button>
                        <button type="reset">Reset the form</button>
                    </div>
                </form>
                <br>
                <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>
            </div>
        </div>
        <c:import url="/html/footer.html"/>
        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/designer/not-mandatory-fields.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/form-validation-text.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/form-validation-uuid.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/protected/js/form-validation-price.js"/>"></script>
    </body>
</html>