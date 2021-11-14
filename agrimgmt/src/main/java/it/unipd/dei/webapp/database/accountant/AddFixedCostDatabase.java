package it.unipd.dei.webapp.database.accountant;

import it.unipd.dei.webapp.resource.FixedCost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class to access the database and add a new fixed cost entity
 */
public final class AddFixedCostDatabase {

    /**
     * SQL statement to add a fixed cost entity in the database
     */
    private static final String STATEMENT = "INSERT INTO Factory.Fixed_cost VALUES(?, ?::Factory.FIXED_COST_TYPE, ?, ?)";

    /**
     * Connection to the database
     */
    private final Connection con;
    /**
     * FixedCost it.unipd.dei.webapp.resource that need to be inserted in the database
     */
    private final FixedCost fixedCost;

    /**
     * Create a new object to insert a new fixed cost entity in the database
     *
     * @param con Connection to the database
     * @param fixedCost FixedCost resources element that need to be inserted in the database
     */
    public AddFixedCostDatabase(final Connection con, final FixedCost fixedCost) {
        this.con = con;
        this.fixedCost = fixedCost;
    }

    /**
     * Insert in the database the requested fixed cost entity
     *
     * @throws SQLException if any error occurs while inserting the item
     */
    public void addFixedCost() throws SQLException {
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);

            pstmt.setDate(1, fixedCost.getDate());
            pstmt.setString(2, fixedCost.getType());
            pstmt.setDouble(3, fixedCost.getPrice());
            pstmt.setDate(4, fixedCost.getReport_date());

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
