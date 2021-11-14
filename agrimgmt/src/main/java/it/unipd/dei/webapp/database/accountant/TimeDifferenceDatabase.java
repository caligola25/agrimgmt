package it.unipd.dei.webapp.database.accountant;

import it.unipd.dei.webapp.resource.TimeDifference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to access the database and retrieve the time difference between the estimated process time
 * and the actual process time for every production phase
 */
public final class TimeDifferenceDatabase {

    /**
     * SQL statement to retrieve all the data (process_name, estimated_time, average_actual_time, difference)
     */
    private static final String STATEMENT = "SELECT PR.process_name, PR.estimated_time, AVG(PP.actual_time) AS average_actual_time, SUM(PP.actual_time - PR.estimated_time) AS difference " +
            "FROM (Factory.Process AS PR " +
            "INNER JOIN Factory.Production_phase AS PP " +
            "ON PR.process_id = PP.process_id " +
            ") " +
            "GROUP BY PR.process_name, PR.estimated_time " +
            "ORDER BY PR.process_name ASC";

    /**
     * Connection to the database
     */
    private final Connection con;

    /**
     * Create a new object to access all the needed information in the database
     *
     * @param con Connection to the database
     */
    public TimeDifferenceDatabase(final Connection con) {
        this.con = con;
    }

    /**
     * Return the TimeDifference table
     *
     * @return A list of type TimeDifference containing in each element a row of the corresponding database table
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<TimeDifference> retrieveTimeDifference() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<TimeDifference> td = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                td.add(new TimeDifference(
                        rs.getString("process_name"),
                        rs.getString("estimated_time"),
                        rs.getString("average_actual_time"),
                        rs.getString("difference")
                ));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return td;
    }
}
