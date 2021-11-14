package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.DeleteMaterialOrderDatabase;
import it.unipd.dei.webapp.resource.MaterialOrder;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Delete Material Order to the database.
 */
public final class DeleteMaterialOrderServlet extends AbstractDatabaseServlet {
    /**
     * Delete Material Order to the database.
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

        UUID material_order_id = null;
        String caller = null; //remembers which form called this it.unipd.dei.webapp.servlet
        Message message = null;

        try{
            material_order_id = UUID.fromString(req.getParameter("materialOrderId"));
            caller = req.getParameter("caller");
            MaterialOrder order = new MaterialOrder(material_order_id, 0, null, null, null);
            new DeleteMaterialOrderDatabase(getDataSourceW().getConnection(), order).deleteMaterialOrder();

            //creates correct message
            switch (caller) {
                case "undo":
                    message = new Message("Previously inserted material order correctly removed.");
                    break;
                case "delete":
                    message = new Message(String.format("Material Order %s successfully removed.", material_order_id));
                    break;
            }
            
        }
        catch(SQLException ex){
            
            message = new Message("Cannot remove the material Order: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
        }

        req.setAttribute("message", message);

        //selects the correct dispatcher
        switch (caller) {
            case "undo":
                    req.getRequestDispatcher("/protected/jsp/prod_planner/undo-insertion.jsp").forward(req, res);
                    break;
                case "delete":
                    req.getRequestDispatcher("/protected/jsp/prod_planner/delete-material-order-result.jsp").forward(req, res);
                    break;
        }
        
    }
}
