package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.SearchItemDatabase;
import it.unipd.dei.webapp.database.prod_planner.SearchProdPhaseDatabase;
import it.unipd.dei.webapp.resource.Item;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.ProductionPhase;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Retrieve a list of item given a production order UUID and a list of production phase given an item UUID
 */
public final class ItemNProdPhaseListServlet extends AbstractDatabaseServlet {

    /**
     * Retrieve a list of item given a production order UUID and a list of production phase given an item UUID
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

        HashMap<Item,List<ProductionPhase>> info = new HashMap<Item,List<ProductionPhase>>();
        Message m = null;

        try {
            UUID orderId = UUID.fromString(req.getParameter("prodOrderId"));
            List<Item> items = new SearchItemDatabase(getDataSourceR().getConnection(), orderId).searchItemByProdOrderId();


            if (items.isEmpty())
                m = new Message("No item found in the database.",
                        "E600", "The database does not contain any item associated to the productOrder yet.");
            //cycle through all the found Items
            for(Item item : items) {
                //get the list of production phases
                List<ProductionPhase> phases = new SearchProdPhaseDatabase(getDataSourceR().getConnection(), item.getItemId()).searchProdPhaseByItemId();
                info.put(item, phases);
            }
            m = new Message("All information correctly retrieved");

        } catch (SQLException ex) {
            m = new Message("Cannot show material orders list: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("info", info);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/items-phases-list.jsp").forward(req, res);
    }
}
