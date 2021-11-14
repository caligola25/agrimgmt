var index=1;
var myTimer;

var d1 = document.getElementById("dot1");
var d2 = document.getElementById("dot2");
var d3 = document.getElementById("dot3");
var d4 = document.getElementById("dot4");
var d5 = document.getElementById("dot5");

window.onload=function(){
    showSlides(index);
    myTimer = setInterval(function(){nextSlides(1)}, 6000);
}


if(d1) {
    d1.onclick = function () {
        currentSlide(1);
    }
}

if(d2) {
    d2.onclick = function () {
        currentSlide(2);
    }
}

if(d3) {
    d3.onclick = function () {
        currentSlide(3);
    }
}

if(d4) {
    d4.onclick = function () {
        currentSlide(4);
    }
}
if(d5) {
    d5.onclick = function () {
        currentSlide(5);
    }
}


//per gestire il passaggio alla nuova photo
function nextSlides(n){
    clearInterval(myTimer);
    showSlides(index += 1);
    myTimer = setInterval(function(){nextSlides(n + 1)}, 6000);
}

//chiamata dai dot per effettuare il cambio di photo
function currentSlide(n){
    clearInterval(myTimer);
    myTimer = setInterval(function(){nextSlides(n + 1)}, 6000);
    showSlides(index = n);
}

//fa cambiare la classe solo al selezionato/attivo
function showSlides(n){
    var i;
    var slides = document.getElementsByClassName("photos");
    var dots = document.getElementsByClassName("dot");

    //dopo l'ultima immagine torno alla prima
    if (n > slides.length) {index = 1}

    //riporta allo stato iniziale tutte le photo e i dot
    for (i = 0; i < slides.length; i++) {
        slides[i].className = slides[i].className.replace(" activeS","");
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" activeD", "");
    }
    slides[index-1].className += " activeS";
    dots[index-1].className += " activeD";
}