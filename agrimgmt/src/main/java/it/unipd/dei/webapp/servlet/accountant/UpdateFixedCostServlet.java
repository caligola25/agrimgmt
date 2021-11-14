package it.unipd.dei.webapp.servlet.accountant;

import it.unipd.dei.webapp.database.accountant.ReportManagementDatabase;
import it.unipd.dei.webapp.database.accountant.RetrieveFixedCostTypesDatabase;
import it.unipd.dei.webapp.database.accountant.SearchFixedCostDatabase;
import it.unipd.dei.webapp.database.accountant.UpdateFixedCostDatabase;
import it.unipd.dei.webapp.resource.*;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Class in charge of managing the fixed cost update process
 */
public final class UpdateFixedCostServlet extends AbstractDatabaseServlet {

    /**
     * This method has the function of retrieving from the database all the information needed to correctly display
     * the fixed cost update page:
     * <ul>
     *     <li>
     *         Collects the type and the date of the fixed cost to uniquely identifying it and retrieve all of its
     *         information from the database
     *     </li>
     *     <li>
     *         Retrieve from the database all the allowed fixed cost's types
     *     </li>
     *     <li>
     *         Retrieve from the database all the report's dates
     *     </li>
     * </ul>
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = null;
        Date date = null;
        Message m = null;
        List<Date> possReportDate = null;
        List<String> types = null;
        FixedCost fc = null;

        try{
            type = req.getParameter("type_search");
            date = Date.valueOf(req.getParameter("date_search"));

            fc = new SearchFixedCostDatabase(getDataSourceR().getConnection(), type, date).searchFixedCost();

            possReportDate = new ReportManagementDatabase(getDataSourceR().getConnection()).viewDates();

            types = new RetrieveFixedCostTypesDatabase(getDataSourceR().getConnection()).retrieveTypes();

            m = new Message("Search of the requested data ended successfully");
        } catch (SQLException ex) {
            m = new Message("Cannot complete the research: error while accessing the database)", "E200", ex.getMessage());
        }

        // JSP FORWARDING
        req.setAttribute("fixedCost", fc);
        req.setAttribute("reportDates", possReportDate);
        req.setAttribute("types", types);
        req.setAttribute("type", type);
        req.setAttribute("date", date);
        req.setAttribute("message", m);

        // forward the control to the jsp
        req.getRequestDispatcher("/protected/jsp/accountant/update-fixed-cost.jsp").forward(req, res);
    }

    /**
     * This method is actually in charge of updating the fixed cost entity based on the received parameters:
     * <ul>
     *     <li>
     *         Collects the type and the date of the fixed cost entity that the user want to modify plus the parameters
     *         that need to be changed
     *     </li>
     *     <li>
     *         Finds the correct fixed cost and modifies it following the user instruction
     *     </li>
     *     <li>
     *         Retrieve from the database all the allowed fixed cost's types
     *     </li>
     *     <li>
     *         Retrieve from the database all the report's dates
     *     </li>
     * </ul>
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = null;
        String oldType = null;
        double price = -1;
        Date date = null;
        Date oldDate = null;
        Date report_date = null;
        FixedCost newFc = null;
        FixedCost fc = null;
        List<Date> possReportDate = null;
        List<String> types = null;

        Message m = null;

        try {
            //retrieves the request parameters
            if(!req.getParameter("type").equals("")) {
                type = req.getParameter("type");
            }
            if(!req.getParameter("price").equals("")) {
                price = Double.parseDouble(req.getParameter("price"));
            }
            if(!req.getParameter("date").equals("")) {
                date = Date.valueOf(req.getParameter("date"));
            }
            if(!req.getParameter("report_date").equals("")) {
                report_date = Date.valueOf(req.getParameter("report_date"));
            }

            oldDate = Date.valueOf(req.getParameter("oldDate"));
            oldType = req.getParameter("oldType");

            //retrieve the original fixed cost entity
            fc = new SearchFixedCostDatabase(getDataSourceR().getConnection(), oldType, oldDate).searchFixedCost();

            // retrieve data for the jsp
            possReportDate = new ReportManagementDatabase(getDataSourceR().getConnection()).viewDates();

            types = new RetrieveFixedCostTypesDatabase(getDataSourceR().getConnection()).retrieveTypes();

            //chek the received parameters
            if (price < 0 && price != -1) {
                throw new SQLException("The inserted value is smaller than 0", "42604");
            }

            //update the selected fixed cost in the db
            if (date != null) {
                new UpdateFixedCostDatabase(getDataSourceW().getConnection(), type, price, date, report_date, fc.getType(), fc.getDate()).updateFixedCostDate();
            }
            if (type != null) {
                new UpdateFixedCostDatabase(getDataSourceW().getConnection(), type, price, date, report_date, fc.getType(), fc.getDate()).updateFixedCostType();
            }
            if (price != -1) {
                new UpdateFixedCostDatabase(getDataSourceW().getConnection(), type, price, date, report_date, fc.getType(), fc.getDate()).updateFixedCostPrice();
            }
            if (report_date != null) {
                new UpdateFixedCostDatabase(getDataSourceW().getConnection(), type, price, date, report_date, fc.getType(), fc.getDate()).updateFixedCostReportDate();
            }

            if(date != null && type != null) {
                newFc = new SearchFixedCostDatabase(getDataSourceR().getConnection(), type, date).searchFixedCost();
            } else if (date != null && type == null) {
                newFc = new SearchFixedCostDatabase(getDataSourceR().getConnection(), oldType, date).searchFixedCost();
            } else if (date == null && type == null) {
                newFc = new SearchFixedCostDatabase(getDataSourceR().getConnection(), oldType, oldDate).searchFixedCost();
            } else if (date == null && type != null) {
                newFc = new SearchFixedCostDatabase(getDataSourceR().getConnection(), type, oldDate).searchFixedCost();
            }

            m = new Message(String.format("The fixed cost of type %s, of date %s has been correctly updated", fc.getType(), fc.getDate().toString()));
        } catch (NumberFormatException ex) {
            m = new Message("Cannot complete the update. Invalid input parameter: price must be a number.", "E100", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot complete the update. Invalid input parameters: the dates has to be inserted as (yyyy-mm-dd)", "E100", ex.getMessage());
        } catch (SQLException ex) {
            switch (ex.getSQLState()) {
                case "23503":
                    m = new Message(String.format("Cannot complete the update. The report date %s doesn't correspond to any report.", report_date), "E400", ex.getMessage());
                    break;
                case "23528":
                    m = new Message(String.format("Cannot complete the update. The type %s is not allowed, the accepted ones are: electrical_bill, gas_bill, water_bill, rent, tax", type), "E500", ex.getMessage());
                    break;
                case "42604":
                    m = new Message("Cannot complete the update. The price value must be greater or equal than 0", "E500", ex.getMessage());
                    break;
                default:
                    m = new Message("Cannot complete the update. Unexpected error while accessing the database.", "E200", ex.getMessage());
                    break;
            }
        }

        req.setAttribute("newFixedCost", newFc);
        req.setAttribute("fixedCost", fc);
        req.setAttribute("types", types);
        req.setAttribute("reportDates", possReportDate);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/accountant/update-fixed-cost.jsp").forward(req, res);
    }
}
