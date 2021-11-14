package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.warehouse_worker.ListSupplierDatabase;
import it.unipd.dei.webapp.database.prod_planner.AddMaterialOrderDatabase;
import it.unipd.dei.webapp.database.prod_planner.ListRawMaterialDatabase;
import it.unipd.dei.webapp.resource.MaterialOrder;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.RawMaterial;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Adds a new material order into the database.
 */
public final class AddMaterialOrderServlet extends AbstractDatabaseServlet{

    /**
     * Adds a new material order into the database.
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
        float price = 0;
        int number_form = 0;

        Message m = null;
        MaterialOrder mo = null;
        List<String> suppliers = null;
        List<RawMaterial> materials = null;

        try{

            material_order_id = UUID.randomUUID();
            price = Float.parseFloat(req.getParameter("price"));
            number_form = Integer.parseInt(req.getParameter("number"));

            mo = new MaterialOrder(material_order_id, price, new Date(System.currentTimeMillis()), "completed", null);
            new AddMaterialOrderDatabase(getDataSourceW().getConnection(), mo).addMaterialOrder();


            m = new Message(String.format("Material Order %s successfully added.", material_order_id));


        }catch (IllegalArgumentException ex){
            m = new Message("Cannot add the supplying. Invalid input parameters: material_id must be a UUID.",
                "E100", ex.getMessage());

        }catch(SQLException ex){

            m = new Message("Cannot add the material order: unexpected error while accessing the database.",
                    "E200", ex.getMessage());

        }

        //retrieve all suppliers' names
        try {
            suppliers = new ListSupplierDatabase(getDataSourceR().getConnection(), null).listSupplier();
        }
        catch (SQLException ex){
            m = new Message("Cannot look for the suppliers: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        //retrieve all raw materials
        try {
            materials = new ListRawMaterialDatabase(getDataSourceR().getConnection()).listRawMaterial();
        }
        catch (SQLException ex){
            m = new Message("Cannot look for the raw materials: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }
        
        req.setAttribute("message",m);
        req.setAttribute("materialOrder", mo);
        req.setAttribute("num", number_form);//how many instances of supply form we want in the following page
        req.setAttribute("supp_names", suppliers);
        req.setAttribute("raw_materials", materials);

        req.getRequestDispatcher("/protected/jsp/prod_planner/insert-material-order-result.jsp").forward(req,res);
    }
}
