var ipt = document.getElementsByClassName("multiple");

for(var i=0; i<ipt.length; i++){
    ipt[i].addEventListener("change",function (){

        var radios = this.parentNode.getElementsByTagName("input");
        var test = false;

        for(var j=0; j<radios.length; j++){

            if(radios[i].type !== "radio")
                continue;
            if(radios[j].checked && radios[j].id === "id"){

                var itemRegExpMultiple = /^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/;
                var test = itemRegExpMultiple.test(this.value);
                if(test){
                    this.setCustomValidity("");
                }else{
                    this.setCustomValidity("It must be an UUIDv4");
                }
            }
            else if(radios[j].checked && radios[j].id === "date"){

                var itemRegExpMultiple = /^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$/;
                var test = itemRegExpMultiple.test(this.value);
                if(test){
                    this.setCustomValidity("");
                }else{
                    this.setCustomValidity("Date format incorrect, it must be [YYYY]-[MM]-[DD]");
                }
            }
            else if(radios[j].checked){

                var itemRegExpMultiple = /^[0-9a-zA-Z.+,\-:_!?&%\s]+$/;
                var test = itemRegExpMultiple.test(this.value);
                if(test){
                    this.setCustomValidity("");
                }else{
                    this.setCustomValidity("Text format incorrect");
                }
            }
        }
    });
}