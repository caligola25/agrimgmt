package it.unipd.dei.webapp.servlet.designer;

import it.unipd.dei.webapp.database.designer.ChangeProcessEstimatedTimeDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Process;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Change the estimated time of a process into the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class ChangeProcessEstimatedTimeServlet extends AbstractDatabaseServlet {
    /**
     * Retrieved the UUID of a process into the database.
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
        UUID processId = null;

        try {

            processId = UUID.fromString(req.getParameter("processId"));;

        } catch(Exception ex){
            m = new Message("Unable to dispatch the request.",
                    "E700", "Cannot retrieve request parameters.");
        }

        req.setAttribute("processId", processId);
        req.setAttribute("message", m);

        // forward the control to the jsp
        req.getRequestDispatcher("/protected/jsp/designer/change-process-estimated-time-form.jsp").forward(req,res);
    }

    /**
     * Update estimated time of a process into the database.
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
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, IllegalArgumentException {
        UUID processId = null;
        String estimatedTime = null;

        Message m = null;

        try{
            processId = UUID.fromString(req.getParameter("processId"));
            estimatedTime = req.getParameter("estimatedTime");

            if(estimatedTime.equals("00:00")){
                throw new IllegalArgumentException();
            }

            Process process = new ChangeProcessEstimatedTimeDatabase(getDataSourceW().getConnection(), processId, estimatedTime).
                    changeProcessEstimatedTime();

            if (process != null)
                m = new Message(String.format("Process estimated time successfully changed."));
            else
                m = new Message("No processes found to update with the inserted UUID.", "E600", "The database does not contains any process with the inserted UUID.");

        }catch (IllegalArgumentException ex) {
            m = new Message("Cannot change process estimated time. Invalid input parameters: process Id must be a UUID, estimated time must be greater than 00:00.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot change process estimated time: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
        }
        req.setAttribute("message",m);
        req.setAttribute("processId",processId);
        req.setAttribute("estimatedTime",estimatedTime);
        req.getRequestDispatcher("/protected/jsp/designer/change-process-estimated-time-result.jsp").forward(req,res);
    }
}
