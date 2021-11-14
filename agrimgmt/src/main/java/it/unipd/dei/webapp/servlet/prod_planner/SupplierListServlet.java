package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.warehouse_worker.ListSupplierDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Supplier;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Retrieve the list of Supplier from the database
 */
public final class SupplierListServlet extends AbstractDatabaseServlet{

    /**
     * Retrieve the list of Supplier from the database
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

        List<Supplier> suppliers = null;
        Message m = null;

        try {

            suppliers = new ListSupplierDatabase(getDataSourceR().getConnection()).listSupplierFull();

            if (suppliers.isEmpty())
                m = new Message("No Supplier found in the database.",
                        "E600", "The database does not contains any supplier yet.");
            else
                m = new Message("Supplier list correctly retrieved.");

        } catch (SQLException ex) {
            m = new Message("Cannot show supplier list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("suppliers", suppliers);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/supplier-list.jsp").forward(req, res);
    }
    
}
