package it.unipd.dei.webapp.servlet.designer;

import it.unipd.dei.webapp.database.designer.ProcessListDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Process_ProductName_MaterialName;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Shows the list of processes (with product name and material name in place of product id and material id) present in the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class ProcessListServlet extends AbstractDatabaseServlet{

    /**
     * Shows the list of processes (with product name and material name in place of product id and material id) present in the database.
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

        List<Process_ProductName_MaterialName> processes = null;
        Message m = null;

        try {

            processes = new ProcessListDatabase(getDataSourceR().getConnection()).processList();

            if (processes.isEmpty())
                m = new Message("No process found in the database.",
                        "E600", "The database does not contains any process yet.");
            else
                m = new Message("Process list correctly retrieved.");

        } catch (SQLException ex) {
            m = new Message("Cannot show product list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("processes", processes);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/designer/process-list.jsp").forward(req, res);
    }
    
}
