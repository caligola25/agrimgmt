package it.unipd.dei.webapp.database.accountant;

import it.unipd.dei.webapp.resource.ProductOrder_CustomerName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to access the database and search all the product order with a specific status into a date interval
 */
public final class SearchProductOrderByDateAndStatus {
    /**
     * SQL statement to search all the product order with a specific status inside a date interval
     * column value: customer_name, product_order_date, price
     */
    private static final String STATEMENT = "SELECT CU.customer_name, PO.product_order_date, PO.price " +
            "FROM (Factory.Product_order AS PO INNER JOIN Factory.Customer AS CU ON PO.customer_id = CU.customer_id) " +
            "WHERE (PO.product_order_date >= ?) AND (PO.product_order_date <= ?) AND (PO.order_status = ?::factory.product_order_status OR PO.order_status = ?::factory.product_order_status) " +
            "ORDER BY PO.product_order_date ASC";

    /**
     * Connection to the database
     */
    private final Connection con;
    /**
     * Starting date of the interested period of time
     */
    private final Date startDate;
    /**
     * End date of the interested period of time
     */
    private final Date endDate;
    /**
     * First interested state
     */
    private final String status1;
    /**
     * Second interested state
     */
    private final String status2;

    /**
     * Create a new object to search the interested product order
     *
     * @param con Connection to the database
     * @param startDate Starting date of the interested period of time
     * @param endDate End date of the interested period of time
     * @param status1 First interested status
     * @param status2 Second interested status
     */
    public SearchProductOrderByDateAndStatus(final Connection con, final Date startDate, final Date endDate, final String status1, final String status2) {
        this.con = con;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status1 = status1;
        this.status2 = status2;
    }

    /**
     * Return all the product order with status_1 and status_2 within the period startDate - endDate
     * with the corresponding client name
     *
     * @return A List of type ProductOrder_CustomerName containing in each element a row of the corresponding database table
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<ProductOrder_CustomerName> searchProductOrder() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<ProductOrder_CustomerName> productOrders = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
            pstmt.setString(3, status1);
            pstmt.setString(4, status2);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                productOrders.add(new ProductOrder_CustomerName(
                        rs.getString("customer_name"),
                        rs.getDate("product_order_date"),
                        rs.getFloat("price")
                ));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return productOrders;
    }
}
