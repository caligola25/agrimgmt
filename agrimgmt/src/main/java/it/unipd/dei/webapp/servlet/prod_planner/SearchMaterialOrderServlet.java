package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.SearchMaterialOrderDatabase;
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
 * Search for a Material Order given his UUID, the name of the associated supplier, his status or his date from the database
 */
public final class SearchMaterialOrderServlet extends AbstractDatabaseServlet {

    /**
     * Search for a Material Order given his UUID, the name of the associated supplier, his status or his date from the database
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

        List<List<String>> materialOrders = null;
        Message m = null;

        try {
            String sel = req.getParameter("type_search");
            switch (sel) {
                case "id":
                    materialOrders = new SearchMaterialOrderDatabase(getDataSourceR().getConnection(),
                            UUID.fromString(req.getParameter("word"))).searchMaterialOrderById();
                    break;
                case "supplier":
                    materialOrders = new SearchMaterialOrderDatabase(getDataSourceR().getConnection(),
                            req.getParameter("word")).searchMaterialOrderBySupplierName();
                    break;
                case "status":
                    materialOrders = new SearchMaterialOrderDatabase(getDataSourceR().getConnection(),
                            req.getParameter("word")).searchMaterialOrderByStatus();
                    break;
                case "date":
                    materialOrders = new SearchMaterialOrderDatabase(getDataSourceR().getConnection(),
                            Date.valueOf(req.getParameter("word"))).searchMaterialOrderByDate();
                    break;
                default:
                    throw new ServletException();
            }

            if (materialOrders.isEmpty())
                m = new Message("No Material Orders found in the database.",
                        "E600", "The database does not contain the searched material orders.");
            else
                m = new Message("Material Orders list correctly retrieved.");

        } catch(NullPointerException ex){
            m = new Message("Error while choosing the criterion of search.",
                    "E700", "Criterion of search not defined.");
        } catch(SQLException ex){
            m = new Message("Cannot show material orders list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        } catch(IllegalArgumentException ex){
            m = new Message("Cannot show material orders list: unexpected error in input parameters.",
                    "E100", "String expected for Supplier and Status search. " +
                    "A valid UUIDv4 expected for UUID search. String with format [YYYY]-[MM]-[DD] expected for Date search.");
        }

        req.setAttribute("materialOrders", materialOrders);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/material-orders-list.jsp").forward(req, res);
    }
}
