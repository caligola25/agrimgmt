package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.SearchEmployeeDatabase;
import it.unipd.dei.webapp.resource.Employee;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Retrieve the list of Employee from the database
 */
public final class EmployeeListServlet extends AbstractDatabaseServlet{

    /**
     * Retrieve the list of Employee from the database
     *
     * @param req
     *            the HTTP request from the client.
     * @param res
     *            the HTTP response from the server.
     *
     * @throws ServletException
     *             if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<Employee> employees = null;
        Message m = null;

        try {

            employees = new SearchEmployeeDatabase(getDataSourceR().getConnection()).employeeList();

            if (employees.isEmpty())
                m = new Message("No Employees found in the database.",
                        "E600", "The database does not contains any employee yet.");
            else
                m = new Message("Employees list correctly retrieved.");

        } catch (SQLException ex) {
            m = new Message("Cannot show employee list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("employees", employees);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/employee-list.jsp").forward(req, res);
    }
}
