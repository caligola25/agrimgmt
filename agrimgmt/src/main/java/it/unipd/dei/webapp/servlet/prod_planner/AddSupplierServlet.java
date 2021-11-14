package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.AddSupplierDatabase;
import it.unipd.dei.webapp.resource.Supplier;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Adds a new supplying into the database.
 */
public final class AddSupplierServlet extends AbstractDatabaseServlet {

    /**
     * Adds a new supplying into the database.
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
            new AddSupplierDatabase(getDataSourceW().getConnection(), s).createSupplier();
            m = new Message(String.format("Supplier %s successfully added.", supplier_name));

        }
        catch (IllegalArgumentException ex){
            m = new Message("Cannot add the supplier. Invalid input parameters: all fields must not be empty.",
                    "E100", ex.getMessage());
        }
        catch(SQLException ex){   
            m = new Message("Cannot add the supplying: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("messages",m);
        req.setAttribute("supplier", s);
        
        req.getRequestDispatcher("/protected/jsp/prod_planner/insert-supplier-result.jsp").forward(req,res);
    }
}
