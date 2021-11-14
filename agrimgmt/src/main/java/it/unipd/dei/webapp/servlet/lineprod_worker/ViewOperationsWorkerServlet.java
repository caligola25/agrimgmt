package it.unipd.dei.webapp.servlet.lineprod_worker;


import it.unipd.dei.webapp.database.line_worker.ChangePhaseState;
import it.unipd.dei.webapp.database.line_worker.ViewOperationsWorker;
import it.unipd.dei.webapp.resource.*;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


/**
 * Show all the operation for the employee.
 */
public final class ViewOperationsWorkerServlet extends AbstractDatabaseServlet {

    /**
     * Show all the operation for the employee
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException      if any error occurs in the client/server communication.
     */

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long nOpQueued=0, nOpRunn=0;
        HttpSession session = req.getSession(false);
        UUID employeeId;
        Message m;
        List<ProductionPhase> operationsCompleted, operationsQueued;
        ProductionPhase operationRunning;
        try {
            // retrieves the request parameters
            Employee employee = (Employee) session.getAttribute("employee");
            employeeId = employee.getEmployeeId();

            nOpQueued=new ChangePhaseState(getDataSourceR().getConnection()).getNumberRunningOp(employeeId);
            nOpRunn =new ChangePhaseState(getDataSourceR().getConnection()).getNumberQueudOp(employeeId);
            session.setAttribute("nOpRunn", nOpQueued);
            session.setAttribute("nQueued", nOpRunn);

            if(nOpRunn!=0) {
                operationsQueued = new ViewOperationsWorker(getDataSourceR().getConnection()).operationsQueued(employeeId);
                operationRunning = new ViewOperationsWorker(getDataSourceR().getConnection()).operationsRunning(employeeId);
                session.setAttribute("operationsQueued",operationsQueued);
                session.setAttribute("operationRunning",operationRunning);
            }
            operationsCompleted = new ViewOperationsWorker(getDataSourceR().getConnection()).operationsCompleted(employeeId);
            session.setAttribute("operationsCompleted", operationsCompleted);
            res.sendRedirect(req.getContextPath() + "/protected/jsp/lineprod_worker/view-operations-worker.jsp");

        } catch (IllegalArgumentException ex) {
            m = new Message("Invalid input parameters.", "E100", ex.getMessage());
            req.setAttribute("message", m);
            req.getRequestDispatcher("/protected/jsp/lineprod_worker/view-operations-worker.jsp").forward(req, res);
        } catch (SQLException ex) {
            m = new Message("Cannot show the operations completed: unexpected error while accessing the database.", "E200", ex.getMessage());
            req.setAttribute("message", m);
            req.getRequestDispatcher("/protected/jsp/lineprod_worker/view-operations-worker.jsp").forward(req, res);
        }
    }
}
