package it.unipd.dei.webapp.database.line_worker;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Insert the actual time to the production_phase and set the phase as completed
 */
public final class InsertPhaseTime {
    private final Connection con;
    private static final String STATEMENT ="update Factory.production_phase set actual_time= ?::interval , phase_status='completed' where phase_id=?";

    /* QUERY that works
            update Factory.production_phase
            set actual_time='5 hours 2 minutes 10 seconds'::interval , phase_status='completed'
            where phase_id='23e5f65b-238f-4afe-b25e-262311f3c3d3'
     */


    /**Creates a new object for update the production Phase time
     * @param e the connection to the database.
     */
    public InsertPhaseTime(final Connection e){
        this.con = e;
    }

    /** change the state of the running operation into completed and inser actual_time
     * @param time actual time, the time that the employee use for complete that procuction_phase
     * @param phase_id the phase ID that now is completed
     * @throws SQLException if any error occurs while inserting the actual time
     */
    public void insertTime(String time,UUID phase_id) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, time);
            pstmt.setObject(2, phase_id);
            pstmt.execute();

        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
    }
}
