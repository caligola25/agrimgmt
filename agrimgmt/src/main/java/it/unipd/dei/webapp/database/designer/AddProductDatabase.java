package it.unipd.dei.webapp.database.designer;

import it.unipd.dei.webapp.resource.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add a new Product into the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class AddProductDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Factory.product (product_id, product_name, price, available) VALUES (?, ?, ?, ?)";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The product to be added into the database
     */
    private final Product product;

    /**
     * Creates a new object for adding a product into the database.
     *
     * @param con     the connection to the database.
     * @param product the product to be added into the database.
     */
    public AddProductDatabase(final Connection con, final Product product) {
        this.con = con;
        this.product = product;
    }

    /**
     * Adds a new product into the database
     *
     * @throws SQLException if any error occurs while adding the product.
     */
    public void addProduct() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, product.getProductId());
            pstmt.setString(2, product.getProductName());
            pstmt.setFloat(3, product.getPrice());
            pstmt.setBoolean(4, product.isAvailable());

            pstmt.execute();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

    }

}