var h = document.getElementById("h");
var m = document.getElementById("m");
var s = document.getElementById("s");

h.addEventListener("change",check);
m.addEventListener("change",check);
s.addEventListener("change",check);

function check(){
    if (!(m.value==0 && s.value==0 && h.value==0)) {
        this.setCustomValidity("");
    } else {
        this.setCustomValidity("A time cannot be 00:00:00");
    }
}

