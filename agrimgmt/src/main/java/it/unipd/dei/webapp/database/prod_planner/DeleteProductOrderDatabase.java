package it.unipd.dei.webapp.database.prod_planner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unipd.dei.webapp.resource.ProductOrder;

/**
 * Delete a {@link ProductOrder} from the database
 */
public final class DeleteProductOrderDatabase {

    /**
     * The query to be executed
     */
    private static final String STATEMENT_DELETE = "DELETE FROM Factory.product_order WHERE product_order_id = ?";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * The product order to be deleted from the database
     */
    private final ProductOrder order;

    /**
     * Create a new Object for deleting a Product Order entry in the database.
     * @param con The connection to the database.
     * @param order The object to be deleted.
     */
    public DeleteProductOrderDatabase(Connection con, ProductOrder order) {
        this.con = con;
        this.order = order;
    }
    
    /**
     * Deletes the Product Order from the database.
     * @throws SQLException In case anything goes wrong in the database.
     */
    public void deleteProductOrder() throws SQLException{
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_DELETE);
            pstmt.setObject(1, order.getProdOrderId());

            pstmt.execute();
        } finally{
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
