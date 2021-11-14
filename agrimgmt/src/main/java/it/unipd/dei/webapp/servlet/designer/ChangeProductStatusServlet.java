package it.unipd.dei.webapp.servlet.designer;

import it.unipd.dei.webapp.database.designer.ChangeProductStatusDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Product;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Changes the available status of a product into the database
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class ChangeProductStatusServlet extends AbstractDatabaseServlet {
    /**
     * Updates the available status of a product into the database.
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
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        UUID productId = null;
        boolean available = false;

        Message m = null;

        try{
            productId = UUID.fromString(req.getParameter("productId"));
            available = Boolean.parseBoolean(req.getParameter("available"));

            Product product = new ChangeProductStatusDatabase(getDataSourceW().getConnection(), productId, available).
                    changeProductStatus();

            if (product != null)
                m = new Message(String.format("Product status successfully changed."));
            else
                m = new Message("No processes found to update with the inserted UUID.", "E600", "The database does not contains any process with the inserted UUID.");

        }catch (IllegalArgumentException ex) {
            m = new Message("Cannot change product status. Invalid input parameters: Product Id must be a UUID.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot change status: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
        }
        req.setAttribute("message",m);
        req.setAttribute("productId",productId);
        req.setAttribute("available",available);
        req.getRequestDispatcher("/protected/jsp/designer/change-product-status-result.jsp").forward(req,res);
    }
}
