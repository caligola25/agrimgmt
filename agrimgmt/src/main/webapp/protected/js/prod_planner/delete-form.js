var formElements = document.getElementsByName("delete-form");
formElements.forEach(function(v) {
    var elm = document.getElementById(v.getAttribute('id'));
    elm.onsubmit = function () {
        var id = elm.getAttribute('id');
        var name = id.split("--")[1]
        if(confirm("Are you sure you want to delete " + name + " from the database?"))
            return true;
        else
            return false;
    };
});