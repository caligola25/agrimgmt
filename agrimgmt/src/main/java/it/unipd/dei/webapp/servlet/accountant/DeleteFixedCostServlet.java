package it.unipd.dei.webapp.servlet.accountant;

import it.unipd.dei.webapp.database.accountant.SearchFixedCostDatabase;
import it.unipd.dei.webapp.database.accountant.DeleteFixedCostDatabase;
import it.unipd.dei.webapp.resource.FixedCost;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Class in charge of deleting a fixed cost entity
 */
public final class DeleteFixedCostServlet extends AbstractDatabaseServlet {

    /**
     * This method collects the date and type of the fixed cost to delete and remove it from the database
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = null;
        Date date = null;
        FixedCost fc = null;

        Message m = null;

        try {
            //retrieves the request parameters
            type = req.getParameter("type_search");
            date = Date.valueOf(req.getParameter("date_search"));

            //find the fixed cost entity to remove
            fc = new SearchFixedCostDatabase(getDataSourceR().getConnection(), type, date).searchFixedCost();

            //remove the selected fixed cost from the db
            new DeleteFixedCostDatabase(getDataSourceW().getConnection(), type, date).deleteFixedCost();

            m = new Message(String.format("The fixed cost of type %s, of date %s has been correctly removed", type, date.toString()));
        } catch (SQLException ex) {
            m = new Message("Cannot complete the update. Unexpected error while accessing the database.", "E200", ex.getMessage());
        }

        req.setAttribute("fixedCost", fc);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/accountant/delete-fixed-cost.jsp").forward(req, res);
    }
}
