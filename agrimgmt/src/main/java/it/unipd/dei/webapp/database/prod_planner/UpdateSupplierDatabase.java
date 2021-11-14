package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Update a {@link Supplier} stored in the database.
 */
public final class UpdateSupplierDatabase {

    /**
     * The SQL statement to be executed to update the name of a Supplier.
     */
    private static final String STATEMENT_NAME = "UPDATE Factory.supplier " +
            "SET supplier_name = ? " +
            "WHERE supplier_name = ?";

    /**
     * The SQL statement to be executed to update the country of a Supplier.
     */
    private static final String STATEMENT_COUNTRY = "UPDATE Factory.supplier " +
        "SET country = ? " +
        "WHERE supplier_name = ?";

    /**
     * The SQL statement to be executed to update a Supplier.
     */
    private static final String STATEMENT_ALL = "UPDATE Factory.supplier " +
            "SET supplier_name = ?, country = ? "+
            "WHERE supplier_name = ?";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * Attributes of the Customer to be updated.
     */
    private final String oldName;
    private final String newName;
    private final String newCountry;

    /**
     * Create an object to update a Supplier.
     *
     * @param con the connection to the database.
     * @param oldName the current name of the Supplier
     * @param newName the new name of the Supplier
     * @param newCountry the new country of the Supplier
     */
    public UpdateSupplierDatabase(final Connection con, final String oldName, final String newName, final String newCountry) {
        this.con = con;
        this.oldName = oldName;
        this.newName = newName;
        this.newCountry = newCountry;
    }

    /**
     * Performs the update of the given supplier name
     *
     * @throws SQLException if any error occurs accessing the database
     */
    public void updateSupplierName() throws SQLException {
        PreparedStatement pstmt = null;

        if (newName.equals(""))
            throw new IllegalArgumentException("New Name must not be empty.");

        try{
            pstmt = con.prepareStatement(STATEMENT_NAME);
            pstmt.setString(1, newName);
            pstmt.setString(2, oldName);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }

    /**
     * Performs the update of the given supplier country
     *
     * @throws SQLException if any error occurs accessing the database
     */
    public void updateSupplierCountry() throws SQLException, IllegalArgumentException {
        PreparedStatement pstmt = null;

        if (newCountry.equals(""))
            throw new IllegalArgumentException("New Country must not be empty.");
        
        try{
            pstmt = con.prepareStatement(STATEMENT_COUNTRY);
            pstmt.setString(1, newCountry);
            pstmt.setString(2, oldName);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }

    /**
     * Performs the update of the given supplier
     *
     * @throws SQLException if any error occurs accessing the database
     */
    public void updateSupplierAll() throws SQLException, IllegalArgumentException {
        PreparedStatement pstmt = null;

        if (newName.equals("") || newCountry.equals(""))
            throw new IllegalArgumentException("New Name, New Country must not be empty.");
        
        try{
            pstmt = con.prepareStatement(STATEMENT_ALL);
            pstmt.setString(1, newName);
            pstmt.setString(2, newCountry);
            pstmt.setString(3, oldName);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
    
}
