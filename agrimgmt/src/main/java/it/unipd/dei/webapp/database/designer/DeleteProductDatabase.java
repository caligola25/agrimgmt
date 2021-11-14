package it.unipd.dei.webapp.database.designer;

import it.unipd.dei.webapp.resource.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Delete a Product from the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class DeleteProductDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "DELETE FROM Factory.product WHERE product_id = ?";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The product to be deleted into the database
     */
    private final Product product;

    /**
     * Creates a new object for deleting a product from the database.
     *
     * @param con     the connection to the database.
     * @param product the product to be deleted from the database.
     */
    public DeleteProductDatabase(final Connection con, final Product product) {
        this.con = con;
        this.product = product;
    }

    /**
     * Deletes a new product into the database
     *
     * @throws SQLException if any error occurs while adding the product.
     */
    public void deleteProduct() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, product.getProductId());

            pstmt.execute();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

    }

}