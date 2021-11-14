package it.unipd.dei.webapp.database.warehouse_worker;

import it.unipd.dei.webapp.resource.Item;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Modify the status of an item

 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class UpdateStatusItemDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE Factory.item SET item_status=?::Factory.ITEM_STATUS WHERE item_id = ? RETURNING *";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The product to be added into the database
     */
    private final UUID item_id;
    private final String item_status;


    /**
     * Creates a new object for modify the item into the database.
     *
     * @param con     the connection to the database.
     * @param item_id the id of the item that will be modified into the database
     * @param item_status  the status changed of the item into the database.
     */
    public UpdateStatusItemDatabase(final Connection con, final UUID item_id, final String item_status) {
        this.con = con;
        this.item_id = item_id;
        this.item_status = item_status;
    }

    /**
     * Update status of an item into the database
     *
     * @return the Item that will be modified.
     *
     * @throws SQLException if any error occurs while adding the product.
     */
    public Item updateItemStatus() throws SQLException {

        PreparedStatement pstmt = null;
        Item item = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, item_status);
            pstmt.setObject(2, item_id);

            //err = pstmt.executeBatch().length;
            rs = pstmt.executeQuery();

            while(rs.next()) {
                item = new Item(
                        (UUID) rs.getObject("item_id"),
                        rs.getString("item_status"),
                        (UUID) rs.getObject("product_order_id"),
                        (UUID) rs.getObject("product_id")
                );
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return item;

    }

}
