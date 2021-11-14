package it.unipd.dei.webapp.servlet.designer;

import it.unipd.dei.webapp.database.designer.ProductListDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Product;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Shows the list of products present in the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class ProductListServlet extends AbstractDatabaseServlet{

    /**
     * Shows the list of products present in the database.
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

        List<Product> products = null;
        Message m = null;

        try {

            products = new ProductListDatabase(getDataSourceR().getConnection()).productList();

            if (products.isEmpty())
                m = new Message("No Product found in the database.",
                        "E600", "The database does not contains any product yet.");
            else
                m = new Message("Product list correctly retrieved.");

        } catch (SQLException ex) {
            m = new Message("Cannot show product list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("products", products);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/designer/product-list.jsp").forward(req, res);
    }
    
}
