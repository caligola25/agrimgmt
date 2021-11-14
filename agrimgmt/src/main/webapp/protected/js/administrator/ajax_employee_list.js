(function () {

  var bt = document.getElementById("add_emp");
  bt.addEventListener("click", function () { add_employee(); });

  var httpRequest;
  window.onload = makeRequest;

  function makeRequest() {

    var url = 'http://localhost:8080/agrimgmt-1.0/protected/administrator/rest/employee/';

    httpRequest = new XMLHttpRequest();

    if (!httpRequest) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }
    httpRequest.onreadystatechange = alertContents;
    httpRequest.open('GET', url);
    httpRequest.send();
  }

  function alertContents() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {

      if (httpRequest.status == 200) {


        var div = document.getElementById('results');
        div.setAttribute("class", "table-responsive");
        var table = document.createElement("table");
        table.setAttribute("id", "employee_table");
        table.setAttribute("class", "table")

        var thead = document.createElement('thead');

        var tr = document.createElement('tr');

        var th = document.createElement('th');
        th.appendChild(document.createTextNode('Employee ID'));
        tr.appendChild(th);

        var th = document.createElement('th');
        th.appendChild(document.createTextNode('Name'));
        tr.appendChild(th);

        var th = document.createElement('th');
        th.appendChild(document.createTextNode('Surname'));
        tr.appendChild(th);

        var th = document.createElement('th');
        th.appendChild(document.createTextNode('Role'));
        tr.appendChild(th);

        var th = document.createElement('th');
        th.appendChild(document.createTextNode('Salary'));
        tr.appendChild(th);

        var th = document.createElement('th');
        th.appendChild(document.createTextNode('Number of assigned operations'));
        tr.appendChild(th);

        var th = document.createElement('th');
        th.appendChild(document.createTextNode('Manage'));
        tr.appendChild(th);

        thead.appendChild(tr);
        table.appendChild(thead);

        var tbody = document.createElement('tbody');


        var jsonData = JSON.parse(httpRequest.responseText);
        var resource = jsonData['it.unipd.dei.webapp.resource-list'];

        for (var i = 0; i < resource.length; i++) {
          var employee = resource[i].employee;

          var tr = document.createElement('tr');
          tr.setAttribute("id", "r"+employee['employeeId']);

          var td_id = document.createElement('td');
          td_id.appendChild(document.createTextNode(employee['employeeId']));
          tr.appendChild(td_id);

          var td_name = document.createElement('td');
          td_name.appendChild(document.createTextNode(employee['name']));
          tr.appendChild(td_name);

          var td_surname = document.createElement('td');
          td_surname.appendChild(document.createTextNode(employee['surname']));
          tr.appendChild(td_surname);

          var td_role = document.createElement('td');
          td_role.appendChild(document.createTextNode(employee['role']));
          tr.appendChild(td_role);

          var td_salary = document.createElement('td');
          td_salary.appendChild(document.createTextNode(employee['salary'] + " $"));
          tr.appendChild(td_salary);

          var td_nOperation = document.createElement('td');
          td_nOperation.appendChild(document.createTextNode(employee['nOperation']));
          tr.appendChild(td_nOperation);

          var td_manage = document.createElement('td');
          //create button container div for the hidden forms
          var button_div = document.createElement('div');
          button_div.setAttribute("class", "button-container");
          //append div to table cell
          td_manage.appendChild(button_div);
            //create tooltip container
            var cont_u = document.createElement('div');
            cont_u.setAttribute("class", "tooltip");

            var form_u = document.createElement("form");
            form_u.setAttribute("method", "GET");
            form_u.setAttribute("action", "../../administrator/update-employee");
            form_u.appendChild(cont_u);

            // Create an input element for id
            var id_u = document.createElement("input");
            id_u.setAttribute("type", "hidden");
            id_u.setAttribute("name", "id");
            id_u.setAttribute("value", employee['employeeId']);

            // Create a submit button
            var button_u = document.createElement("button");
            button_u.setAttribute("type", "submit");
            var button_label = document.createElement("i");
            button_label.setAttribute("class", "far fa-edit fa-lg update")
            button_u.appendChild(button_label);

            var span_u = document.createElement('span');
            span_u.setAttribute("class", "tooltiptext");
            span_u.innerHTML = "Update";

            // Append the id input to the form
            form_u.append(id_u);

            // Append the button to the form
            button_u.append(span_u);
            cont_u.append(button_u);
            form_u.append(cont_u);

            button_div.appendChild(form_u);

            //create tooltip container
            var cont_d = document.createElement('div');
            cont_d.setAttribute("class", "tooltip");

            var form_d = document.createElement("form");
            form_d.setAttribute("method", "GET");
            form_d.setAttribute("id", "e"+employee['employeeId']);
            form_d.setAttribute("name", "emp_form");
            //form_d.setAttribute("onsubmit", "delete_employee(\""+employee['employeeId']+"\")");
            //create tooltip container
            var cont_d = document.createElement('div');
            cont_d.setAttribute("class", "tooltip");
            form_d.appendChild(cont_d);

            // Create a submit button 
            var button_d = document.createElement("button");
            button_d.setAttribute("type", "button");
            var button_label = document.createElement("i");
            button_label.setAttribute("class", "far fa-trash-alt fa-lg delete");
            button_d.appendChild(button_label);

            var span_d = document.createElement('span');
            span_d.setAttribute("class", "tooltiptext");
            span_d.innerHTML = "Delete";

            // Append the button to the form
            button_d.append(span_d);
            cont_d.append(button_d);
            form_d.append(cont_d);

            button_div.appendChild(form_d);

          tr.appendChild(td_manage);

          tbody.appendChild(tr);
        }

        table.appendChild(tbody);

        div.appendChild(table);

        var forms = document.getElementsByName("emp_form");
        forms.forEach(function(currentValue) { currentValue.addEventListener("click", function() { delete_employee(currentValue.getAttribute('id').substring(1));});});

      } else {
        alert('There was a problem with the request.');
      }
    }
  }
})();