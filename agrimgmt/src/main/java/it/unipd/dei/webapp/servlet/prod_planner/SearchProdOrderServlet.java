package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.SearchProductOrderDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Search for a Production Order given his UUID, the name of the associated customer, his status or his date from the database
 */
public final class SearchProdOrderServlet extends AbstractDatabaseServlet {

    /**
     * Search for a Production Order given his UUID, the name of the associated customer, his status or his date from the database
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
     * @throws IllegalArgumentException
     *             if any error occurs involving the parameters inserted by the user and retrieved as attribute of the request
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<List<String>> prodOrders = null;
        Message m = null;

        try {
            String sel = req.getParameter("type_search");
            switch (sel) {
                case "id":
                    prodOrders = new SearchProductOrderDatabase(getDataSourceR().getConnection(),
                            UUID.fromString(req.getParameter("word"))).searchProductOrderById();
                    break;
                case "customer":
                    prodOrders = new SearchProductOrderDatabase(getDataSourceR().getConnection(),
                            req.getParameter("word")).searchProductOrderByCustomerName();
                    break;
                case "status":
                    prodOrders = new SearchProductOrderDatabase(getDataSourceR().getConnection(),
                            req.getParameter("word")).searchProductOrderByStatus();
                    break;
                case "date":
                    prodOrders = new SearchProductOrderDatabase(getDataSourceR().getConnection(),
                            Date.valueOf(req.getParameter("word"))).searchProductOrderByDate();
                    break;
                default:
                    throw new ServletException();
            }

            if (prodOrders.isEmpty())
                m = new Message("No Production Orders found in the database.",
                        "E600", "The database does not contain the searched production orders.");
            else
                m = new Message("Production Orders list correctly retrieved.");

        } catch(NullPointerException ex){
            m = new Message("Error while choosing the criterion of search.",
                    "E700", "Criterion of search not defined.");
        } catch(SQLException ex){
            m = new Message("Cannot show production orders list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        } catch(IllegalArgumentException ex){
            m = new Message("Cannot show production orders list: unexpected error in input parameters.",
                    "E100", "String expected for Customer and Status search. " +
                    "A valid UUIDv4 expected for UUID search. String with format [YYYY]-[MM]-[DD] expected for Date search.");
        }

        req.setAttribute("prodOrders", prodOrders);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/prod-order-list.jsp").forward(req, res);
    }
}
