<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>About Us</title>

        <meta HTTP-EQUIV="keywords" content="Agrobe businness macchina agricola macchine agricole agricultural machine agrticultural machines produttore manufacturer">

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/about.css"/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <div id="container">
        <c:import url="/jsp/header.jsp"/>

        <h1>About Us</h1>

        <div class="group">
            <div class="paragraph">
                <h2>What is Agrobe</h2>
                <p>We are a small but constantly growing factory that produces agricultural equipment to suit everyone's need for big and tiny machines. We produce standard catalogue products and, on commission, we can create custom products for our clients.</p>
            </div>
                <img class="images" src="<c:url value="/media/GRANO.jpg"/>">
        </div>

        <div class="group">
                <img class="images" src="<c:url value="/media/aratore.jpg"/>">
                <div class="paragraph">
                <h2>Our Origins</h2>
                <p>Our businness started in 1880 when after Italy's unification there were a strong push to develop an independent economy based on agriculture. Our first products were plows for the families of the town, then we moved to building and selling more and more complex machines to the neighboring cities. The rest is history.</p>
            </div>
        </div>

        <div class="group">
            <div class="paragraph">
                <h2>Our Idea of Businness</h2>
                <p>We are convinced that customer satisfaction is the most important factor to consider when working in a resilient and fundamental sector as the agricultural one. For this reason our vendors are happy to accompany our customers even after the purchasing phase of the product's life for every little doubt or curiosity that may come to their mind.</p>
            </div>
                <img class="images" src="<c:url value="/media/happy_salesman.jpg"/>">
        </div>

        <div class="group">
            <img class="images" src="<c:url value="/media/honest_farmer.jpg"/>">
            <div class="paragraph">
            <h2>Our Spirit</h2>
            <p>We love many things other than our customers:
                <ul>
                    <li>Big cultivated fields</li>
                    <li>Manufacturing high quality goods</li>
                    <li>Tractors</li>
                    <li>Our developers who deserve a good mark</li>
                </ul>
            </p>
        </div>
    </div>

    </div>
    <c:import url="/html/footer.html"/>
    </body>
</html>