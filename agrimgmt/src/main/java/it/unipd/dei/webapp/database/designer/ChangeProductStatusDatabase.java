package it.unipd.dei.webapp.database.designer;

import it.unipd.dei.webapp.resource.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Class to access the database and change the product's status
 */
public final class ChangeProductStatusDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE Factory.product SET available = ? WHERE product_id = ? RETURNING *";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The product id of the product to be modified into the database
     */
    private final UUID productId;

    /**
     * The status of the product to be changed into the database
     */
    private final boolean available;


    /**
     * Creates a new object for adding a product into the database.
     *
     * @param con     the connection to the database.
     * @param productId the id of the product that will be modified into the database
     * @param available  the status changed of the product into the database.
     */
    public ChangeProductStatusDatabase(final Connection con, final UUID productId, final Boolean available) {
        this.con = con;
        this.productId = productId;
        this.available = available;
    }

    /**
     * Change status of a product into the database
     *
     * @return a Product resource with the status updated
     * @throws SQLException if any error occurs while adding the product.
     */
    public Product changeProductStatus() throws SQLException {

        PreparedStatement pstmt = null;
        Product product = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setBoolean(1, available);
            pstmt.setObject(2, productId);

            rs = pstmt.executeQuery();

            while(rs.next()){
                product = new Product((UUID) rs.getObject("product_id"),
                        rs.getString("product_name"),
                        rs.getFloat("price"),
                        rs.getBoolean("available"));
            }
        }finally {
            if (rs != null){
                rs.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return product;

    }

}