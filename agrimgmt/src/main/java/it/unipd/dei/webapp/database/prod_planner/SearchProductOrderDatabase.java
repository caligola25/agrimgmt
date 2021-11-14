package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.ProductOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Search for {@link ProductOrder} stored in the database.
 */
public final class SearchProductOrderDatabase {
    /**
     * The SQL statementS to be executed to retrieve a product order given its UUID.
     */
    private static final String STATEMENT_ID = "SELECT product_order_id, product_order_date, order_status, price, customer_name, report_date " +
            "FROM (Factory.product_order AS P INNER JOIN Factory.customer AS C ON P.customer_id = C.customer_id) " +
            "WHERE product_order_id = ? ORDER BY product_order_date DESC";

    /**
     * The SQL statementS to be executed to retrieve a list of product orders given its customer name.
     */
    private static final String STATEMENT_NAME = "SELECT product_order_id, product_order_date, order_status, price, customer_name, report_date " +
            "FROM (Factory.product_order AS P INNER JOIN Factory.customer AS C ON P.customer_id = C.customer_id) " +
            "WHERE customer_name = ? ORDER BY product_order_date DESC";

    /**
     * The SQL statementS to be executed to retrieve a product order given its status.
     */
    private static final String STATEMENT_STATUS = "SELECT product_order_id, product_order_date, order_status, price, customer_name, report_date " +
            "FROM (Factory.product_order AS P INNER JOIN Factory.customer AS C ON P.customer_id = C.customer_id) " +
            "WHERE order_status = ?::factory.product_order_status ORDER BY product_order_date DESC";

    /**
     * The SQL statementS to be executed to retrieve a product order given its date.
     */
    private static final String STATEMENT_DATE = "SELECT product_order_id, product_order_date, order_status, price, customer_name, report_date " +
            "FROM (Factory.product_order AS P INNER JOIN Factory.customer AS C ON P.customer_id = C.customer_id) " +
            "WHERE product_order_date = ? ORDER BY customer_name ASC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Attribute of the ProductOrder to be found
     */
    private final UUID id;
    private final String nameOrStatus;
    private final Date date;

    /**
     * Create an object to search for a ProductOrder given its UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchProductOrderDatabase(Connection con, UUID id) {
        this.con = con;
        this.id = id;
        this.nameOrStatus = null;
        this.date = null;
    }

    /**
     * Create an object to search for all ProductOrder given the Customer name or the Status.
     * @param con the connection to the database.
     * @param nameOrStatus the customer name or the status of orders we want to find.
     */
    public SearchProductOrderDatabase(Connection con, String nameOrStatus) {
        this.con = con;
        this.nameOrStatus = nameOrStatus;
        this.id = null;
        this.date = null;
    }

    /**
     * Create an object to search for all ProductOrder given the Date.
     * @param con the connection to the database.
     * @param date the date of orders we want to find.
     */
    public SearchProductOrderDatabase(Connection con, Date date) {
        this.con = con;
        this.date = date;
        this.id = null;
        this.nameOrStatus = null;
    }

    /**
     * Performs the search in the database for the ProductOrder given the UUID.
     * @return An List of Lists representing the production orders with that id
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchProductOrderById() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet res = null;

        List<List<String>> order = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(STATEMENT_ID);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            //interested in the only result
            if (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("product_order_id"));
                tmp.add(res.getDate("product_order_date").toString());
                tmp.add(res.getString("customer_name"));
                tmp.add(res.getString("order_status"));
                tmp.add(Float.toString(res.getFloat("price")));
                if (res.getDate("report_date") == null)
                    tmp.add("");
                else
                    tmp.add(res.getDate("report_date").toString());
                order.add(tmp);
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
        return order;
    }

    /**
     * Performs the search in the database for ProductOrders given the name of the Customer.
     * @return A List of Lists representing the production orders made by the Customer
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchProductOrderByCustomerName() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        final List<List<String>> orders = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT_NAME);
            pstmt.setString(1, nameOrStatus);

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
                    tmp.add("");
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
     * Performs the search in the database for ProductOrders given the status.
     * @return An List of Lists representing the production orders with that status
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchProductOrderByStatus() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        final List<List<String>> orders = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT_STATUS);
            pstmt.setString(1, nameOrStatus);

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
                    tmp.add("");
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
     * Performs the search in the database for ProductOrders given the date.
     * @return A List of Lists representing the production orders with that date
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchProductOrderByDate() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        final List<List<String>> orders = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT_DATE);
            pstmt.setDate(1, date);

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
                    tmp.add("");
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
}