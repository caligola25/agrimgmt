package it.unipd.dei.webapp.database.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that retrieve all the allowed roles from the database
 */
public final class RetrieveRolesDatabase {

    /**
     * SQL statement to retrieve all the allowed roles from the database
     */
    private static final String STATEMENT = "SELECT unnest(enum_range(NULL::Factory.ROLE)) AS roles";

    /**
     * Connection to the database
     */
    private final Connection con;

    /**
     * Create a new object to retrieve all the allowed roles from the database
     *
     * @param con Connection to the database
     */
    public RetrieveRolesDatabase(final Connection con) {
        this.con = con;
    }

    /**
     * Method that retrieve all the allowed roles from the database
     *
     * @return a List of String in which each string represent a different allowed role
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<String> retrieveRoles() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<String> roles = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                roles.add(rs.getString("roles"));
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

        return roles;
    }
}
