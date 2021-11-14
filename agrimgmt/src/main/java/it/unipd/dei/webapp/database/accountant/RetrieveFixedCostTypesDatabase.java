package it.unipd.dei.webapp.database.accountant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to access the database and retrieve all the possible fixed cost types
 */
public final class RetrieveFixedCostTypesDatabase {
    /**
     * SQL statement to retrieve all the possible fixed cost types defined inside the database
     */
    private static final String STATEMENT = "SELECT unnest(enum_range(NULL::Factory.FIXED_COST_TYPE)) AS fixedCost";
    /**
     * Connection to the database
     */
    private final Connection con;

    /**
     * Create a new object to retrieve all the possible fixed costs types
     *
     * @param con Connection to the database
     */
    public RetrieveFixedCostTypesDatabase(final Connection con) {
        this.con = con;
    }

    /**
     * Return all the possible fixed cost types
     *
     * @return A List of type String containing in each element a possible fixed costs type
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<String> retrieveTypes() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<String> types = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                types.add(rs.getString("fixedCost"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return types;
    }
}
