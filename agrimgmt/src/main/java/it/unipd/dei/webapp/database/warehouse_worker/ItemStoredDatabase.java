package it.unipd.dei.webapp.database.warehouse_worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Show all the the item stored in the warehouse

 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class ItemStoredDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT IT.item_id, IT.product_order_id, IT.product_id, PR.product_name " +
            "FROM Factory.item AS IT INNER JOIN Factory.product AS PR ON IT.product_id = PR.product_id " +
            "WHERE IT.item_status='stored'";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * Creates a new object for showing the all the item stored into the warehouse.
     *
     * @param con
     *            the connection to the database.
     */
    public ItemStoredDatabase (final Connection con){
        this.con = con;
    }

    /**
     * Searches all the item stored into the warehouse.
     *
     * @return A List of Lists representing all the item with status 'stored' in the database.
     *
     * @throws SQLException
     *             if any error occurs while searching for stored item.
     */
    public List<List<String>> showItemStored() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        List<List<String>> items = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(STATEMENT);

            res = pstmt.executeQuery();

            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("item_id"));
                tmp.add(res.getString("product_order_id"));
                tmp.add(res.getString("product_id"));
                tmp.add(res.getString("product_name"));

                items.add(tmp);
            }
        } finally {
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return items;
    }
}
