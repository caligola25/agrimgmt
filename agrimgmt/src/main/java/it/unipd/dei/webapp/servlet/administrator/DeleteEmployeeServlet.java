package it.unipd.dei.webapp.servlet.administrator;

import it.unipd.dei.webapp.database.administrator.EmployeeDatabase;
import it.unipd.dei.webapp.resource.Employee;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Class in charge of deleting an employee entity
 */
public final class DeleteEmployeeServlet extends AbstractDatabaseServlet {

    /**
     * This method collects the employee_id and delete the corresponding employee
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UUID id = null;
        Employee e = null;

        Message m = null;

        try {
            //retrieves the request parameters
            id = UUID.fromString(req.getParameter("id"));

            //find the employee entity to remove
            e = new EmployeeDatabase(getDataSourceR().getConnection(), id).viewEmployeeById();

            //remove the selected employee from the db
            new EmployeeDatabase(getDataSourceW().getConnection(), id).deleteEmployee();

            m = new Message(String.format("The employee %s has been correctly removed", id.toString()));
        } catch (SQLException ex) {
            m = new Message("Cannot complete the update. Unexpected error while accessing the database.", "E200", ex.getMessage());
        }

        req.setAttribute("employee", e);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/administrator/delete-employee.jsp").forward(req, res);
    }
}
