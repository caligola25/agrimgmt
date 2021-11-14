<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Update Customer</title>
        <link href="<c:url value="/css/page-organization.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/header.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/footer.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/form-template.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/menu.css"/>" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/table-template.css"/>" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <!--For font awesome-->
        <script src="https://kit.fontawesome.com/0683b468ce.js" crossorigin="anonymous"></script>
    </head>

    <body>

        <div id="container">

        <c:import url="/jsp/header.jsp"/>
        <c:import url="/jsp/sidebar.jsp"/>

        <h1>Update</h1>
        <p>Insert the data that you want to change:</p>
        <form method="POST" action="<c:url value="/protected/prod_planner/update-customer"/>">
            <label for="name">Name of the customer to be modified:</label>
            <input name="name" id="name" type="text" value="${customer.name}"><br/>

            <!--Hidden input fields (old customer data)-->
            <input type="hidden" name="id" value="${customer.customerId}">
            <input type="hidden" name="country" value="${customer.country}">
            <input type="hidden" name="city" value="${customer.city}">
            <input type="hidden" name="street" value="${customer.street}">

            <label for="type">Select which information you want to update:</label>

            <input type="radio" id="name" name="type" value="name"/>
            <label for="name" class="radio_label">Name</label>
            <input type="radio" id="address" name="type" value="address"/>
            <label for="address" class="radio_label">Address</label>
            <input type="radio" id="all" name="type" value="all"/>
            <label for="all" class="radio_label">All</label>
            <br/>

            <label for="newName">New Name:</label>
            <input name="newName" id="newName" type="text" placeholder="Name"><br/>
            <label for="newCountry">New Country:</label>
            <input name="newCountry" id="newCountry" type="text" placeholder="Country"><br/>
            <label for="newCity">New City:</label>
            <input name="newCity" id="newCity" type="text" placeholder="City"><br/>
            <label for="newStreet">New Street:</label>
            <input name="newStreet" id="newStreet" type="text" placeholder="Street"><br/>

            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
        </form>



        <a href="<c:url value="/protected/prod_planner/customer-list"/>"><button>Back</button></a><br>
        <a href="<c:url value="/protected/jsp/prod_planner/manage-customers.jsp"/>"><button>Back to Customers</button></a><br>
        <a href="<c:url value="/protected/jsp/user_homepage.jsp"/>"><button>Back to home</button></a>

        </div>

        <c:import url="/html/footer.html"/>

        <script type="text/javascript" src="<c:url value="/js/label_width.js"/>"></script>
        <script src="<c:url value="/protected/js/form-validation-text.js"/>"></script>

	</body>
</html>