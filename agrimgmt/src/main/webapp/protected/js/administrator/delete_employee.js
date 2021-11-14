function delete_employee(id) {

  var httpRequest_e;

  var conf = confirm("Do you want to remove employee \"" + id + "\"?");
  if (conf != true) {
    return;
  }

  makeRequestDelEmployee();

  function makeRequestDelEmployee() {

    var url_e = 'http://localhost:8080/agrimgmt-1.0/protected/administrator/rest/employee/' + id;

    httpRequest_e = new XMLHttpRequest();

    if (!httpRequest_e) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }
    httpRequest_e.onreadystatechange = alertContentsE;
    httpRequest_e.open('DELETE', url_e);

    httpRequest_e.send();
  }

  function alertContentsE() {
      if (httpRequest_e.readyState === XMLHttpRequest.DONE) {

        if (httpRequest_e.status == 200) {

            var row = document.getElementById("r"+id);
            var row_elements = row.getElementsByTagName('td');

            var div = document.getElementById('result_add');
            var p1 = document.createElement('p');
            var b = document.createElement('b');
            b.appendChild(document.createTextNode("Results: "));
            p1.appendChild(b);
            p1.appendChild(document.createTextNode("Employee \"" + row_elements[1].textContent + " " + row_elements[2].textContent + "\" correctly removed"));
            div.replaceChildren(p1);

            row.remove();


        } else {
            alert('There was a problem with the request.');
        }
      }
  }

}