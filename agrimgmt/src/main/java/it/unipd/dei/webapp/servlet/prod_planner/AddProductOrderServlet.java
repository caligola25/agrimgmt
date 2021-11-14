package it.unipd.dei.webapp.servlet.prod_planner;

import it.unipd.dei.webapp.database.line_worker.UpdateEmployeeBusyDatabase;
import it.unipd.dei.webapp.database.prod_planner.*;
import it.unipd.dei.webapp.resource.*;
import it.unipd.dei.webapp.resource.Process;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Creates a new production order and the related items and production phases into the database.
 */
public final class AddProductOrderServlet extends  AbstractDatabaseServlet{

    /**
     * Creates a new production order and the related items and production phases into the database.
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
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, IllegalArgumentException {

        // request parameters
        String customerName = null;
        String[] products_id;
        String[] quantity;

        // model
        ProductOrder prodOrder;
        float price = 0;
        List<Process> processes;
        HashMap<String, Integer> materials = new HashMap<>();
        HashMap<String, String> matDict = new HashMap<>();
        List<List<String>> matMissing = new ArrayList<>();
        Message m;

        try{

            customerName = req.getParameter("customerName");
            products_id = req.getParameterValues("product_id");
            quantity = req.getParameterValues("quantity");

            // check if quantity value inserted is valid
            for (String qty : quantity)
                if (Integer.parseInt(qty) < 1)
                    throw new IllegalArgumentException("Invalid input parameters: number of items must be a positive integer.");

            // initialize dictionaries of material names and material quantity
            List<List<String>> matStored = new WarehouseListDatabase(getDataSourceR().getConnection()).warehouseMaterialList();
            for (List<String> mat : matStored) {
                materials.put(mat.get(0), materials.getOrDefault(mat.get(0), 0) + Integer.parseInt(mat.get(2)));
                matDict.put(mat.get(0), mat.get(1));
            }

            // check if the amount of material needed is stored in warehouse
            for (int i = 0; i < products_id.length; i++) {
                List<List<String>> matNeeded = new SearchMaterialNeededDatabase(getDataSourceR().getConnection(),
                        UUID.fromString(products_id[i])).searchMaterialNeededByProductId();
                for (List<String> mat : matNeeded) {
                    materials.put(mat.get(0), materials.get(mat.get(0)) - (Integer.parseInt(mat.get(1)) * Integer.parseInt(quantity[i])));
                }
            }

            // record missing materials and quantity
            for (String mat : materials.keySet()) {
                if (materials.get(mat) < 0){
                    List<String> tmp = new ArrayList<>();
                    tmp.add(mat);
                    tmp.add(matDict.get(mat));
                    tmp.add(materials.get(mat) * -1 + "");
                    matMissing.add(tmp);
                }
            }

            // check if the warehouse has all needed materials
            if (!matMissing.isEmpty())
                throw new ServletException("There are not enough stored materials in the warehouse to complete the process.");

            // record the total price of the order
            for (int i = 0; i < products_id.length; i++)
                price += new SearchProductDatabase(getDataSourceR().getConnection(),
                        UUID.fromString(products_id[i])).searchProductById().getPrice() * Integer.parseInt(quantity[i]);

            // add new product order into the database
            UUID prodOrder_id = UUID.randomUUID();
            List<Customer> customer = new SearchCustomerDatabase(getDataSourceR().getConnection(), customerName).searchCustomerByName();
            prodOrder = new ProductOrder(prodOrder_id, "in_production", price, new Date(System.currentTimeMillis()),
                    customer.get(0).getCustomerId(), null);
            new AddProductOrderDatabase(getDataSourceW().getConnection(), prodOrder).addProductOrder();

            // creation and insertion of items and production phase into the database
            for (int i = 0; i < products_id.length; i++) {      // iterate foreach product inserted in the form

                for (int j = 0; j < Integer.parseInt(quantity[i]); j++) {       // iterate to add the correct quantity of item of the same type

                    Item item = new Item(UUID.randomUUID(), "in_construction", prodOrder_id, UUID.fromString(products_id[i]));
                    new AddItemDatabase(getDataSourceW().getConnection(), item).addItem();      // add item into the database
                    processes = new SearchProcessDatabase(getDataSourceR().getConnection(), UUID.fromString(products_id[i])).searchProcessByProductId();

                    // add production phase foreach item into the database
                    for (Process proc : processes) {

                        // find less busy employee
                        Employee employee = new SearchEmployeeDatabase(getDataSourceR().getConnection()).searchFreeEmployee();

                        new AddProductionPhaseDatabase(getDataSourceW().getConnection(),
                                new ProductionPhase(UUID.randomUUID(), item.getItemId(), proc.getProcessId(),
                                        employee.getEmployeeId(), "queued", null)).addProductionPhase();

                        //updates the number of actions of the employee
                        new UpdateEmployeeBusyDatabase(getDataSourceW().getConnection()).updateEmployeeBusy(employee.getnOperation() + 1,employee.getEmployeeId());
                    }
                }
            }

            req.setAttribute("prodOrder", prodOrder);
            req.setAttribute("products", products_id);
            req.setAttribute("quantity", quantity);

            m = new Message("Production Order successfully added.");

        } catch (SQLException ex) {
            m = new Message("Cannot insert a new product order: unexpected error while accessing the database.", "E200", ex.getMessage());
        } catch (ServletException ex) {
            m = new Message("Material missing to add a new product order.", "E800", ex.getMessage());
            req.setAttribute("matMissing", matMissing);
        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot insert a new product order. Invalid input arguments.", "E100", ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            m = new Message("Cannot insert a new product order. There are not enough free employees.", "E900", ex.getMessage());
        }

        req.setAttribute("customer", customerName);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/prod_planner/insert-prod-order-result.jsp").forward(req, res);
    }
}
