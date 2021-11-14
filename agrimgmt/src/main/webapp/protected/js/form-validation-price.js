var inputPrice = document.getElementsByName("price");
var inputSalary = document.getElementsByName("salary");
var itemRegExpPrice = /^[0-9]{1,10}([,.][0-9]{1,2})?$/;

for(var i=0; i<inputPrice.length; i++){

    inputPrice[i].addEventListener("change",function (){

        var test = itemRegExpPrice.test(this.value);

        if(test){
            this.setCustomValidity("");
        }else{
            this.setCustomValidity("Incorrect format: A price must be a number with at most two digits after the decimal point");
        }
    });
}

for(var i=0; i<inputSalary.length; i++){

    inputSalary[i].addEventListener("change",function (){

        var test = itemRegExpPrice.test(this.value);

        if(test){
            this.setCustomValidity("");
        }else{
            this.setCustomValidity("Incorrect format: A salary must be a number with at most two digits after the decimal point");
        }
    });
}