package it.unipd.dei.webapp.servlet.warehouse_worker;

import it.unipd.dei.webapp.database.warehouse_worker.UpdateStatusItemDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Item;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Modify the status of an item.

 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class UpdateStatusItemServlet extends AbstractDatabaseServlet {
    /**
     * Update status of item into the database.
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
        UUID item_id = null;
        String item_status = null;

        Message m = null;

        try{
            item_id = UUID.fromString(req.getParameter("item_id"));
            item_status = req.getParameter("status");

            Item item = new UpdateStatusItemDatabase(getDataSourceW().getConnection(),item_id, item_status).
                    updateItemStatus();

            if (item == null)
                m = new Message("Cannot upload the item because item searched doesn't exist","UPDATE 0","CHANGE ITEM ID");
            else
                m = new Message(String.format("Item status successfully upload."));

            req.setAttribute("message",m);
            req.setAttribute("itemId",item_id);
            req.setAttribute("itemStatus",item_status);

        }catch (IllegalArgumentException ex) {
            m = new Message("Cannot upload item status. Invalid input parameters: item_id must be a UUID.",
                    "E100", ex.getMessage());

        } catch (SQLException ex) {
            if (ex.getSQLState().equals("23505")) {
                m = new Message(String.format("Cannot upload status"),
                        "E300", ex.getMessage());

            }else {
                m = new Message("Cannot upload status: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
            }
        }
        req.setAttribute("message",m);
        req.getRequestDispatcher("/protected/jsp/warehouse_worker/result-update-status-item.jsp").forward(req,res);
    }
}
