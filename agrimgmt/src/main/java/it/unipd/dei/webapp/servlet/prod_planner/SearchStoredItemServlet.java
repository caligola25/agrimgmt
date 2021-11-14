package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.SearchStoredItemDatabase;
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
 * Search for an unsold Item given his UUID from the database
 */
public final class SearchStoredItemServlet extends AbstractDatabaseServlet {

    /**
     * Search for an unsold Item with status given his UUID from the database
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

        List<List<String>> items = null;
        Message m = null;

        try {
            String sel = req.getParameter("type_search");
            switch (sel) {
                case "id":
                    items = new SearchStoredItemDatabase(getDataSourceR().getConnection(),
                            UUID.fromString(req.getParameter("word"))).searchStoredItemById();
                    break;
                case "name":
                    items = new SearchStoredItemDatabase(getDataSourceR().getConnection(),
                            req.getParameter("word")).searchStoredItemByName();
                    break;
                default:
                    throw new ServletException();
            }

            if (items.isEmpty())
                m = new Message("No Stored Items found in the database.",
                        "E600", "The database does not contain the searched stored unsold item.");
            else
                m = new Message("Stored Items list correctly retrieved.");

        } catch(NullPointerException ex){
            m = new Message("Error while choosing the criterion of search.",
                    "E700", "Criterion of search not defined.");
        } catch(SQLException ex){
            m = new Message("Cannot show stored unsold items list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        } catch(IllegalArgumentException ex){
            m = new Message("Cannot show stored items list: unexpected error in input parameters.",
                    "E100", "String expected for Name search." +
                    "A valid UUIDv4 expected for UUID search.");
        }

        req.setAttribute("items", items);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/warehouse-list.jsp").forward(req, res);
    }
}
