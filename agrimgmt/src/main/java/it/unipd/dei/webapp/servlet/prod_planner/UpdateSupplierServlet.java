package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.UpdateSupplierDatabase;
import it.unipd.dei.webapp.resource.*;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Updates a supplier stored in the database.
 */
public final class UpdateSupplierServlet extends AbstractDatabaseServlet{

    /**
     * Creates a page for supplier update.
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
        Message m = null;
        String supName = null;
        String supCountry = null;

        try {

            supName = req.getParameter("name");
            supCountry = req.getParameter("country");

        } catch(Exception ex){
            m = new Message("Unable to dispatch the request.",
                    "E700", "Cannot retrieve request parameters.");
        }

        req.setAttribute("supName", supName);
        req.setAttribute("supCountry", supCountry);
        req.setAttribute("message", m);

        // forward the control to the jsp
        req.getRequestDispatcher("/protected/jsp/prod_planner/update-supplier.jsp").forward(req, res);
    }

    /**
     * Updates a supplier stored in the database.
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
     * @throws NullPointerException
     *             if an inserted parameter by a user is null
     * @throws IllegalArgumentException
     *             if any error occurs while processing the inserted parameters
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = null;
        String oldName = null;
        String newName = null;
        String oldCountry = null;
        String newCountry = null;


        Supplier oldSupplier = null;
        Supplier updatedSupplier = null;

        Message m = null;

        try {
            //retrieves the request parameters
            type = req.getParameter("type");
            oldName = req.getParameter("oldName");
            oldCountry = req.getParameter("oldCountry");
            oldSupplier = new Supplier(oldName, oldCountry);
            newName = req.getParameter("newName");
            newCountry = req.getParameter("newCountry");

            switch (type) {
                case "name":
                    new UpdateSupplierDatabase(getDataSourceW().getConnection(), oldName, newName, oldCountry).updateSupplierName();
                    updatedSupplier = new Supplier(newName, oldCountry);
                    break;
                case "country":
                    new UpdateSupplierDatabase(getDataSourceW().getConnection(), oldName, newName, newCountry).updateSupplierCountry();
                    updatedSupplier = new Supplier(oldName, newCountry);
                    break;
                case "all":
                new UpdateSupplierDatabase(getDataSourceW().getConnection(), oldName, newName, newCountry).updateSupplierAll();
                    updatedSupplier = new Supplier(newName, newCountry);
                    break;
            }
            
            m = new Message("The Supplier has been correctly updated. ");
        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot complete the update. Invalid input parameter.", "E100", ex.getMessage());
        } catch (NullPointerException ex) {
            m = new Message("Cannot complete the update. Type of update must be chosen.", "E100", ex.getMessage());
        } catch (NoSuchElementException ex) {
            m = new Message("Cannot complete the update. Invalid input parameter: chosen supplier name does not exist.", "E100", ex.getMessage());
        } catch (SQLException ex) {
            switch (ex.getSQLState()) {
                case "23503":
                    m = new Message(String.format("Cannot complete the update. The name %s doesn't correspond to any supplier.", oldName), "E400", ex.getMessage());
                    break;
                default:
                    m = new Message("Cannot complete the update. Unexpected error while accessing the database.", "E200", ex.getMessage());
                    break;
            }
        }

        req.setAttribute("oldSupplier", oldSupplier);
        req.setAttribute("updatedSupplier", updatedSupplier);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/update-supplier-result.jsp").forward(req, res);
    }
}
