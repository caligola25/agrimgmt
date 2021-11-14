package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.prod_planner.DeleteMaterialOrderDatabase;
import it.unipd.dei.webapp.database.prod_planner.IncreaseSupplyingDatabase;
import it.unipd.dei.webapp.resource.Supplying;
import it.unipd.dei.webapp.resource.MaterialOrder;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Adds a new supplying into the database.
 */
public final class AddSupplyingServlet extends AbstractDatabaseServlet {

    /**
     * Adds a new supplying into the database.
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

        UUID[] supplying_id = null;
        String[] material_id = null;
        String[] quantity = null;
        UUID material_order_id = null;
        String supplier_name = null;
        
        Message errorMessage = null;
        List<Message> m = new ArrayList<Message>();
        List<Supplying> suppList = new ArrayList<Supplying>();
        Supplying s = null;

        try{
            material_id = req.getParameterValues("material_id");
            quantity = req.getParameterValues("quantity");
            supplier_name = req.getParameter("supplier");
            material_order_id = UUID.fromString(req.getParameter("materialOrderId"));

            int len = material_id.length;//number of forms
            supplying_id = new UUID[len];
            for(int i = 0; i < len; i++) {
                supplying_id[i] = UUID.randomUUID();
                s = new Supplying(supplying_id[i],UUID.fromString(material_id[i]),Integer.parseInt(quantity[i]),
                material_order_id,supplier_name);

                new IncreaseSupplyingDatabase(getDataSourceW().getConnection(), s ).increaseSupplying();
                m.add(new Message(String.format("Raw Material %s successfully added.", material_id[i])));
                suppList.add(s);//supplying successfully added
            }

        }
        catch (IllegalArgumentException ex){
            errorMessage = new Message("Cannot add the supplying. Invalid input parameters: material_id must be a UUID.",
                    "E100", ex.getMessage());
            //remove eventually added items so far
            try{
                removeMaterialOrder(material_order_id);
            } catch (SQLException e) {
                errorMessage = new Message("Cannot remove the material Order: unexpected error while accessing the database.",
                        "E200", e.getMessage());
            }
        }
        catch(SQLException ex){
            
            errorMessage = new Message("Cannot add the supplying: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
            //remove eventually added items so far
            try{
                removeMaterialOrder(material_order_id);
            } catch (SQLException e) {
                errorMessage = new Message("Cannot remove the material Order: unexpected error while accessing the database.",
                        "E200", e.getMessage());
            }
    
        }

        req.setAttribute("messages",m);
        req.setAttribute("materialOrderId",material_order_id);
        req.setAttribute("message", errorMessage);
        req.setAttribute("sup", suppList);
        
        req.getRequestDispatcher("/protected/jsp/prod_planner/insert-supplying-result.jsp").forward(req,res);
    }

    private void removeMaterialOrder(UUID material_order_id) throws SQLException{
        //can set many fields to null since the only one interesting is the material_order_id
        MaterialOrder order = new MaterialOrder(material_order_id, 0, null, null, null);
        new DeleteMaterialOrderDatabase(getDataSourceW().getConnection(), order).deleteMaterialOrder();
    }


}
