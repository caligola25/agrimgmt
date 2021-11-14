package it.unipd.dei.webapp.database.designer;

import it.unipd.dei.webapp.resource.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * View the list of product from the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class ProductListDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT * FROM Factory.product";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * Creates a new object for viewing the list of products from the database.
     *
     * @param con     the connection to the database.
     */
    public ProductListDatabase(final Connection con){
        this.con = con;
    }

    /**
     * Shows a list of the process present into the database
     *
     * @return a list of {@code Product} object.
     *
     * @throws SQLException if any error occurs while retrieving the products.
     */
    public List<Product> productList() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Product> products = new ArrayList<Product>();

        try{
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while(rs.next()){
                products.add(new Product(UUID.fromString(rs.getString("product_id")),
                        rs.getString("product_name"), rs.getInt("price"),
                        rs.getBoolean("available")));
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
        return products;
    }
}
