package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.ProductOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Retrieve the {@link ProductOrder} list of product orders stored in the database.
 */
public final class ProductOrderListDatabase {

    /**
     * The SQL statement to be executed to retrieve the whole list associated with the Customer name.
     */
    private static final String STATEMENT = "SELECT product_order_id, product_order_date, order_status, price, customer_name, report_date " +
            "FROM (Factory.product_order AS P INNER JOIN Factory.customer AS C ON P.customer_id = C.customer_id) ORDER BY product_order_date DESC";

    /**
     * The SQL statement to be executed to retrieve the list of in production product orders only associated with the Customer name.
     */
    private static final String STATEMENT_INPROD = "SELECT product_order_id, product_order_date, order_status, price, customer_name " +
            "FROM (Factory.product_order AS P INNER JOIN Factory.customer AS C ON P.customer_id = C.customer_id) WHERE order_status = 'in_production' " +
            "ORDER BY product_order_date DESC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Create an object to search for ProductOrder.
     * @param con the connection to the database.
     */
    public ProductOrderListDatabase(Connection con) {
        this.con = con;
    }

    /**
     * Performs the search in the database for all ProductOrders associated with the Customer name.
     * @return A List of Lists representing all the ProductOrders.
     * @throws SQLException If there is any problem in the SQL execution.
     */
    public List<List<String>> productOrderList() throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet res = null;
        final List<List<String>> orders = new ArrayList<>();

        try {

            pstmt = con.prepareStatement(STATEMENT);
            res = pstmt.executeQuery();

            //interested in the only result
            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("product_order_id"));
                tmp.add(res.getDate("product_order_date").toString());
                tmp.add(res.getString("customer_name"));
                tmp.add(res.getString("order_status"));
                tmp.add(Float.toString(res.getFloat("price")));
                if (res.getDate("report_date") == null)
                    tmp.add(null);
                else
                    tmp.add(res.getDate("report_date").toString());
                orders.add(tmp);
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
        return orders;
    }

    /**
     * Performs the search in the database for ProductOrders in production associated with the Customer name.
     * @return A list of list representing all the ProductOrders in production.
     * @throws SQLException If there is any problem in the SQL execution.
     */
    public List<List<String>> productOrderListInProduction() throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet res = null;
        final List<List<String>> orders = new ArrayList<>();

        try {

            pstmt = con.prepareStatement(STATEMENT_INPROD);
            res = pstmt.executeQuery();

            //interested in the only result
            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("product_order_id"));
                tmp.add(res.getDate("product_order_date").toString());
                tmp.add(res.getString("customer_name"));
                tmp.add(res.getString("order_status"));
                tmp.add(Float.toString(res.getFloat("price")));
                orders.add(tmp);
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
        return orders;
    }
}
