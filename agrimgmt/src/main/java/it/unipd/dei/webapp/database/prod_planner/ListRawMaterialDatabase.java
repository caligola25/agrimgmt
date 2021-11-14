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
 * Retrieve the list of all  {@link RawMaterial} stored in the database.
 */
public final class ListRawMaterialDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT material_id, material_name FROM Factory.raw_material ORDER BY material_name ASC";

    /**
     * Connection to the database
     */
    private final Connection con;


    /**
     * Creates a new object for listing all raw materials into the database.
     *
     * @param con
     *            the connection to the database.
     */

    public ListRawMaterialDatabase(final Connection con){
        this.con = con;
    }

    /**
     * Lists all the Raw Materials in the database
     *
     * @return a List of RawMaterials containing all the raw materials entity present in the database
     * @throws SQLException
     *             if any error occurs while adding the supplying.
     */
    public List<RawMaterial> listRawMaterial() throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<RawMaterial> listRawMat = new ArrayList<RawMaterial>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                //create object
                RawMaterial mat = new RawMaterial(UUID.fromString(rs.getString("material_id")), rs.getString("material_name"));
                listRawMat.add(mat);
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return listRawMat;
    }
}
