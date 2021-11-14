package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.SearchCustomerDatabase;
import it.unipd.dei.webapp.resource.Customer;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Search for a Customer given his UUID or his name from the database
 */
public final class SearchCustomerServlet extends AbstractDatabaseServlet {

    /**
     * Search for a Customer given his UUID or his name from the database
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
     * @throws IllegalArgumentException
     *             if any error occurs involving the parameters inserted by the user and retrieved as attribute of the request
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<Customer> customers = null;
        Message m = null;

        try {
            String sel = req.getParameter("type_search");
            switch (sel) {
                case "id":
                    customers = new SearchCustomerDatabase(getDataSourceR().getConnection(),
                            UUID.fromString(req.getParameter("word"))).searchCustomerById();
                    break;
                case "name":
                    customers = new SearchCustomerDatabase(getDataSourceR().getConnection(),
                            req.getParameter("word")).searchCustomerByName();
                    break;
                default:
                    throw new ServletException();
            }

            if (customers.isEmpty())
                m = new Message("No Customer found in the database.",
                        "E600", "The database does not contain the searched customer.");
            else
                m = new Message("Customers list correctly retrieved.");

        } catch(NullPointerException ex){
            m = new Message("Error while choosing the criterion of search.",
                    "E700", "Criterion of search not defined.");
        } catch(SQLException ex){
            m = new Message("Cannot show customers list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        } catch(IllegalArgumentException ex){
            m = new Message("Cannot show customers list: unexpected error in input parameters.",
                    "E100", "String expected for Name search." +
                    "A valid UUIDv4 expected for UUID search.");
        }

        req.setAttribute("customers", customers);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/customer-list.jsp").forward(req, res);
    }
}
