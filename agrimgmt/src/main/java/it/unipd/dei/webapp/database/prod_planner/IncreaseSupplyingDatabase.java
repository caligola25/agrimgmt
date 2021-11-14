package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Supplier;
import it.unipd.dei.webapp.resource.Supplying;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Add a new {@link Supplier} to the database.
 */
public final class IncreaseSupplyingDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Factory.Supplying (supplying_id, material_id, quantity, material_order_id, supplier_name) " +
            "VALUES (?, ?, ?, ?, ?)";

    /**
     * Connection to the database
     */
    private final Connection con;

    /**
     * The supplying to be added into the database
     */

    private final Supplying supplying;

    /**
     * Creates a new object for adding a supplying into the database.
     *
     * @param con
     *            the connection to the database.
     * @param supplying
     *            the supplying to be added into the database.
     */

    public IncreaseSupplyingDatabase(final Connection con, final Supplying supplying){
        this.con = con;
        this.supplying = supplying;
    }

    /**
     * Adds a new raw supplying into the database
     *
     * @throws SQLException
     *             if any error occurs while adding the supplying.
     */
    public void increaseSupplying() throws SQLException{
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, UUID.randomUUID());
            pstmt.setObject(2, supplying.getMaterialId());
            pstmt.setInt(3, supplying.getQuantity());
            pstmt.setObject(4, supplying.getMaterialIdOrder());
            pstmt.setString(5, supplying.getSupplierName());

            pstmt.execute();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
