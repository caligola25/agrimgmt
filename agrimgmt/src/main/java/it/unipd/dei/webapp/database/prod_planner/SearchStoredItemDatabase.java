package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Search for {@link Item} stored in the warehouse from the database.
 */
public final class SearchStoredItemDatabase {

    /**
     * The SQL statement to be executed to retrieve a stored item given its UUID.
     */
    private static final String STATEMENT_ID = "SELECT I.item_id, P.product_id, P.product_name, PO.price FROM Factory.item AS I " +
            "INNER JOIN Factory.product AS P ON I.product_id = P.product_id INNER JOIN Factory.product_order AS PO ON I.product_order_id = PO.product_order_id " +
            "WHERE I.item_status = 'stored' AND (PO.order_status = 'not_paid' OR PO.order_status = 'cancelled') AND P.product_id = ? " +
            "GROUP BY I.item_id, P.product_id, P.product_name, PO.price ORDER BY product_name ASC";

    /**
     * The SQL statement to be executed to retrieve a list of stored items given its product name.
     */
    private static final String STATEMENT_NAME = "SELECT I.item_id, P.product_id, P.product_name, PO.price FROM Factory.item AS I " +
            "INNER JOIN Factory.product AS P ON I.product_id = P.product_id INNER JOIN Factory.product_order AS PO ON I.product_order_id = PO.product_order_id " +
            "WHERE I.item_status = 'stored' AND (PO.order_status = 'not_paid' OR PO.order_status = 'cancelled') AND product_name = ? " +
            "GROUP BY I.item_id, P.product_id, P.product_name, PO.price ORDER BY product_name ASC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Attribute of the Stored Unsold Items to be found
     */
    private final UUID id;
    private final String name;

    /**
     * Create an object to search for a Stored Unsold Item given its UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchStoredItemDatabase(Connection con, UUID id) {
        this.con = con;
        this.id = id;
        this.name = null;
    }

    /**
     * Create an object to search for all Stored Material given the name.
     * @param con the connection to the database.
     * @param name the material name of material we want to find.
     */
    public SearchStoredItemDatabase(Connection con, String name) {
        this.con = con;
        this.name = name;
        this.id = null;
    }

    /**
     * Performs the search in the database for the Stored Material given the UUID.
     * @return An List of Lists representing the stored material with that id
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchStoredItemById() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet res = null;

        List<List<String>> items = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(STATEMENT_ID);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            //interested in the only result
            if (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("item_id"));
                tmp.add(res.getString("product_id"));
                tmp.add(res.getString("product_name"));
                tmp.add(Integer.toString(res.getInt("price")));
                items.add(tmp);
            }
        }
        finally {
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return items;
    }

    /**
     * Performs the search in the database for Stored Unsold Item given the name of the Product.
     * @return A List of Lists representing the stored unsold items with that name.
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchStoredItemByName() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        final List<List<String>> items = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT_NAME);
            pstmt.setString(1, name);

            res = pstmt.executeQuery();

            //interested in the only result
            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("item_id"));
                tmp.add(res.getString("product_id"));
                tmp.add(res.getString("product_name"));
                tmp.add(Integer.toString(res.getInt("price")));
                items.add(tmp);
            }
        }
        finally {
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
