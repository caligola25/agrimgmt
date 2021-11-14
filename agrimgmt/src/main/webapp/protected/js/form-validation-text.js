var inputs = document.getElementsByTagName("input");
var itemRegExpText = /^[0-9a-zA-Z.+,\-:_!?&%\s]+$/;

for(var i=0; i<inputs.length; i++){

    if(inputs[i].name === "price" || inputs[i].name === "salary" || inputs[i].id === "UUID")
        continue;
    if(inputs[i].type === "text"){

        inputs[i].addEventListener("change",function (){

            var test = itemRegExpText.test(this.value);
            if(test){
                this.setCustomValidity("");
            }else{
                this.setCustomValidity("Text format incorrect");
            }
        });
    }
}