package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.SearchItemDatabase;
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
 * Retrieve a list of items related to a specific production order
 */
public final class ControlItemServlet extends AbstractDatabaseServlet{

    /**
     * Retrieve a list of items related to a specific production order
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

        List<List<String>> items = null;
        Message m = null;

        try {

            items = new SearchItemDatabase(getDataSourceR().getConnection(), UUID.fromString(req.getParameter("prodOrderId"))).searchItemNameByProdOrderId();

            if (items.isEmpty())
                m = new Message("No Items found in the database.",
                        "E600", "The database does not contains any items for this production order.");
            else
                m = new Message("Items list correctly retrieved.");

        } catch (SQLException ex) {
            m = new Message("Cannot show items list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("items", items);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/control-items-in-production.jsp").forward(req, res);
    }
}
