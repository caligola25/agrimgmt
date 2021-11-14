package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.UpdateCustomerDatabase;
import it.unipd.dei.webapp.resource.*;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.util.NoSuchElementException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Updates a customer stored in the database.
 */
public final class UpdateCustomerServlet extends AbstractDatabaseServlet {

    /**
     * Creates a page for customer update.
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

        UUID customerId = null;
        String name = null;
        String country = null;
        String city = null;
        String street = null;

        Customer customer = null;

        try {
            customerId = UUID.fromString(req.getParameter("id"));
            name = req.getParameter("name");
            country = req.getParameter("country");
            city = req.getParameter("city");
            street = req.getParameter("street");

            customer = new Customer(customerId, name, country, city, street);

        } catch(Exception ex){
            m = new Message("Unable to dispatch the request.",
                    "E700", "Cannot retrieve request parameters.");
        }

        req.setAttribute("customer", customer);
        req.setAttribute("message", m);
        
        // forward the control to the jsp
        req.getRequestDispatcher("/protected/jsp/prod_planner/update-customer.jsp").forward(req, res);
    }

    /**
     * Updates a customer stored in the database.
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

        UUID customerId = null;
        String oldName = null;
        String oldCountry = null;
        String oldCity = null;
        String oldStreet = null;

        String newName = null;
        String country = null;
        String city = null;
        String street = null;

        Customer oldCustomer = null;
        Customer updatedCustomer = null;

        Message m = null;

        try {
            //retrieves the request parameters
            type = req.getParameter("type");

            customerId = UUID.fromString(req.getParameter("id"));
            oldName = req.getParameter("name");
            oldCountry = req.getParameter("country");
            oldCity = req.getParameter("city");
            oldStreet = req.getParameter("street");
            oldCustomer = new Customer(customerId, oldName, oldCountry, oldCity, oldStreet);

            newName = req.getParameter("newName");
            country = req.getParameter("newCountry");
            city = req.getParameter("newCity");
            street = req.getParameter("newStreet");

            switch (type) {
                case "name":
                    new UpdateCustomerDatabase(getDataSourceW().getConnection(), customerId, newName, country, city, street).updateCustomerName();
                    updatedCustomer = new Customer(customerId, newName, oldCustomer.getCountry(), oldCustomer.getCity(), oldCustomer.getStreet());
                    break;
                case "address":
                    new UpdateCustomerDatabase(getDataSourceW().getConnection(), customerId, oldName, country, city, street).updateCustomerAddress();
                    updatedCustomer = new Customer(customerId, oldName, country, city, street);
                    break;
                case "all":
                    new UpdateCustomerDatabase(getDataSourceW().getConnection(), customerId, newName, country, city, street).updateCustomerAll();
                    updatedCustomer = new Customer(customerId, newName, country, city, street);
                    break;
            }
            
            m = new Message(String.format("The Customer %s has been correctly updated. ", customerId));
        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot complete the update. Invalid input parameter.", "E100", ex.getMessage());
        } catch (NullPointerException ex) {
            m = new Message("Cannot complete the update. Type of update must be chosen.", "E100", ex.getMessage());
        } catch (NoSuchElementException ex) {
            m = new Message("Cannot complete the update. Invalid input parameter: chosen customer name does not exist.", "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot complete the update. Unexpected error while accessing the database.", "E200", ex.getMessage());
        }

        req.setAttribute("oldCustomer", oldCustomer);
        req.setAttribute("updatedCustomer", updatedCustomer);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/update-customer-result.jsp").forward(req, res);
    }
}