package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.ProductOrderListDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Retrieve a list of production orders with status 'in production'
 */
public final class ControlProdOrderServlet extends AbstractDatabaseServlet{

    /**
     * Retrieve a list of production orders with status 'in production'
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

        List<List<String>> prodOrders = null;
        Message m = null;

        try {

            prodOrders = new ProductOrderListDatabase(getDataSourceR().getConnection()).productOrderListInProduction();

            if (prodOrders.isEmpty())
                m = new Message("No Production Orders found in the database.",
                        "E600", "The database does not contains any production order in production yet.");
            else
                m = new Message("Production Orders list correctly retrieved.");

        } catch (SQLException ex) {
            m = new Message("Cannot show production orders list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("prodOrders", prodOrders);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/control-order-in-prod.jsp").forward(req, res);
    }
}
