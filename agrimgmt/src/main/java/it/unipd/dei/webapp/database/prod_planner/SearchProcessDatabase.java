package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Search for {@link Process} stored in the database given a specific Product UUID.
 */
public final class SearchProcessDatabase {

    /**
     * The SQL statement to be executed.
     */
    private static final String STATEMENT = "SELECT * FROM Factory.process WHERE product_id = ? ORDER BY sequence_number ASC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Attribute of the Process to be found
     */
    private final UUID id;

    /**
     * Create an object to search for a Process given the Product UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchProcessDatabase(final Connection con, final UUID id){
        this.con = con;
        this.id = id;
    }

    /**
     * Performs the search in the database for the Process given the Product UUID.
     * @return The process with that product id
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<Process> searchProcessByProductId() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final List<Process> processes = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            while (res.next())
                processes.add(new Process((UUID) res.getObject("process_id"), res.getString("process_name"),
                        res.getInt("sequence_number"), res.getString("estimated_time"), (UUID) res.getObject("product_id"),
                        (UUID) res.getObject("material_id"), res.getInt("quantity")));

        }finally {
            if (res != null){
                res.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return processes;
    }
}
