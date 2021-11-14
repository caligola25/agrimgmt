package it.unipd.dei.webapp.database.line_worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Change the phase state and give us some info about the production phase
 */
public final class ChangePhaseState {

    //return a phase_id of one queued operation
    private static final String STATEMENT_1 ="select Factory.production_phase.phase_id from Factory.production_phase where phase_status='queued' and employee_id=? fetch first 1 rows only";
    //change the state of the queued operation into running op
    private static final String STATEMENT_2 ="update Factory.production_phase set phase_status='running' where phase_id=?;";
    private static final String STATEMENT_3 ="select count(*) from Factory.production_phase where phase_status='queued' and employee_id=?;";
    private static final String STATEMENT_4 ="select count(*) from Factory.production_phase where phase_status='running' and employee_id=?;";
    private final Connection con;

    /** Creates a new object for update the production Phase
     * @param e the connection to the database.
     */
    public ChangePhaseState(final Connection e ){
        this.con = e;
    }


    /**
     * @param employee_id UUID of the employee
     * @return the number of the prodcution_phase in queued phase for that employee
     * @throws SQLException if any error occurs while counting the number of queued operations
     */
    public long getNumberQueudOp(UUID employee_id) throws  SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs;
        long nOpQueued=0;
        try {
            pstmt = con.prepareStatement(STATEMENT_3);
            pstmt.setObject(1, employee_id);
            rs = pstmt.executeQuery();
            rs.next();
            nOpQueued= (long) rs.getObject(1);
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return nOpQueued;
    }

    /**
     * @param employee_id UUID of the employee
     * @return the number of the prodcution_phase in running phase for that employee
     * @throws SQLException if any error occurs while counting the number of running operations
     */
    public long getNumberRunningOp(UUID employee_id) throws  SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs;
        long nOp=0;
        try {
            pstmt = con.prepareStatement(STATEMENT_4);
            pstmt.setObject(1, employee_id);
            rs = pstmt.executeQuery();
            rs.next();
            nOp= (long) rs.getObject(1);
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return nOp;
    }

    /**
     * @param employee_id UUID of the employee
     * @return a new UUID that represent a phase that is queued
     * @throws SQLException if any error occurs while getting a new phase
     */
    public UUID getNewRunningPhaseId(UUID employee_id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;
        UUID newRunningPhase_id;
        try {
            pstmt = con.prepareStatement(STATEMENT_1);
            pstmt.setObject(1, employee_id);
            rs = pstmt.executeQuery();
            rs.next();
            newRunningPhase_id=(UUID) rs.getObject("phase_id");
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return newRunningPhase_id;
    }

    /**
     * @param phase_id UUID of the phase and change the state from queued to running
     * @throws SQLException if any error occurs while changing the state of the operation
     */
    public void changePhaseStatus(UUID phase_id) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(STATEMENT_2);
            pstmt.setObject(1, phase_id);
            pstmt.execute();
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
    }
}
