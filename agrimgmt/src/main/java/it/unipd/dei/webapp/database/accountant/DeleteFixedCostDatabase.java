package it.unipd.dei.webapp.database.accountant;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class to access the database and delete a specific fixed cost entity
 */
public final class DeleteFixedCostDatabase {
    /**
     * SQL statement to remove a fixed cost entity with a specific date and type
     */
    private static final String STATEMENT = "DELETE FROM Factory.Fixed_cost " +
            "WHERE (fixed_cost_date = ?) AND (fixed_cost_type = ?::Factory.FIXED_COST_TYPE)";

    /**
     * Connection to the database
     */
    private final Connection con;
    /**
     * Fixed cost type used to select the correct fixed cost entity (in conjunction with date)
     */
    private final String type;
    /**
     * Fixed cost date used to select the correct fixed cost entity (in conjunction with type)
     */
    private final Date date;

    /**
     * Create a new object to remove a fixed cost entity from the database
     *
     * @param con Connection to the database
     * @param type Fixed cost type used to select the correct fixed cost entity (in conjunction with date)
     * @param date Fixed cost date used to select the correct fixed cost entity (in conjunction with type)
     */
    public DeleteFixedCostDatabase(final Connection con, final String type, final Date date) {
        this.con = con;
        this.type = type;
        this.date = date;
    }

    /**
     * Remove from the database the requested fixed cost entity
     *
     * @throws SQLException if any error occurs while inserting the item
     */
    public void deleteFixedCost() throws SQLException {
        PreparedStatement pstmt = null;

        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setDate(1, date);
            pstmt.setString(2, type);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
