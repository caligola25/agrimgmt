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
 * Class in charge of inserting a new employee entity
 */
public final class AddEmployeeServlet extends AbstractDatabaseServlet {

    /**
     * This method collect all the information needed to insert a new employee in the database
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UUID id = null;
        String name = null;
        String surname = null;
        String role = null;
        float salary = -1;
        int nOperation = 0;

        Employee employee = null;
        Message m = null;

        try {
            id = UUID.randomUUID(); // generate a random UUID

            //retrieves the request parameters
            name = req.getParameter("name");
            surname = req.getParameter("surname");
            role = req.getParameter("role");
            salary = Float.parseFloat(req.getParameter("salary"));

            //chek the received parameters
            if (salary < 0) {
                throw new SQLException("The inserted value is smaller than 0", "42604");
            }

            //store the new fixed cost in the db
            employee = new Employee(id, surname, name, nOperation, salary, role);
            new EmployeeDatabase(getDataSourceW().getConnection(), employee).addEmployee();

            m = new Message(String.format("The employee %s has been successfully added to the system", id.toString()));
        } catch (NumberFormatException ex) {
            m = new Message("Cannot complete the insertion. Invalid input parameter: salary must be a number.", "E100", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot complete the insertion. Invalid input parameters", "E100", ex.getMessage());
        } catch (SQLException ex) {
            switch (ex.getSQLState()) {
                case "23505":
                    m = new Message(String.format("Cannot complete the insertion. Employee %s already exist.", id.toString()), "E300", ex.getMessage());
                    break;
                case "23528":
                    m = new Message(String.format("Cannot complete the insertion. The role %s is not allowed, the accepted ones are: Administrator, Warehouse worker, Production line worker, Accountant, Designer, Production planner", role), "E500", ex.getMessage());
                    break;
                case "42604":
                    m = new Message("Cannot complete the insertion. The salary value must be greater or equal than 0", "E500", ex.getMessage());
                    break;
                default:
                    m = new Message("Cannot complete the insertion. Unexpected error while accessing the database", "E200", ex.getMessage());
                    break;
            }
        }

        req.setAttribute("employee", employee);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/administrator/add-employee-result.jsp").forward(req, res);
    }
}