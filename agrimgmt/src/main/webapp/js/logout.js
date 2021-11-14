var btn = document.getElementById("logout-btn");
btn.onclick = function() {logout()};

function logout() {
    window.location.href = "http://log:out@localhost:8080/agrimgmt-1.0/protected/jsp/user_homepage.jsp";
    return false;

    //window.location.assign("http://log:out@localhost:8080/agrimgmt-1.0/protected/jsp/user_homepage.jsp");
}