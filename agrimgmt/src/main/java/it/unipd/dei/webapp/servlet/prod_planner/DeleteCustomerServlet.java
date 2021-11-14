package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import it.unipd.dei.webapp.resource.Customer;
import it.unipd.dei.webapp.resource.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unipd.dei.webapp.database.prod_planner.DeleteCustomerDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Removes a customer from the database.
 */
public final class DeleteCustomerServlet extends AbstractDatabaseServlet{
    /**
     * Removes a customer from the database.
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

        UUID customerId = null;
        String name = null;
        String country = null;
        String city = null;
        String street = null;
        
        Message m = null;
        Customer customer = null;

        try{
            customerId = UUID.fromString(req.getParameter("id"));
            name = req.getParameter("name");
            country = req.getParameter("country");
            city = req.getParameter("city");
            street = req.getParameter("street");

            customer = new Customer(customerId, name, country, city, street);

            new DeleteCustomerDatabase(getDataSourceW().getConnection(), customer).deleteCustomer();
            m = new Message(String.format("Customer %s successfully added.", name));

        }
        catch(SQLException ex){   
            m = new Message("Cannot remove the customer: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("message",m);
        req.setAttribute("customer", customer);
        
        req.getRequestDispatcher("/protected/jsp/prod_planner/delete-customer-result.jsp").forward(req,res);
    }
}
