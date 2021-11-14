$(".product-details").click(function(){
    if(document.getElementById('product-details').checked) {
        $(".not-mandatory-pr").css("visibility", "visible");
        $('#product_name').attr("required", true)
        $('#price').attr("required", true)
    } else {
        $(".not-mandatory-pr").css("visibility", "hidden");
        $('#product_name').val(undefined)
        $('#price').val(undefined)
        $('#product_name').attr("required", false)
        $('#price').attr("required", false)
    }
});

$(".material-details").click(function(){
    if(document.getElementById('material-details').checked) {
        $(".not-mandatory-ma").css("visibility", "visible");
        $('#material_name').attr("required", true)
    } else {
        $(".not-mandatory-ma").css("visibility", "hidden");
        $('#material_name').val(undefined)
        $('#material_name').attr("required", false)
    }
});

