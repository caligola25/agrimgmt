package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.RawMaterial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Search for {@link RawMaterial} stored in he warehouse from the database.
 */
public final class SearchStoredMaterialDatabase {

    /**
     * The SQL statement to be executed to retrieve a material stored in the warehouse with its quantity given its UUID.
     */
    private static final String STATEMENT_ID = "SELECT MAT_U.material_id, material_name, SUM(mat_bought) - SUM(mat_used) AS quantity " +
            "FROM (SELECT material_id, SUM(quantity) AS mat_used" +
            "   FROM Factory.Production_phase AS PP" +
            "       INNER JOIN Factory.Process AS PR" +
            "       ON PP.process_id = PR.process_id" +
            "   GROUP BY material_id" +
            ") AS MAT_U " +
            "INNER JOIN (" +
            "   SELECT RM.material_id, material_name, SUM(S.quantity) AS mat_bought" +
            "       FROM Factory.Supplying AS S" +
            "       INNER JOIN Factory.Raw_Material AS RM" +
            "       ON S.material_id = RM.material_id" +
            "       GROUP BY RM.material_id" +
            ") AS MAT_B " +
            "ON MAT_U.material_id = MAT_B.material_id " +
            "WHERE MAT_U.material_id = ? " +
            "GROUP BY MAT_U.material_id, material_name";

    /**
     * The SQL statement to be executed to retrieve a material stored in the warehouse with its quantity given its name.
     */
    private static final String STATEMENT_NAME = "SELECT MAT_U.material_id, material_name, SUM(mat_bought) - SUM(mat_used) AS quantity " +
            "FROM (SELECT material_id, SUM(quantity) AS mat_used" +
            "   FROM Factory.Production_phase AS PP" +
            "       INNER JOIN Factory.Process AS PR" +
            "       ON PP.process_id = PR.process_id" +
            "   GROUP BY material_id" +
            ") AS MAT_U " +
            "INNER JOIN (" +
            "   SELECT RM.material_id, material_name, SUM(S.quantity) AS mat_bought" +
            "       FROM Factory.Supplying AS S" +
            "       INNER JOIN Factory.Raw_Material AS RM" +
            "       ON S.material_id = RM.material_id" +
            "       GROUP BY RM.material_id" +
            ") AS MAT_B " +
            "ON MAT_U.material_id = MAT_B.material_id " +
            "WHERE material_name = ? " +
            "GROUP BY MAT_U.material_id, material_name";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Attribute of the Stored Material to be found
     */
    private final UUID id;
    private final String name;

    /**
     * Create an object to search for a Stored Material given its UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchStoredMaterialDatabase(Connection con, UUID id) {
        this.con = con;
        this.id = id;
        this.name = null;
    }

    /**
     * Create an object to search for all Stored Material given the name.
     * @param con the connection to the database.
     * @param name the material name of material we want to find.
     */
    public SearchStoredMaterialDatabase(Connection con, String name) {
        this.con = con;
        this.name = name;
        this.id = null;
    }

    /**
     * Performs the search in the database for the Stored Material given the UUID.
     * @return An List of Lists representing the stored material with that id
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchStoredMaterialById() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet res = null;

        List<List<String>> materials = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(STATEMENT_ID);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            //interested in the only result
            if (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("material_id"));
                tmp.add(res.getString("material_name"));
                tmp.add(Integer.toString(res.getInt("quantity")));
                materials.add(tmp);
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
        return materials;
    }

    /**
     * Performs the search in the database for Stored Material given the name of the Material.
     * @return A List of Lists representing the stored materials with that name.
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchStoredMaterialByName() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        final List<List<String>> materials = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT_NAME);
            pstmt.setString(1, name);

            res = pstmt.executeQuery();

            //interested in the only result
            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("material_id"));
                tmp.add(res.getString("material_name"));
                tmp.add(Integer.toString(res.getInt("quantity")));
                materials.add(tmp);
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
        return materials;
    }
}
