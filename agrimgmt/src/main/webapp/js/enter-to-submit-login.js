$(document).ready(function() {
    $('input').keyup(function(event) {
        if (event.which === 13)
        {
            event.preventDefault();
            $('#login_btn').click();
        }
    });
});