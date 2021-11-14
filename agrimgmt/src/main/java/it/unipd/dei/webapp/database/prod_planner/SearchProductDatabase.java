package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Search for a list of {@link Product} stored in the database.
 */
public final class SearchProductDatabase {

    /**
     * The SQL statement to be executed to retrieve a Product given its UUID.
     */
    private static final String STATEMENT = "SELECT * FROM Factory.product WHERE product_id = ?";

    /**
     * The SQL statement to be executed to retrieve a list of all products stored.
     */
    private static final String STATEMENT_ALL = "SELECT * FROM Factory.product ORDER BY product_name ASC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Attribute of the Product to be found
     */
    private final UUID id;

    /**
     * Create an object to search for a Product.
     * @param con the connection to the database.
     */
    public SearchProductDatabase(final Connection con){
        this.con = con;
        this.id = null;
    }

    /**
     * Create an object to search for a Product given its UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchProductDatabase(final Connection con, final UUID id){
        this.con = con;
        this.id = id;
    }

/**
     * Performs the search in the database for all the stored Product.
     * @return A List of all stored products
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<Product> searchProduct() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final List<Product> products = new ArrayList<Product>();

        try{
            pstmt = con.prepareStatement(STATEMENT_ALL);

            res = pstmt.executeQuery();

            while (res.next()) {
                Product product = new Product((UUID) res.getObject("product_id"), res.getString("product_name"), res.getInt("price"), res.getBoolean("available"));
                products.add(product); 
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
        return products;
    }

    /**
     * Performs the search in the database for the Product given the UUID.
     * @return The product with that id
     * @throws SQLException If there is any problem in the SQL execution
     */
    public Product searchProductById() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final Product product;

        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            if(res.next())
                product = new Product((UUID) res.getObject("product_id"), res.getString("product_name"), res.getInt("price"), res.getBoolean("available"));
            else
                product = null;

        }finally {
            if (res != null){
                res.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return product;
    }
}
