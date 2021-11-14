package it.unipd.dei.webapp.database.designer;

import it.unipd.dei.webapp.resource.Process;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


/**
 * Class to access the database and change the estimated time of a process
 */
public final class ChangeProcessEstimatedTimeDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE Factory.process SET estimated_time = ?::interval WHERE process_id = ? RETURNING *";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The product id of the product to be modified into the database
     */
    private final UUID processId;

    /**
     * The estimated time of the process to be changed into the database
     */
    private final String estimatedTime;


    /**
     * Creates a new object for adding a product into the database.
     *
     * @param con     the connection to the database.
     * @param processId the id of the process that will be modified into the database
     * @param estimatedTime  the time changed of the process into the database.
     */
    public ChangeProcessEstimatedTimeDatabase(final Connection con, final UUID processId, final String estimatedTime) {
        this.con = con;
        this.processId = processId;
        this.estimatedTime = estimatedTime;
    }

    /**
     * Change status of a product into the database
     *
     * @return a Process resource with the estimated time value updated
     * @throws SQLException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     */
    public Process changeProcessEstimatedTime() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Process process = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, estimatedTime);
            pstmt.setObject(2, processId);

            rs = pstmt.executeQuery();

            while(rs.next()){
                process = new Process(UUID.fromString(rs.getString("process_id")),
                        rs.getString("process_name"), rs.getInt("sequence_number"),
                        rs.getString("estimated_time"),
                        UUID.fromString(rs.getString("product_id")),
                        UUID.fromString(rs.getString("material_id")), rs.getInt("quantity"));
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
        return process;

    }

}