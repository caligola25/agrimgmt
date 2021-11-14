package it.unipd.dei.webapp.database.designer;

import it.unipd.dei.webapp.resource.Process_ProductName_MaterialName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * View the list of processes (with product name and material name in place of product id and material id) from the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class ProcessListDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT PS.process_id, PS.process_name, PS.sequence_number, PS.estimated_time, PT.product_name, RM.material_name, PS.quantity " +
                                            "FROM Factory.process AS PS INNER JOIN Factory.product AS PT ON PS.product_id = PT.product_id INNER JOIN Factory.raw_material as RM ON PS.material_id = RM.material_id "+
                                            "ORDER BY PT.product_name, PS.sequence_number ASC";


    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * Creates a new object for viewing the list of processes (with product name and material name in place of product id and material id) from the database.
     *
     * @param con     the connection to the database.
     */
    public ProcessListDatabase(final Connection con){
        this.con = con;
    }

    /**
     * Creates a list of the processes (with product name and material name in place of product id and material id) present into the database
     *
     * @return a list of {@code Process_ProductName_MaterialName} object.
     *
     * @throws SQLException if any error occurs while retrieving the processes.
     */
    public List<Process_ProductName_MaterialName> processList() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Process_ProductName_MaterialName> processes = new ArrayList<Process_ProductName_MaterialName>();

        try{
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while(rs.next()){
                processes.add(new Process_ProductName_MaterialName(UUID.fromString(rs.getString("process_id")),
                        rs.getString("process_name"),
                        rs.getInt("sequence_number"),
                        rs.getString("estimated_time"),
                        rs.getString("product_name"),
                        rs.getString("material_name"),
                        rs.getInt("quantity")));
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
        return processes;
    }
}
