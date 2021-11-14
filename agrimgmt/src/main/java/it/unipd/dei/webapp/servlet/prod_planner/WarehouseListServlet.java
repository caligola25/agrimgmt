package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.WarehouseListDatabase;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Retrieve the list of materials stored in the warehouse from the database
 */
public final class WarehouseListServlet extends  AbstractDatabaseServlet{

    /**
     * Retrieve the list of materials stored in the warehouse from the database
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

        List<List<String>> materials = null;
        List<List<String>> items = null;
        Message m = null;

        try {

            materials = new WarehouseListDatabase(getDataSourceR().getConnection()).warehouseMaterialList();

            if (materials.isEmpty())
                m = new Message("No stored materials found in the database.",
                        "E600", "The warehouse does not contains any materials yet.");
        } catch (SQLException ex) {
            m = new Message("Cannot show stored materials list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        try {

            items = new WarehouseListDatabase(getDataSourceR().getConnection()).warehouseItemList();

        } catch (SQLException ex) {
            m = new Message("Cannot show stored items list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        m = new Message("Warehouse list correctly retrieved.");

        req.setAttribute("materials", materials);
        req.setAttribute("items", items);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/warehouse-list.jsp").forward(req, res);
    }
}
