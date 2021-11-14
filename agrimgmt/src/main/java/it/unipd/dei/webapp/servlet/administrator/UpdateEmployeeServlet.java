package it.unipd.dei.webapp.servlet.administrator;

import it.unipd.dei.webapp.database.administrator.EmployeeDatabase;
import it.unipd.dei.webapp.database.administrator.RetrieveRolesDatabase;
import it.unipd.dei.webapp.resource.*;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Class in charge of updating an already existing employee
 */
public final class UpdateEmployeeServlet extends AbstractDatabaseServlet {

    /**
     * This method has the function of retrieving from the database all the information needed to correctly display
     * the employee update page:
     * <ul>
     *     <li>
     *         Collects the employee_id to uniquely identifying the correspondent employee and retrieve all of its
     *         information from the database
     *     </li>
     *     <li>
     *         Retrieve from the database all the allowed roles
     *     </li>
     * </ul>
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Employee e = null;
        UUID id = null;
        Message m = null;
        List<String> roles = null;

        try{
            id = UUID.fromString(req.getParameter("id"));

            e = new EmployeeDatabase(getDataSourceR().getConnection(), id).viewEmployeeById();

            roles = new RetrieveRolesDatabase(getDataSourceR().getConnection()).retrieveRoles();

            m = new Message("Search of the requested data ended successfully");
        } catch (SQLException ex) {
            m = new Message("Cannot complete the research: error while accessing the database)", "E200", ex.getMessage());
        }

        // JSP FORWARDING
        req.setAttribute("employee", e);
        req.setAttribute("roles", roles);
        req.setAttribute("message", m);

        // forward the control to the jsp
        req.getRequestDispatcher("/protected/jsp/administrator/update-employee.jsp").forward(req, res);
    }

    /**
     * This method is actually in charge of updating the employee entity based on the received parameters:
     * <ul>
     *     <li>
     *         Collects the employee_id of the employee entity that the user wants to modify plus the parameters
     *         that need to be changed
     *     </li>
     *     <li>
     *         Finds the correct employee and modifies it following the user instruction
     *     </li>
     * </ul>
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
        Employee e = null;
        Employee newE = null;

        Message m = null;

        try {
            //retrieves the request parameters
            if(!req.getParameter("id").equals("")) {
                id = UUID.fromString(req.getParameter("id"));
            }
            if(!req.getParameter("name").equals("")) {
                name = req.getParameter("name");
            }
            if(!req.getParameter("surname").equals("")) {
                surname = req.getParameter("surname");
            }
            if(!req.getParameter("role").equals("")) {
                role = req.getParameter("role");
            }
            if(!req.getParameter("salary").equals("")) {
                salary = Float.parseFloat(req.getParameter("salary"));
            }

            e = new EmployeeDatabase(getDataSourceR().getConnection(), id).viewEmployeeById();

            //chek the received parameters
            if (salary < 0 && salary != -1) {
                throw new SQLException("The inserted value is smaller than 0", "42604");
            }

            //update the selected employee in the db
            if (name == null) {
                name = e.getName();
            }
            if (surname == null) {
                surname = e.getSurname();
            }
            if (role == null) {
                role = e.getRole();
            }
            if (salary == -1) {
                salary = e.getSalary();
            }

            newE = new EmployeeDatabase(getDataSourceW().getConnection(),
                    new Employee(e.getEmployeeId(), surname, name, e.getnOperation(), salary, role)).updateEmployee();

            m = new Message(String.format("The employee %s has been correctly updated", id.toString()));
        } catch (NumberFormatException ex) {
            m = new Message("Cannot complete the update. Invalid input parameter: salary must be a number.", "E100", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot complete the update. Invalid input parameters", "E100", ex.getMessage());
        } catch (SQLException ex) {
            switch (ex.getSQLState()) {
                case "23528":
                    m = new Message(String.format("Cannot complete the update. The role %s is not allowed, the accepted ones are: Administrator, Warehouse worker, Production line worker, Accountant, Designer, Production planner", role), "E500", ex.getMessage());
                    break;
                case "42604":
                    m = new Message("Cannot complete the update. The salary value must be greater or equal than 0", "E500", ex.getMessage());
                    break;
                default:
                    m = new Message("Cannot complete the update. Unexpected error while accessing the database.", "E200", ex.getMessage());
                    break;
            }
        }

        req.setAttribute("newEmployee", newE);
        req.setAttribute("employee", e);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/administrator/update-employee.jsp").forward(req, res);
    }
}