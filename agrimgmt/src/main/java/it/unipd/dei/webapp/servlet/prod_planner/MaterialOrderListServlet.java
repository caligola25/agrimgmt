package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.MaterialOrderListDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Retrieve the list of Material Orders from the database
 */
public final class MaterialOrderListServlet extends AbstractDatabaseServlet {

    /**
     * Retrieve the list of Material Orders from the database
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

        List<List<String>> materialOrders = null;
        Message m = null;

        try {

            materialOrders = new MaterialOrderListDatabase(getDataSourceR().getConnection()).materialOrderList();

            if (materialOrders.isEmpty())
                m = new Message("No material orders found in the database.",
                        "E600", "The database does not contains any material order yet.");
            else
                m = new Message("Material orders list correctly retrieved.");

        } catch (SQLException ex) {
            m = new Message("Cannot show material orders list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("materialOrders", materialOrders);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/material-orders-list.jsp").forward(req, res);
    }
}
