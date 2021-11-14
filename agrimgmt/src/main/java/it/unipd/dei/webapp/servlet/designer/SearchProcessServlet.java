package it.unipd.dei.webapp.servlet.designer;

import it.unipd.dei.webapp.database.designer.SearchProcessByProductIdDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Process_ProductName_MaterialName;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Search the processes (with product name and material name in place of product id and material id) to build a specific product in the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class SearchProcessServlet extends AbstractDatabaseServlet {

    /**
     * Search the processes (with product name and material name in place of product id and material id) to build a specific product in the database.
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
            processes = new SearchProcessByProductIdDatabase(getDataSourceR().getConnection(),
                    UUID.fromString(req.getParameter("product_id"))).searchProcessByProductId();

            if (processes.isEmpty())
                m = new Message("No product found in the database with that UUID.", "E600", "The database does not contains any product with the inserted UUID.");
            else
                m = new Message("Processes list correctly retrieved.");

        }catch (IllegalArgumentException ex) {
            m = new Message("Cannot show the processes list. Invalid input parameters: product_id must be a UUID.",
                    "E100", ex.getMessage());
        } catch(SQLException ex){
            m = new Message("Cannot show processes list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("processes", processes);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/designer/search-process.jsp").forward(req, res);
    }
}
