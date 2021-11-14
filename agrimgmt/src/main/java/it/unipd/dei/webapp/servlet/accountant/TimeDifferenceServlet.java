package it.unipd.dei.webapp.servlet.accountant;

import it.unipd.dei.webapp.database.accountant.TimeDifferenceDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.TimeDifference;

import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class in charge of retrieving all the information needed to build the time difference overview page
 */
public final class TimeDifferenceServlet extends AbstractDatabaseServlet {

    /**
     * This method retrieves all the TimeDifference resources needed to build the Time difference overview page
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<TimeDifference> td = null;
        Message m = null;

        try {
            td = new TimeDifferenceDatabase(getDataSourceR().getConnection()).retrieveTimeDifference();

            m = new Message("Procedure completed correctly");
        } catch (SQLException ex) {
            m = new Message("Cannot complete the operation: error while accessing the database)", "E200", ex.getMessage());
        }

        // JSP FORWARDING
        req.setAttribute("timeDifference", td);
        req.setAttribute("message", m);

        // forward the control to the jsp
        req.getRequestDispatcher("/protected/jsp/accountant/time-difference-analysis.jsp").forward(req, res);
    }
}