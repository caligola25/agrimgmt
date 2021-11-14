package it.unipd.dei.webapp.servlet.lineprod_worker;

import it.unipd.dei.webapp.database.line_worker.ChangePhaseState;
import it.unipd.dei.webapp.database.line_worker.InsertPhaseTime;
import it.unipd.dei.webapp.database.line_worker.UpdateEmployeeBusyDatabase;
import it.unipd.dei.webapp.database.line_worker.ViewOperationsWorker;
import it.unipd.dei.webapp.resource.Employee;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.ProductionPhase;
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
 *  Insert the actual time for that prouction_phase, then change the state of a queued operation into running
 */
public final class InsertPhaseTimeServlet extends AbstractDatabaseServlet {


    /**
     * Insert the time of the operation completed
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException      if any error occurs in the client/server communication.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<ProductionPhase> operationsCompleted,operationsQueued;
        ProductionPhase operationRunning;
        UUID phaseToRun,employeeId,prodPhaseId;
        long nOpQueued,nOpRunn;
        String h="0",m="0",s="0";
        Message message;
        HttpSession session = req.getSession(false);
        try{
            // retrieves the request parameters
            Employee employee= (Employee) session.getAttribute("employee");
            ProductionPhase opRunning =(ProductionPhase) session.getAttribute("operationRunning");
            session.setAttribute("employee", employee);
            employeeId= employee.getEmployeeId();
            prodPhaseId = opRunning.getProdPhaseId();
            h = req.getParameter("h");
            m = req.getParameter("m");
            s = req.getParameter("s");
            nOpQueued= (long) session.getAttribute("nQueued");

            new InsertPhaseTime(getDataSourceW().getConnection()).insertTime(h+" hours "+ m+ " minutes "+s+" seconds ",prodPhaseId);
            new UpdateEmployeeBusyDatabase(getDataSourceW().getConnection() ).updateEmployeeBusy(employee.getnOperation()-1, employeeId);

            if ( nOpQueued!=0 ){
                //to get a new running phase_id
                phaseToRun = new ChangePhaseState(getDataSourceR().getConnection()).getNewRunningPhaseId(employeeId);
                //and change his status into running
                new ChangePhaseState(getDataSourceW().getConnection()).changePhaseStatus(phaseToRun);
                nOpQueued=nOpQueued-1;
                session.setAttribute("nQueued", nOpQueued);
                nOpRunn=1;
            }else{
                nOpRunn=0;
            }
            session.setAttribute("nOpRunn", nOpRunn);

            if(nOpRunn!=0) {
                operationsQueued = new ViewOperationsWorker(getDataSourceR().getConnection()).operationsQueued(employeeId);
                operationRunning = new ViewOperationsWorker(getDataSourceR().getConnection()).operationsRunning(employeeId);
                session.setAttribute("operationsQueued",operationsQueued);
                session.setAttribute("operationRunning",operationRunning);
            }
            operationsCompleted = new ViewOperationsWorker(getDataSourceR().getConnection()).operationsCompleted(employeeId);
            session.setAttribute("operationsCompleted", operationsCompleted);
            res.sendRedirect(req.getContextPath()+"/protected/jsp/lineprod_worker/view-operations-worker.jsp");

        }catch (IllegalArgumentException ex) {
            message = new Message("Cannot check the credential. Invalid input parameters.", "E100", ex.getMessage());
            req.setAttribute("message", message);
            req.getRequestDispatcher("/protected/jsp/lineprod_worker/view-operations-worker.jsp").forward(req, res);
        }
        catch (SQLException ex) {
            message = new Message("Cannot add the process. Invalid input parameters: estimated time must be HH:MM:SS.","E100", ex.getMessage());
            req.setAttribute("message", message);
            req.getRequestDispatcher("/protected/jsp/lineprod_worker/view-operations-worker.jsp").forward(req, res);
        }
    }
}
