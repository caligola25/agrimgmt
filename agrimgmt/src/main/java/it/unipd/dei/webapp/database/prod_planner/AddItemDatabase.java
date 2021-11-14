package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Performs the query to add a new {@link Item} into the database
 */
public final class AddItemDatabase {

    /**
     * Connection to the database.
     */
    private final Connection con;
    /**
     * The item to be added to the database
     */
    private final Item item;
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Factory.item VALUES (?, ?::factory.item_status, ?, ?)";

    /**
     * Creates a new object to add a item into the database.
     * @param con
     *          the connection to the database.
     * @param item
     *          the item to be added to the database.
     */
    public AddItemDatabase(final Connection con, final Item item) {
        this.con = con;
        this.item = item;
    }

    /**
     * Adds a new item to the database
     * @throws SQLException
     *          if any error occurs while inserting the item
     */
    public void addItem() throws SQLException {
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, item.getItemId());
            pstmt.setString(2, item.getItemStatus());
            pstmt.setObject(3, item.getProductOrderId());
            pstmt.setObject(4, item.getProductId());

            pstmt.execute();
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
