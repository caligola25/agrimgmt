package it.unipd.dei.webapp.database.prod_planner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unipd.dei.webapp.resource.Supplier;

/**
 * Delete a {@link Supplier} from the database
 */
public final class DeleteSupplierDatabase {

    /**
     * The query to be executed
     */
    private static final String STATEMENT_DELETE = "DELETE FROM Factory.supplier WHERE supplier_name = ?";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * The supplier to be deleted from the database
     */
    private final Supplier supplier;

    /**
     * Create a new Object for deleting a Supplier entry in the database.
     * @param con The connection to the database.
     * @param supplier The object to be deleted.
     */
    public DeleteSupplierDatabase(Connection con, Supplier supplier) {
        this.con = con;
        this.supplier = supplier;
    }
    
    /**
     * Deletes the Supplier from the database.
     * @throws SQLException In case anything goes wrong in the database.
     */
    public void deleteSupplier() throws SQLException{
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_DELETE);
            pstmt.setString(1, supplier.getName());

            pstmt.execute();
        } finally{
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
    
}
