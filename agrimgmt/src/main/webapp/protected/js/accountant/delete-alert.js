var formElements = document.getElementsByName("delete-form");
formElements.forEach(function(v) {
    var elm = document.getElementById(v.id);
    elm.onsubmit = function () {
        var id = elm.id;
        var type = id.split("-")[1]
        var date = id.split("-")[2]
        if(confirm("Are you sure you want to delete the fixed cost element of type " + type + " and date " + date + "?"))
            return true;
        else
            return false;
    };
});