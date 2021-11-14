package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import it.unipd.dei.webapp.resource.Supplier;
import it.unipd.dei.webapp.resource.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unipd.dei.webapp.database.prod_planner.DeleteSupplierDatabase;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Removes a supplier from the database.
 */
public final class DeleteSupplierServlet extends AbstractDatabaseServlet{

    /**
     * Removes a supplier from the database.
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

        String supplier_name = null;
        String supplier_country = null;
        
        Message m = null;
        Supplier s = null;

        try{
            supplier_name = req.getParameter("name");
            supplier_country = req.getParameter("country");

            s = new Supplier(supplier_name, supplier_country);

            s = new Supplier(supplier_name, supplier_country);
            new DeleteSupplierDatabase(getDataSourceW().getConnection(), s).deleteSupplier();
            m = new Message(String.format("Supplier %s successfully added.", supplier_name));

        }
        catch(SQLException ex){   
            m = new Message("Cannot remove the supplier: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("message",m);
        req.setAttribute("supplier", s);
        
        req.getRequestDispatcher("/protected/jsp/prod_planner/delete-supplier-result.jsp").forward(req,res);
    }
}
