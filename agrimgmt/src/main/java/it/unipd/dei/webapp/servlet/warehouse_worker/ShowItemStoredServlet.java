package it.unipd.dei.webapp.servlet.warehouse_worker;

import it.unipd.dei.webapp.database.warehouse_worker.ItemStoredDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Show all the the item stored in the warehouse.

 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class ShowItemStoredServlet extends AbstractDatabaseServlet {

    /**
     * Show all the the item stored in the warehouse.
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
    public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<List<String>> storedItems = null;
        Message m = null;

        try {
            storedItems = new ItemStoredDatabase(getDataSourceR().getConnection())
                    .showItemStored();

            if (storedItems.isEmpty())
                m = new Message("No Item stored found in the database.",
                        "E600", "The database does not contain any item stored at the moment.");
            else
                m = new Message("List of item stored correctly found");

        }catch(SQLException ex){
            m = new Message("Cannot show item stored list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("message", m);
        req.setAttribute("elements",storedItems);
        req.getRequestDispatcher("/protected/jsp/warehouse_worker/item-stored-list.jsp").forward(req, res);
    }
}
