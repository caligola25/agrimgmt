package it.unipd.dei.webapp.servlet.designer;

import it.unipd.dei.webapp.database.designer.*;
import it.unipd.dei.webapp.resource.Process;
import it.unipd.dei.webapp.resource.RawMaterial;
import it.unipd.dei.webapp.resource.Product;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Adds a new process into the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class AddProcessServlet extends AbstractDatabaseServlet {

    /**
     * Adds a new process into the database.
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

        //process parameters
        UUID process_id = null;
        String process_name = null;
        int sequence_number = -1;
        String estimated_time = null;
        int quantity = -1;

        //product parameters
        UUID product_id = null;
        String product_name = null;
        float price = -1;
        boolean available = false;

        //material parameters
        UUID material_id = null;
        String material_name = null;

        // model
        Process pr  = null;
        Product p = null;
        RawMaterial r  = null;

        Message m = null;

        //to block the execution in there is an error in adding the product or the material before trying to add the process
        boolean err=false;

        //to delete just created product that are not connected with process (database constraint 1,N) as an error occurred in the successively creation of the material or the process
        boolean deleteP = false;

        //to delete just created materials that are not connected with process (database constraint 1,N) as an error occurred in the successively creation of the process
        boolean deleteM = false;

        try{
            product_id = UUID.fromString(req.getParameter("product_id"));
            if(!(req.getParameter("product_name").equals("")||req.getParameter("price").equals(""))){
                product_name = req.getParameter("product_name");
                price = Float.parseFloat(req.getParameter("price"));
                available = Boolean.parseBoolean(req.getParameter("available"));
                if(price<=0){
                    throw new IllegalArgumentException();
                }
                p = new Product(product_id, product_name, price, available);
                new AddProductDatabase(getDataSourceW().getConnection(), p).addProduct();
            }
        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot add the process related product. Invalid input parameters: product id must be a UUID, price a grater than 0 number.",
                    "E100", ex.getMessage());
            err=true;
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("23505")) {
                m = new Message(String.format("Cannot add the process related product: product %s already exists. Just specify the ID for already present things.", product_id),
                        "E300", ex.getMessage());
            } else {
                m = new Message("Cannot add the process related product: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
            }
            err=true;
        }

        if(!err){
            try{
                material_id = UUID.fromString(req.getParameter("material_id"));
                if(!req.getParameter("material_name").equals("")) {
                    material_name = req.getParameter("material_name");
                    r = new RawMaterial(material_id, material_name);
                    new AddRawMaterialDatabase(getDataSourceW().getConnection(), r).addRawMaterial();
                }
            } catch (IllegalArgumentException ex) {
                m = new Message("Cannot add the process related material. Invalid input parameters: material_id must be a UUID.",
                        "E100", ex.getMessage());
                err=true;
                deleteP=true;
            } catch (SQLException ex) {
                if (ex.getSQLState().equals("23505")) {
                    m = new Message(String.format("Cannot add the process related material: the material %s already exists. Just specify the ID for already present things.", material_id),
                            "E300", ex.getMessage());
                } else {
                    m = new Message("Cannot add the the process related material: unexpected error while accessing the database.",
                            "E200", ex.getMessage());
                }
                err=true;
                deleteP=true;
            }
        }

        if(!err){
            try{

                // retrieves the request parameters
                process_id= UUID.randomUUID();
                process_name = req.getParameter("process_name");
                sequence_number = Integer.parseInt(req.getParameter("sequence_number"));
                estimated_time = req.getParameter("estimated_time");
                quantity =  Integer.parseInt(req.getParameter("quantity"));

                if(process_name.equals("")||estimated_time.equals("00:00")||quantity<=0||sequence_number<=0){
                    throw new IllegalArgumentException();
                }

                // creates objects for accessing the database and storing the respective thing
                pr = new Process(process_id, process_name, sequence_number, estimated_time, product_id, material_id, quantity);
                new AddProcessDatabase(getDataSourceW().getConnection(), pr).addProcess();

                m = new Message(String.format("Process %s successfully added.", process_id));

            } catch (IllegalArgumentException ex) {
                m = new Message("Cannot add the process. Invalid input parameters: process name must be specified, sequence number and quantity must be grater than 0 numbers and the estimated time must be greater than 00:00 ",
                        "E100", ex.getMessage());
                deleteP=true;
                deleteM=true;
            }catch (SQLException ex) {
                if (ex.getSQLState().equals("23503")) {
                    m = new Message(String.format("Cannot add the process: the product or the material don't already exists. You have to specify the details for things not already present."),
                            "E300", ex.getMessage());
                } else {
                    m = new Message("Cannot add the process: unexpected error while accessing the database.",
                            "E200", ex.getMessage());
                }
                deleteP=true;
                deleteM=true;
            }
        }

        if(deleteP&&(p!=null)){//we were not able to connect the product to a process so we have to delete it but only if p!=null which means that was just created so it is not a product already present in the database.
            try {
                new DeleteProductDatabase(getDataSourceW().getConnection(), p).deleteProduct();
            } catch (SQLException ignored) {//ignored as it was just created so it is for sure in the database and we know all details about it
            }
        }

        if(deleteM&&(r!=null)){//we were not able to connect the raw material to a process so we have to delete it but only if r!=null which means that was just created so it is not a product already present in the database.
            try {
                new DeleteRawMaterialDatabase(getDataSourceW().getConnection(), r).deleteRawMaterial();
            } catch (SQLException ignored) {//ignored as it was just created so it is for sure in the database and we know all details about it
            }
        }

        req.setAttribute("process", pr);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/designer/add-process-result.jsp").forward(req, res);
    }


}