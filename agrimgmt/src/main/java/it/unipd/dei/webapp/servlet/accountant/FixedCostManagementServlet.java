package it.unipd.dei.webapp.servlet.accountant;

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
 * Class in charge of retrieving all the information needed to populate the fixed cost management page
 */
public final class FixedCostManagementServlet extends AbstractDatabaseServlet {

    /**
     *  This method has the function of retrieving from the database all teh information needed to build the fixed
     *  cost management page:
     * <ul>
     *     <li>
     *         Retrieve from the database all the fixed costs entity that are not already linked to a report
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
        Message m = null;
        List<FixedCost> fc = null;
        List<String> t = null;
        List<Date> rd = null;

        try{
            fc = new SearchFixedCostDatabase(getDataSourceR().getConnection()).viewNoReportFixedCost();

            t = new RetrieveFixedCostTypesDatabase(getDataSourceR().getConnection()).retrieveTypes();

            rd = new ReportManagementDatabase(getDataSourceR().getConnection()).viewDates();
        } catch (SQLException ex) {
            m = new Message("Cannot complete the research: error while accessing the database)", "E200", ex.getMessage());
        }

        // JSP FORWARDING
        req.setAttribute("fixedCost", fc);
        req.setAttribute("types", t);
        req.setAttribute("reportDates", rd);
        req.setAttribute("message", m);

        // forward the control to the jsp
        req.getRequestDispatcher("/protected/jsp/accountant/fixed-cost-management.jsp").forward(req, res);
    }
}
