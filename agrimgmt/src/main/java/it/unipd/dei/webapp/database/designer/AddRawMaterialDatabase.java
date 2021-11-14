package it.unipd.dei.webapp.database.designer;

import it.unipd.dei.webapp.resource.RawMaterial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add a new Raw Material into the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class AddRawMaterialDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Factory.raw_material (material_id, material_name) VALUES (?, ?)";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The raw material to be added into the database
     */
    private final RawMaterial rawMaterial;

    /**
     * Creates a new object for adding a raw material into the database.
     *
     * @param con
     *            the connection to the database.
     * @param rawMaterial
     *            the raw material to be added into the database.
     */
    public AddRawMaterialDatabase(final Connection con, final RawMaterial rawMaterial) {
        this.con = con;
        this.rawMaterial = rawMaterial;
    }

    /**
     * Adds a new raw material into the database
     *
     * @throws SQLException
     *             if any error occurs while adding the raw material.
     */
    public void addRawMaterial() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, rawMaterial.getMaterialId());
            pstmt.setString(2, rawMaterial.getMaterialName());

            pstmt.execute();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

    }
}