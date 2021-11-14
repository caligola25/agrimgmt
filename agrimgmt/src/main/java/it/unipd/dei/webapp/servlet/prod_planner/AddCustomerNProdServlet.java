package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.SearchCustomerDatabase;
import it.unipd.dei.webapp.database.prod_planner.SearchProductDatabase;
import it.unipd.dei.webapp.resource.Customer;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Product;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Collect the name of the Customer and the number of different products of a new production order
 */
public final class AddCustomerNProdServlet extends AbstractDatabaseServlet {

    /**
     * Collect the name of the Customer and the number of different products of a new production order
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
        String customerName = null;
        int numProd = 0;

        // model
        List<Customer> customers = null;
        List<Product> products = null;
        Customer customer = null;
        Message m = null;

        try{
            customerName = req.getParameter("customerName");
            numProd = Integer.parseInt(req.getParameter("numProd"));

            customers = new SearchCustomerDatabase(getDataSourceR().getConnection(), customerName)
                                        .searchCustomerByName();
            customer = customers.get(0);
            m = new Message(String.format("Customer \"%s\" found.", customer.getName()));

            req.setAttribute("customer", customer.getName());
            req.setAttribute("numProd", numProd);

        } catch (SQLException ex) {
            m = new Message("Cannot insert a new product order: unexpected error while accessing the database.", "E200", ex.getMessage());
        } catch (NumberFormatException ex) {
            m = new Message("Cannot insert a new product order. Invalid input parameters: number of products must be an integer.", "E100", ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            m = new Message(String.format("Customer \"%s\" not found in the database", customerName), "E600", "Please, create the new " +
                    "customer before inserting his new production order.");
        }

        try {
            products = new SearchProductDatabase(getDataSourceR().getConnection()).searchProduct();

        } catch (SQLException ex) {
            m = new Message("Cannot insert a new product order: unexpected error while accessing the database while retrieving product order names.", "E200", ex.getMessage());
        }

        req.setAttribute("products", products);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/insert-item-in-production-order.jsp").forward(req, res);
    }
}
