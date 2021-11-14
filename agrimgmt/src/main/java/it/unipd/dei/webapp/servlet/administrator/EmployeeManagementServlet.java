package it.unipd.dei.webapp.servlet.administrator;

import it.unipd.dei.webapp.database.administrator.EmployeeDatabase;
import it.unipd.dei.webapp.database.administrator.RetrieveRolesDatabase;
import it.unipd.dei.webapp.resource.Employee;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class in charge of retrieving all the needed information to build the employee management page
 */
public final class EmployeeManagementServlet extends AbstractDatabaseServlet {

    /**
     * This method retrieves all the needed information to build the employee management page:
     * <ul>
     *     <li>
     *         All the employee in the database
     *     </li>
     *     <li>
     *         All the allowed roles
     *     </li>
     * </ul>
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Message m;
        List<Employee> e = null;
        List<String> r = null;

        try{
            e = new EmployeeDatabase(getDataSourceR().getConnection()).viewEmployee();

            r = new RetrieveRolesDatabase(getDataSourceR().getConnection()).retrieveRoles();

            m = new Message("Search of the requested data ended successfully");
        } catch (SQLException ex) {
            m = new Message("Cannot complete the research: error while accessing the database)", "E200", ex.getMessage());
        }

        // JSP FORWARDING
        req.setAttribute("employees", e);
        req.setAttribute("roles", r);
        req.setAttribute("message", m);

        // forward the control to the jsp
        req.getRequestDispatcher("/protected/jsp/administrator/employee-management.jsp").forward(req, res);
    }
}
