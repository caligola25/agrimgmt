package it.unipd.dei.webapp.database.prod_planner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.unipd.dei.webapp.resource.Item;

/**
 * Search for {@link Item} stored in the database.
 */
public final class SearchItemDatabase {
    /**
     * The SQL statement to be executed to retrieve the item given its UUID.
     */
    private static final String STATEMENT = "SELECT * FROM Factory.Item WHERE product_order_id = ?";

    /**
     * The SQL statement to be executed to retrieve a list of items in production given their product order UUID.
     */
    private static final String STATEMENT_INPROD = "SELECT item_id, P.product_id, product_name, item_status, price " +
            "FROM Factory.Item AS I INNER JOIN Factory.Product AS P ON I.product_id = P.product_id " +
            "WHERE product_order_id = ? ORDER BY item_status ASC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * UUID of the Product Order.
     */
    private final UUID id;

    /**
     * Create an object to search for a list of items given the Product Order UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchItemDatabase(final Connection con, final UUID id){
        this.con = con;
        this.id = id;
    }

    /**
     * Performs the search in the database for a list of items given the Product UUID.
     * @return A list of Items that are related to the given Product Order.
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<Item> searchItemByProdOrderId() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final List<Item> items = new ArrayList<Item>();

        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            while (res.next()) {
                UUID item_id = UUID.fromString(res.getString("item_id"));
                String item_status = res.getString("item_status");
                UUID product_order_id = UUID.fromString(res.getString("product_order_id"));
                UUID product_id = UUID.fromString(res.getString("product_id"));
                Item it = new Item(item_id, item_status, product_order_id, product_id);
                items.add(it);
            }

        }finally {
            if (res != null){
                res.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return items;
    }

    /**
     * Performs the search in the database for a list of items given the Product UUID with the product name and order by item status.
     * @return A list of list representing items that are related to the given Product Order.
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchItemNameByProdOrderId() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final List<List<String>> items = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT_INPROD);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("item_id"));
                tmp.add(res.getString("product_id"));
                tmp.add(res.getString("product_name"));
                tmp.add(res.getString("item_status"));
                tmp.add(Float.toString(res.getFloat("price")));
                items.add(tmp);
            }

        }finally {
            if (res != null){
                res.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return items;
    }
}
