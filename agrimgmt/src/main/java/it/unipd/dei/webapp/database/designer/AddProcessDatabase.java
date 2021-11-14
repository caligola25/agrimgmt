package it.unipd.dei.webapp.database.designer;

import it.unipd.dei.webapp.resource.Process;
import java.sql.*;

/**
 * Add a new Process into the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class AddProcessDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Factory.process (process_id, process_name, sequence_number, estimated_time, product_id, material_id, quantity) VALUES (?, ?, ?, ?::interval, ?, ?, ?)";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The process to be added into the database
     */
    private final Process process;

    /**
     * Creates a new object for adding a process into the database.
     *
     * @param con     the connection to the database.
     * @param process the process to be added into the database.
     */
    public AddProcessDatabase(final Connection con, final Process process) {
        this.con = con;
        this.process = process;
    }

    /**
     * Adds a new process into the database
     *
     * @throws SQLException if any error occurs while adding the process.
     */
    public void addProcess() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, process.getProcessId());
            pstmt.setString(2, process.getName());
            pstmt.setInt(3, process.getSequenceNumber());
            //pstmt.setObject(4, process.getEstimatedTime());
            pstmt.setString(4, process.getEstimatedTime());
            pstmt.setObject(5, process.getProductId());
            pstmt.setObject(6, process.getMaterialId());
            pstmt.setInt(7, process.getQuantity());

            pstmt.execute();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

    }

}
