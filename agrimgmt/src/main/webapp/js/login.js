
var request;

btnInvia=document.getElementById('login_btn');
errorDiv=document.getElementById('errorDiv');

btnInvia.onclick=function (){
    makeRequest();
};

function makeRequest (){
    //recupero username e password
    var user = document.getElementById("username").value;
    var psw = document.getElementById("psw").value;

    //ora devo combinarli come user:psw e fare ecoded64
    var combinazione = user + ':' + psw;
    var encodedData = window.btoa(combinazione);

    request = new XMLHttpRequest();
    //Ora devo creare la richiesta di autenticazione
    var url_c = 'http://localhost:8080/agrimgmt-1.0/protected/jsp/user_homepage.jsp';
    request.onreadystatechange = alertContents;

    request.open('GET', url_c);
    request.setRequestHeader("Authorization", "Basic " + encodedData);
    request.send();
}


function alertContents() {
    if (request.readyState === XMLHttpRequest.DONE) {

        if (request.status == 200) {
            window.location.replace('http://localhost:8080/agrimgmt-1.0/protected/jsp/user_homepage.jsp');

        } else {
            errorDiv.innerHTML="ATTENTION: wrong credentials";
        }
    }
}