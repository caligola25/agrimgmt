package it.unipd.dei.webapp.servlet.warehouse_worker;

import it.unipd.dei.webapp.database.prod_planner.AddMaterialOrderDatabase;
import it.unipd.dei.webapp.database.prod_planner.IncreaseSupplyingDatabase;
import it.unipd.dei.webapp.resource.MaterialOrder;
import it.unipd.dei.webapp.resource.Supplying;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;
import java.lang.System;

/**
 * Supplying of raw materials.

 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class SupplyingServlet extends AbstractDatabaseServlet {

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

        UUID supplying_id = null;
        UUID material_id = null;
        int quantity = 0;
        UUID material_order_id = null;
        String supplier_name = null;
        float price = 0;

        Message m = null;
        Supplying s = null;
        MaterialOrder mo = null;

        try{
            supplying_id = UUID.randomUUID();
            material_id = UUID.fromString(req.getParameter("material_id"));

            quantity = Integer.parseInt(req.getParameter("quantity"));
            material_order_id = UUID.randomUUID();
            supplier_name = req.getParameter("supplier");
            price = Float.parseFloat(req.getParameter("price"));

            mo = new MaterialOrder(material_order_id, price, new Date(System.currentTimeMillis()), "completed", null);
            new AddMaterialOrderDatabase(getDataSourceW().getConnection(), mo ).addMaterialOrder();

            s = new Supplying(supplying_id,material_id,quantity,material_order_id,supplier_name);

            new IncreaseSupplyingDatabase(getDataSourceW().getConnection(), s ).increaseSupplying();

            m = new Message(String.format("Raw Material %s successfully added.", material_id));

            req.setAttribute("message",m);
            req.setAttribute("materialOrderId",material_order_id);
            req.setAttribute("price",price);
            req.setAttribute("materialId",material_id);
            req.setAttribute("quantity",quantity);
            req.setAttribute("supplierName",supplier_name);

        }catch (IllegalArgumentException ex){
            m = new Message("Cannot add the supplying. Invalid input parameters: material_id must be a UUID.",
                    "E100", ex.getMessage());
            req.setAttribute("message",m);

        }catch(SQLException ex){
            if (ex.getSQLState().equals("23505")) {
                m = new Message(String.format("Cannot add the applying: supplying_id %s already exists.", supplying_id),
                        "E300", ex.getMessage());

                req.setAttribute("message",m);

            } else {
                m = new Message("Cannot add the applying: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
                req.setAttribute("message", m);
            }
        }
        req.getRequestDispatcher("/protected/jsp/warehouse_worker/result-supplying.jsp").forward(req,res);
    }


}
