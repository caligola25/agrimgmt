package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.AddCustomerDatabase;
import it.unipd.dei.webapp.database.prod_planner.SearchCustomerDatabase;
import it.unipd.dei.webapp.resource.Customer;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Creates a new customer into the database.
 */
public final class AddCustomerServlet extends AbstractDatabaseServlet {

    /**
     * Creates a customer into the database.
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
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // request parameters
        UUID customerId = null;
        String name = null;
        String street = null;
        String city = null;
        String country = null;

        // model
        Customer customer = null;
        List<Customer> customers = null;
        Message m = null;

        try {
            // retrieves the request parameters
            customerId = UUID.randomUUID();
            name = req.getParameter("name");
            street = req.getParameter("street");
            city = req.getParameter("city");
            country = req.getParameter("country");

            customers = new SearchCustomerDatabase(getDataSourceR().getConnection(), name)
                    .searchCustomerByName();

            if (!customers.isEmpty()) {
                throw new SQLException("Error while adding a new customer.", "23505");
            }

            // creates a new customer from the request parameters
            customer = new Customer(customerId, name, country, city, street);

            // creates a new object for accessing the database and stores the customer
            new AddCustomerDatabase(getDataSourceW().getConnection(), customer).createCustomer();

            m = new Message(String.format("Customer \"%s\" successfully added.", name));

        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot add the customer. Invalid input parameters: they must be strings.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("23505")) {
                m = new Message(String.format("Cannot add the Customer: customer \"%s\" already exists.", name),
                        "E300", ex.getMessage());
            } else {
                m = new Message("Cannot add the customer: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
            }
        }

        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/insert-new-customer-result.jsp").forward(req, res);
    }
}
