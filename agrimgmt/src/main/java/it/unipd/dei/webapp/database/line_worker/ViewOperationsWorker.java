package it.unipd.dei.webapp.database.line_worker;

import it.unipd.dei.webapp.resource.ProductionPhase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Class used for view all the operations
 */
public final class ViewOperationsWorker {

    private static final String STATEMENT_COMPLETED="Select * From Factory.production_phase where Factory.production_phase.employee_id=? and Factory.production_phase.phase_status='completed'";
    private static final String STATEMENT_QUEUED="Select * From Factory.production_phase where Factory.production_phase.employee_id=? and Factory.production_phase.phase_status='queued'";
    private static final String STATEMENT_RUNNING="Select * From Factory.production_phase where Factory.production_phase.employee_id=? and Factory.production_phase.phase_status='running'";
    private final Connection con;


    /**
     *Creates a new object for view all operations
     * @param con the connection to the database.
     */
    public ViewOperationsWorker(final Connection con ) {
        this.con = con;
    }

    /**
     * Show all the operations that are completed
     * @param employee_id employee's id of the completed operations
     * @return the list of ProductionPhase completed by the employee
     * @throws SQLException
     *      if any error occurs while executing the query for take the operations completed
     */
    public List<ProductionPhase> operationsCompleted( UUID employee_id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final List<ProductionPhase> operations = new ArrayList<ProductionPhase>();
        try {
            pstmt = con.prepareStatement(STATEMENT_COMPLETED);
            pstmt.setObject(1, employee_id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                operations.add(new ProductionPhase(
                        (UUID) rs.getObject("phase_id"),
                        (UUID) rs.getObject("item_id"),
                        (UUID) rs.getObject("process_id"),
                        (UUID) rs.getObject("employee_id"),
                        rs.getString("phase_status"),
                        rs.getString("actual_time")
                ));
            }
        }finally {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            con.close();
        }
        return operations;
    }

    /**
     * Show all the operations that are queued
     * @param employee_id employee's id of the queued operations
     * @return the list of ProductionPhase queued for the employee
     * @throws SQLException
     *      if any error occurs while executing the query for take the operations queued
     */
    public List<ProductionPhase> operationsQueued(UUID employee_id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final List<ProductionPhase> operations = new ArrayList<ProductionPhase>();
        try {
            pstmt = con.prepareStatement(STATEMENT_QUEUED);
            pstmt.setObject(1, employee_id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                operations.add(new ProductionPhase(
                        (UUID) rs.getObject("phase_id"),
                        (UUID) rs.getObject("item_id"),
                        (UUID) rs.getObject("process_id"),
                        (UUID) rs.getObject("employee_id"),
                        rs.getString("phase_status"),
                        rs.getString("actual_time")
                ));
            }
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return operations;
    }

    /**
     * Show the operation that is running.
     * @param employee_id The id of the employee.
     * @return the ProductionPhase in running state.
     * @throws SQLException If there is a problem with the connection to the database.
     */
    public ProductionPhase operationsRunning(UUID employee_id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final List<ProductionPhase> operations = new ArrayList<ProductionPhase>();
        try {
            pstmt = con.prepareStatement(STATEMENT_RUNNING);
            pstmt.setObject(1, employee_id);
            rs = pstmt.executeQuery();
            rs.next();
            operations.add(new ProductionPhase(
                    (UUID) rs.getObject("phase_id"),
                    (UUID) rs.getObject("item_id"),
                    (UUID) rs.getObject("process_id"),
                    (UUID) rs.getObject("employee_id"),
                    rs.getString("phase_status"),
                    rs.getString("actual_time")
                ));

        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return operations.get(0);
    }

}
