(function() {

    var forms = document.getElementsByTagName("form");

    for(var i=0; i<forms.length; i++){
        var label_widths = [];
        var max = 0;
        var new_labels = forms[i].getElementsByTagName("label");

        for(var j=0; j<new_labels.length; j++)
            label_widths.push(new_labels[j].offsetWidth);

        for(var k=0; k<label_widths.length; k++){
            var label_width = label_widths[k];
            if (label_width > max)
                max = label_width;
        }

        max = max + 20;

        for(var l=0; l<new_labels.length; l++){
            if(new_labels[l].className === "radio_label")
                continue;
            new_labels[l].style.width = max+"px";
            new_labels[l].style.display = "inline-block";
        }
    }

})();