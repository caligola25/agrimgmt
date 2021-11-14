package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.ProductOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add a new {@link ProductOrder} to the database
 */
public final class AddProductOrderDatabase {

    /**
     * Connection to the database.
     */
    private final Connection con;
    /**
     * The product order to be added to the database
     */
    private final ProductOrder productOrder;
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Factory.product_order VALUES (?, ?, ?::factory.product_order_status, ?, ?, ?)";

    /**
     * Creates a new object to add a product order into the database.
     * @param con 
     *          the connection to the database.
     * @param productOrder
     *          the product order to be added to the database. 
     */
    public AddProductOrderDatabase(final Connection con, final ProductOrder productOrder) {
        this.con = con;
        this.productOrder = productOrder;
    }

    /**
     * Adds a new product order to the database
     * @throws SQLException
     *          if any error occurs while inserting the product order
     */
    public void addProductOrder() throws SQLException {
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, productOrder.getProdOrderId());
            pstmt.setDate(2, productOrder.getDate());
            pstmt.setString(3, productOrder.getStatus());
            pstmt.setFloat(4, productOrder.getPrice());
            pstmt.setObject(5, productOrder.getCustomerId());
            pstmt.setDate(6, productOrder.getReportDate());

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