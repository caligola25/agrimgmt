package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.SearchProdPhaseDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Retrieve a list of employees related to a specific production phase
 */
public final class ControlEmployeeServlet extends AbstractDatabaseServlet{

    /**
     * Retrieve a list of employees related to a specific production phase
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

        List<List<String>> prodPhases = null;
        Message m = null;

        try {

            prodPhases = new SearchProdPhaseDatabase(getDataSourceR().getConnection(), UUID.fromString(req.getParameter("employeeId"))).searchProdPhaseByEmployeeId();

            if (prodPhases.isEmpty())
                m = new Message("No Production Phase found in the database.",
                        "E600", "The database does not contains any production phase for this employee ID.");
            else
                m = new Message("Production Phases correctly retrieved.");

        } catch (SQLException ex) {
            m = new Message("Cannot show employee information: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("prodPhases", prodPhases);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/control-employee.jsp").forward(req, res);
    }
}
