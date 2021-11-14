<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>AgriManagement Homepage</title>

        <meta name="description" content="Agricultural equipment factory based in Italy.">
        <meta HTTP-EQUIV="keywords" content="Agrobe macchina agricola macchine agricole agricultural machine agrticultural machines produttore manufacturer">

        <!-- CSS stylesheets -->
        <link href="<c:url value="/css/page-organization.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/homepage.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/header.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/css/footer.css"/>" rel="stylesheet" type="text/css"/>


    </head>

    <body>
        <div id="container">
            <c:import url="/jsp/header.jsp"/>

            <div class="content">
                <h1 id="main-title"> OUR BEST PRODUCTS</h1>
                <!-- DIV per lo slideshow-->
                <div class="slideshow-container">

                    <div class="photos fade">
                        <img src="<c:url value="/media/home3.png"/>" alt="Combinata rullo gommato">
                        <div class="text">Combinata rullo gommato</div>
                    </div>
                    <div class="photos fade">
                        <img src="<c:url value="/media/home2.png"/>" alt="Erpice rotante">
                        <div class="text">Erpice rotante</div>
                    </div>
                    <div class="photos fade">
                        <img src="<c:url value="/media/home1.png"/>" alt="Trincia tornadi">
                        <div class="text">Trincia tornadi</div>
                    </div>
                    <div class="photos fade">
                        <img src="<c:url value="/media/home4.png"/>" alt="Aratro">
                        <div class="text">Aratro</div>
                    </div>
                    <div class="photos fade">
                        <img src="<c:url value="/media/Tractor_no_bg.png"/>" alt="Trattore">
                        <div class="text">Trattore</div>
                    </div>

                    <br>
                    <div id="buttonsSlide">
                        <span class="dot" id="dot1" ></span>
                        <span class="dot" id="dot2" ></span>
                        <span class="dot" id="dot3" ></span>
                        <span class="dot" id="dot4" ></span>
                        <span class="dot" id="dot5" ></span>
                    </div>
                </div>

            </div>
        </div>
        <div class="clear"></div>

        <c:import url="/html/footer.html"/>
        <!-- JS scripts -->
        <script type="text/javascript" src="<c:url value="/js/slideShow.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/logout.js"/>"></script>
    </body>
</html>