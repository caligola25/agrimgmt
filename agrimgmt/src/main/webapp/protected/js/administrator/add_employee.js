function add_employee() {

  var httpRequest_e;
  var httpRequest_c;

  var id = uuid();
  var surname = document.getElementById('surname').value;
  var name = document.getElementById('name').value;
  var role = document.getElementById('role').value;
  var salary = document.getElementById('salary').value;
  var nOperation = 0;

  if(!checkInputValidity(document.getElementById('name'), document.getElementById('surname'), document.getElementById('salary')))
     return;

  var employee = "{\"employee\":{\"employeeId\":\"" + id + "\",\"surname\":\"" + surname +"\",\"name\":\"" + name + "\",\"role\":\"" + role + "\",\"salary\":" + salary + ",\"nOperation\":" + nOperation + "}}";

  var username = name.substring(0, 3).toLowerCase() + "." + surname.toLowerCase();
  var passwd = makepw(10);
  var credential = "{\"credential\":{\"username\":\"" + username + "\",\"password\":\"" + passwd +"\",\"employeeId\":\"" + id + "\"}}";

  makeRequestEmployee(employee);

  function makeRequestEmployee(employee) {

    var url_e = 'http://localhost:8080/agrimgmt-1.0/protected/administrator/rest/employee/';

    httpRequest_e = new XMLHttpRequest();

    if (!httpRequest_e) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }
    httpRequest_e.onreadystatechange = alertContentsE;
    httpRequest_e.open('POST', url_e);
    httpRequest_e.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    httpRequest_e.send(employee);
  }

  function makeRequestCredential(credential) {

    var url_c = 'http://localhost:8080/agrimgmt-1.0/protected/administrator/rest/credential/';

    httpRequest_c = new XMLHttpRequest();

    if (!httpRequest_c) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }
    httpRequest_c.onreadystatechange = alertContentsC;
    httpRequest_c.open('POST', url_c);
    httpRequest_c.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    httpRequest_c.send(credential);
  }

  function alertContentsE() {
      if (httpRequest_e.readyState === XMLHttpRequest.DONE) {

        if (httpRequest_e.status == 201) {

          makeRequestCredential(credential);

        } else {
            alert('There was a problem with the request.');
        }
      }
  }

    function alertContentsC() {
        if (httpRequest_c.readyState === XMLHttpRequest.DONE) {

          if (httpRequest_c.status == 201) {

            var div = document.getElementById('result_add');

            var p1 = document.createElement('p');
            var b = document.createElement('b');
            b.appendChild(document.createTextNode("Results: "));
            p1.appendChild(b);
            p1.appendChild(document.createTextNode("Employee \"" + name + " " + surname + "\" correctly added"));
            p1.appendChild(document.createElement('br'));
            p1.appendChild(document.createTextNode("Username: " + username));
            p1.appendChild(document.createElement('br'));
            p1.appendChild(document.createTextNode("Password: " + passwd));
            p1.appendChild(document.createElement('br'));
            div.replaceChildren(p1);

            var form_u = "<form method=\"GET\" action=\"../../administrator/update-employee\"><input type=\"hidden\" name=\"id\" value=\""+id+"\"><div class=\"tooltip\"><button type=\"submit\"><i class=\"far fa-edit fa-lg update\"></i><span class=\"tooltiptext\">Update</span></button></div></form>";
            var form_d = "<form method=\"GET\" id=\"e"+id+"\" name=\"emp_form\"><div class=\"tooltip\"><button type=\"button\"><i class=\"far fa-trash-alt fa-lg delete\"></i><span class=\"tooltiptext\">Delete</span></button></div></form>";
            $('#employee_table > tbody:last-child').append('<tr id=\"r'+id+'\"><td>'+id+'</td><td>'+name+'</td><td>'+surname+'</td><td>'+role+'</td><td>'+salary+'</td><td>'+nOperation+'</td><td>'+'<div class="button-container">'+form_u+form_d+'</div>'+'</td></tr>');
            var bd = document.getElementById("e"+id);
            bd.addEventListener("click", function () { delete_employee(id); });

            document.getElementById("form").reset();

          } else {
              alert('There was a problem with the request.');
          }
        }
    }

  function uuid() {
      var uuid = "", i, random;
      for (i = 0; i < 32; i++) {
          random = Math.random() * 16 | 0;
          if (i == 8 || i == 12 || i == 16 || i == 20)
              uuid += "-";
          uuid += (i == 12 ? 4 : (i == 16 ? (random & 3 | 8) : random)).toString(16);
       }
       return uuid;
  }

  function makepw(length) {
      var result           = [];
      var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789._%$&!';
      var charactersLength = characters.length;
      for ( var i = 0; i < length; i++ ) {
        result.push(characters.charAt(Math.floor(Math.random() * charactersLength)));
      }
      return result.join('');
  }

  function throwMessage(element) {
    var div = document.getElementById('result_add');
    var p1 = document.createElement('p');
    var b = document.createElement('b');
    b.appendChild(document.createTextNode("Error: "));
    p1.appendChild(b);
    p1.appendChild(document.createTextNode(element.validationMessage));
    div.replaceChildren(p1);
  }

  function checkInputValidity(name, surname, salary){
      if(name.value === ""){
          name.setCustomValidity("Empty field, please fill it");
          throwMessage(name);
          return false;
        }
        else if(surname.value === ""){
          surname.setCustomValidity("Empty field, please fill it");
          throwMessage(surname);
          return false;
        }
        else if(salary.value === ""){
          salary.setCustomValidity("Empty field, please fill it");
          throwMessage(salary);
          return false;
        }
        if(!name.validity.valid){
          throwMessage(name);
          return false;
        }
        else if(!surname.validity.valid){
          throwMessage(document.surname);
          return false;
        }
        else if(!salary.validity.valid){
          throwMessage(salary);
          return false;
        }

      return true;
  }
}