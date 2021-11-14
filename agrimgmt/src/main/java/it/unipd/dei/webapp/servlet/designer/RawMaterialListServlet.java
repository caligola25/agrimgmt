package it.unipd.dei.webapp.servlet.designer;

import it.unipd.dei.webapp.database.prod_planner.ListRawMaterialDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.RawMaterial;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Shows the list of raw materials present in the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class RawMaterialListServlet extends AbstractDatabaseServlet{

    /**
     * Shows the list of raw materials present in the database.
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

        List<RawMaterial> materials = null;
        Message m = null;

        try {

            materials = new ListRawMaterialDatabase(getDataSourceR().getConnection()).listRawMaterial();

            if (materials.isEmpty())
                m = new Message("No raw material found in the database.",
                        "E600", "The database does not contains any product yet.");
            else
                m = new Message("Raw material list correctly retrieved.");

        } catch (SQLException ex) {
            m = new Message("Cannot show raw material list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("materials", materials);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/designer/raw-material-list.jsp").forward(req, res);
    }
    
}
