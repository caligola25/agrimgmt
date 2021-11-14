package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.ProductOrder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unipd.dei.webapp.database.prod_planner.DeleteProductOrderDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Removes a product order from the database.
 */
public final class DeleteProductOrderServlet extends AbstractDatabaseServlet {
    /**
     * Removes a product order from the database.
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

        UUID prodOrderId = null;
        
        Message m = null;
        ProductOrder prodOrder = null;

        try{
            prodOrderId = UUID.fromString(req.getParameter("prodOrderId"));

            prodOrder= new ProductOrder(prodOrderId, "", 0, null, null, null);

            new DeleteProductOrderDatabase(getDataSourceW().getConnection(), prodOrder).deleteProductOrder();
            m = new Message(String.format("Product Order %s successfully deleted.", prodOrderId));

        }
        catch(SQLException ex){   
            m = new Message("Cannot remove the product order: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("message",m);
        //req.setAttribute("prodOrderId", prodOrderId);
        
        req.getRequestDispatcher("/protected/jsp/prod_planner/delete-product-order-result.jsp").forward(req,res);
    }
}
