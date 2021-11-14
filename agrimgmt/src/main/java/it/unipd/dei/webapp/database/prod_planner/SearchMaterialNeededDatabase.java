package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.RawMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Search for {@link RawMaterial} needed for the production of a product given its UUID.
 */
public final class SearchMaterialNeededDatabase {

    /**
     * The SQL statement to be executed.
     */
    private static final String STATEMENT = "SELECT RM.material_id, SUM(quantity) AS quantity FROM Factory.Process AS PR " +
            "INNER JOIN Factory.Raw_Material AS RM ON PR.material_id = RM.material_id INNER JOIN Factory.Product AS P " +
            "ON P.product_id = PR.product_id WHERE P.product_id = ? " +
            "GROUP BY RM.material_id";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Attribute of the Product made by the materials to found
     */
    private final UUID id;

    /**
     * Create an object to search for a list of materials given the Product UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchMaterialNeededDatabase(final Connection con, final UUID id){
        this.con = con;
        this.id = id;
    }

    /**
     * Performs the search in the database for a list of materials given the Product UUID.
     * @return A list of lists with materials needed for that product id, one per process.
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchMaterialNeededByProductId() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final List<List<String>> materials = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            while (res.next()) {
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(res.getObject("material_id").toString());
                tmp.add(Integer.toString(res.getInt("quantity")));
                materials.add(tmp);
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
        return materials;
    }
}
