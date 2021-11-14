package it.unipd.dei.webapp.servlet.accountant;

import it.unipd.dei.webapp.database.accountant.AddFixedCostDatabase;
import it.unipd.dei.webapp.database.accountant.ReportManagementDatabase;
import it.unipd.dei.webapp.database.accountant.RetrieveFixedCostTypesDatabase;
import it.unipd.dei.webapp.database.accountant.SearchFixedCostDatabase;
import it.unipd.dei.webapp.resource.FixedCost;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Class in charge of inserting a new fixed cost entity
 */
public final class AddFixedCostServlet extends AbstractDatabaseServlet {

    /**
     * This method collects all the parameters needed to create a new fixed cost entity and insert the just created
     * element in the database
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = null;
        double price = -1;
        Date date = null;
        Date report_date = null;

        FixedCost fixedCost = null;
        List<FixedCost> fc = null;
        List<String> t = null;
        List<Date> rd = null;
        Message m = null;

        try {

            //retrieves the page parameters
            fc = new SearchFixedCostDatabase(getDataSourceR().getConnection()).viewNoReportFixedCost();
            t = new RetrieveFixedCostTypesDatabase(getDataSourceR().getConnection()).retrieveTypes();
            rd = new ReportManagementDatabase(getDataSourceR().getConnection()).viewDates();

            //retrieves the request parameters
            type = req.getParameter("type");
            price = Double.parseDouble(req.getParameter("price"));
            date = Date.valueOf(req.getParameter("date"));

            if(!req.getParameter("report_date").equals("")) {
                report_date = Date.valueOf(req.getParameter("report_date"));
            }

            //chek the received parameters
            if (price < 0) {
                throw new SQLException("The inserted value is smaller than 0", "42604");
            }

            //store the new fixed cost in the db
            fixedCost = new FixedCost(date, price, type, report_date);
            new AddFixedCostDatabase(getDataSourceW().getConnection(), fixedCost).addFixedCost();
            fc = new SearchFixedCostDatabase(getDataSourceR().getConnection()).viewNoReportFixedCost();


            m = new Message(String.format("The fixed cost of type %s, of date %s has been successfully added to the system", type, date.toString()));
        } catch (NumberFormatException ex) {
            m = new Message("Cannot complete the insertion. Invalid input parameter: price must be a number.", "E100", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot complete the insertion. Invalid input parameters: the dates has to be inserted as (yyyy-mm-dd)", "E100", ex.getMessage());
        } catch (SQLException ex) {
            switch (ex.getSQLState()) {
                case "23505":
                    m = new Message(String.format("Cannot complete the insertion. Fixed cost of type %s and date %s already exist.", type, date.toString()), "E300", ex.getMessage());
                    break;
                case "23503":
                    m = new Message(String.format("Cannot complete the insertion. The report date %s doesn't correspond to any report.", report_date.toString()), "E400", ex.getMessage());
                    break;
                case "23502":
                    m = new Message("Cannot complete the insertion. It is necessary to insert a price value", "E500", ex.getMessage());
                    break;
                case "23528":
                    m = new Message(String.format("Cannot complete the insertion. The type %s is not allowed, the accepted ones are: electrical_bill, gas_bill, water_bill, rent, tax", type), "E500", ex.getMessage());
                    break;
                case "42604":
                    m = new Message("Cannot complete the insertion. The price value must be greater or equal than 0", "E500", ex.getMessage());
                    break;
                default:
                    m = new Message("Cannot complete the insertion. Unexpected error while accessing the database", "E200", ex.getMessage());
                    break;
            }
        }

        req.setAttribute("fixedCost", fc);
        req.setAttribute("types", t);
        req.setAttribute("reportDates", rd);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/protected/jsp/accountant/fixed-cost-management.jsp").forward(req, res);
    }
}
