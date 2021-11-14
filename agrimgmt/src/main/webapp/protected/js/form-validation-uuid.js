var inputs = document.getElementsByClassName("UUID");
var itemRegExp = /^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/;

for(var i=0; i<inputs.length; i++) {

    inputs[i].addEventListener("change", function () {
        var test = itemRegExp.test(this.value);
        if (test) {
            this.setCustomValidity("");
        } else {
            this.setCustomValidity("It must be an UUIDv4");
        }
    })
}


