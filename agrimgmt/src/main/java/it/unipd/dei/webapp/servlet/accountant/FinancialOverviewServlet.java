package it.unipd.dei.webapp.servlet.accountant;

import it.unipd.dei.webapp.database.accountant.SearchMaterialOrderByDateAndStatus;
import it.unipd.dei.webapp.database.administrator.EmployeeDatabase;
import it.unipd.dei.webapp.database.accountant.SearchProductOrderByDateAndStatus;
import it.unipd.dei.webapp.database.accountant.SearchFixedCostDatabase;
import it.unipd.dei.webapp.resource.*;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class in charge of building the financial overview page
 */
public final class FinancialOverviewServlet extends AbstractDatabaseServlet {

    /**
     * This method collect the start and end dates of the time period that the user want to analyze and retrieve all
     * the information required from the database to build the financial overview page:
     * <ul>
     *     <li>
     *         Retrieves all the fixed costs information and computes the sum af their price
     *     </li>
     *     <li>
     *         Retrieves all the employee information and computes the sum of their salary
     *     </li>
     *     <li>
     *         Retrieves all the received material order in the target time period and computes the sum of their prices
     *     </li>
     *     <li>
     *         Retrieves all the paid and shipped product order in the target time period and computes the sum of their prices
     *     </li>
     *     <li>
     *         Computes the total outcomes and incomes of the company in the target time period and teh overall earnings
     *     </li>
     * </ul>
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException  if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // GENERAL VARIABLES

        Date startDate = null;
        Date endDate = null;
        List<Message> m = new ArrayList<>();
        boolean illegalParam = false;

        try{
            startDate = Date.valueOf(req.getParameter("start-date"));
            endDate = Date.valueOf(req.getParameter("end-date"));
        } catch (IllegalArgumentException ex) {
            m.add(new Message("Cannot complete the research. Invalid input parameters: the date has to be inserted as (yyyy-mm-dd)",
                    "E100", ex.getMessage()));
            illegalParam = true;
        }

        if(illegalParam == false) {
            // FIXED COST VIEW

            // model elements
            List<FixedCost> fc = null;
            float total_fc = 0;

            try {
                if (startDate.compareTo(endDate) > 0)
                {
                    m.add(new Message("Cannot complete the research. Invalid input parameters: the start date must be smaller than the end date",
                            "E100", "Incompatible date range"));
                } else {
                    fc = new SearchFixedCostDatabase(getDataSourceR().getConnection(), startDate, endDate).viewFixedCost();

                    for (FixedCost k : fc) {
                        total_fc += k.getPrice();
                    }

                    m.add(new Message("Search of the requested data ended successfully"));
                }
            } catch (SQLException ex) {
                m.add(new Message("FIXED COST SEARCH - Cannot complete the research: error while accessing the database)",
                        "E200", ex.getMessage()));
            }


            // EMPLOYEES VIEW

            // model elements
            List<Employee> e = null;
            float total_e = 0;

            try {
                e = new EmployeeDatabase(getDataSourceR().getConnection()).viewEmployee();

                for (Employee k : e) {
                    total_e += k.getSalary();
                }
            } catch (SQLException ex) {
                m.add(new Message("EMPLOYEE SEARCH - Cannot complete the research: error while accessing the database)",
                        "E200", ex.getMessage()));
            }


            // MATERIAL ORDER VIEW

            // model elements
            List<MaterialOrder_SupplierName> matOrders = null;
            float total_mo = 0;

            try{
                if (startDate.compareTo(endDate) <= 0) {
                    matOrders = new SearchMaterialOrderByDateAndStatus(getDataSourceR().getConnection(), startDate, endDate, "received").searchMaterialOrder();

                    for (MaterialOrder_SupplierName k : matOrders) {
                        total_mo += k.getPrice();
                    }
                }
            } catch (SQLException ex ) {
                m.add(new Message("MATERIAL ORDER SEARCH - Cannot complete the research: error while accessing the database)",
                        "E200", ex.getMessage()));
            }


            // PRODUCT ORDER VIEW

            // model elements
            List<ProductOrder_CustomerName> prodOrders = null;
            float total_po = 0;

            try{
                if (startDate.compareTo(endDate) <= 0) {
                    prodOrders = new SearchProductOrderByDateAndStatus(getDataSourceR().getConnection(), startDate, endDate, "paid", "shipped").searchProductOrder();

                    for (ProductOrder_CustomerName k : prodOrders) {
                        total_po += k.getPrice();
                    }
                }
            } catch (SQLException ex ) {
                m.add(new Message("PRODUCT ORDER SEARCH - Cannot complete the research: error while accessing the database)",
                        "E200", ex.getMessage()));
            }

            // JSP FORWARDING

            req.setAttribute("fixedCostList", fc);
            req.setAttribute("employeeList", e);
            req.setAttribute("matOrderList", matOrders);
            req.setAttribute("prodOrderList", prodOrders);
            req.setAttribute("totalFixedPrice", total_fc);
            req.setAttribute("totalSalary", total_e);
            req.setAttribute("totalMatOrderPrice", total_mo);
            req.setAttribute("totalProdOrderPrice", total_po);
            req.setAttribute("startDate", startDate);
            req.setAttribute("endDate", endDate);
        }


        // JSP FORWARDING

        req.setAttribute("messageList", m);

        // forward the control to the jsp
        req.getRequestDispatcher("/protected/jsp/accountant/financial-overview.jsp").forward(req, res);
    }
}
