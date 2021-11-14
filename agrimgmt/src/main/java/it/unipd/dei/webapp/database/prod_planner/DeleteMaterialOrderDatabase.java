package it.unipd.dei.webapp.database.prod_planner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unipd.dei.webapp.resource.MaterialOrder;

/**
 * Delete a {@link MaterialOrder} from the database
 */
public final class DeleteMaterialOrderDatabase {

    /**
     * The query to be executed
     */
    private static final String STATEMENT_DELETE = "DELETE FROM Factory.material_order WHERE material_order_id = ?";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * The material order to be deleted from the database
     */
    private final MaterialOrder order;

    /**
     * Create a new Object for deleting a Material Order entry in the database.
     * @param con The connection to the database.
     * @param order The object to be deleted.
     */
    public DeleteMaterialOrderDatabase(Connection con, MaterialOrder order) {
        this.con = con;
        this.order = order;
    }
    
    /**
     * Deletes the Material order from the database.
     * @throws SQLException In case anything goes wrong in the database.
     */
    public void deleteMaterialOrder() throws SQLException{
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_DELETE);
            pstmt.setObject(1, order.getMaterialOrderId());

            pstmt.execute();
        } finally{
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
