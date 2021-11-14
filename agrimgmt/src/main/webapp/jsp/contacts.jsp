<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>

    <meta HTTP-EQUIV="keywords" content="Agrobe contatti contacts macchina agricola macchine agricole agricultural machine agrticultural machines produttore manufacturer">

    <!-- CSS stylesheets -->
    <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/form-template.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/css/table-template.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/css/contacts.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
    <c:import url="/jsp/header.jsp"/>

    <h1>Contact Us</h1>

    <div class="row">
        <div class="column">
            <div class="card">
                <img src="<c:url value="/media/clock.jpg"/>" alt="aperture times">
                <div class="content">
                    <h2>Opening Time</h2>
                    <p class="title">When can you meet us?</p>
                    <p>MON-FRI: 8am-7pm</p>
                    <p><a href="<c:url value="/jsp/about.jsp"/>"><button class="button">More about us</button></a></p>
                </div>
            </div>
        </div>

        <div class="column">
            <div class="card">
                <img src="<c:url value="/media/phone.jpg"/>" alt="Phone"">
                <div class="content">
                    <h2>Phone</h2>
                    <p class="title">How can you call us?</p>
                    <p>+39 049 1234567</p>
                    <p><a href="tel:+390491234567"><button class="button">Call</button></a></p>
                </div>
            </div>
        </div>

        <div class="column">
            <div class="card">
                <img src="<c:url value="/media/mail.jpg"/>" alt="Mail">
                <div class="content">
                    <h2>E-Mail</h2>
                    <p class="title">How can you write to us?</p>
                    <p>info@agrimgmt.com</p>
                    <p><a href="mailto:info@agrimgmt.com"><button class="button">Write a mail</button></a></p>
                </div>
            </div>
        </div>
    </div>

</div>
<c:import url="/html/footer.html"/>
</body>
</html>