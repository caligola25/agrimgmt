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
 * Search the processes (with product name and material name in place of product id and material id) to build a specific product in the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class SearchProcessByProductIdDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT PS.process_id, PS.process_name, PS.sequence_number, PS.estimated_time, PT.product_name, RM.material_name, PS.quantity " +
                                            "FROM Factory.process AS PS INNER JOIN Factory.product AS PT ON PS.product_id = PT.product_id INNER JOIN Factory.raw_material as RM ON PS.material_id = RM.material_id " +
                                            "WHERE PS.product_id = ?::uuid ORDER BY sequence_number ASC";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The product id of the product to be searched into the database
     */
    private final UUID productId;

    /**
     * Creates a new object for searching for processes (with product name and material name in place of product id and material id) to build a specific product in the database.
     *
     * @param con     the connection to the database.
     *
     * @param productId     the id of the product to be searched into the database.
     */
    public SearchProcessByProductIdDatabase(final Connection con, final UUID productId){
        this.con = con;
        this.productId = productId;
    }

    /**
     * Creates a list of the processes (with product name and material name in place of product id and material id) to build a specific product in the database
     *
     * @return a list of {@code Process_ProductName_MaterialName} object.
     *
     * @throws SQLException if any error occurs while retrieving the processes.
     */
    public List<Process_ProductName_MaterialName> searchProcessByProductId() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Process_ProductName_MaterialName> processes = new ArrayList<Process_ProductName_MaterialName>();

        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, productId.toString());

            rs = pstmt.executeQuery();

            while(rs.next()){
                processes.add(new Process_ProductName_MaterialName(UUID.fromString(rs.getString("process_id")),
                        rs.getString("process_name"), rs.getInt("sequence_number"),
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
