package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.MaterialOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Retrieve the list of all {@link MaterialOrder} stored in the database with the related Supplier name.
 */
public final class MaterialOrderListDatabase {

    /**
     * The SQL statement to be executed.
     */
    private static final String STATEMENT = "SELECT M.material_order_id, SP.supplier_name, order_status, material_order_date, price, report_date " +
            "FROM (Factory.material_order AS M INNER JOIN Factory.supplying AS S ON M.material_order_id = S.material_order_id " +
            "INNER JOIN Factory.supplier AS SP ON S.supplier_name = SP.supplier_name) " +
            "GROUP BY M.material_order_id, SP.supplier_name ORDER BY material_order_date DESC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Create an object to search for all Material Orders.
     * @param con the connection to the database.
     */
    public MaterialOrderListDatabase(Connection con){
        this.con = con;
    }

    /**
     * Performs the search in the database for Material Orders associated with the Supplier name.
     * @return A List of Lists representing the Material Orders.
     * @throws SQLException If there is any problem in the SQL execution.
     */
    public List<List<String>> materialOrderList() throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet res = null;
        final List<List<String>> materialOrders = new ArrayList<>();

        try {

            pstmt = con.prepareStatement(STATEMENT);
            res = pstmt.executeQuery();

            //interested in the only result
            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("material_order_id"));
                tmp.add(res.getString("supplier_name"));
                tmp.add(res.getString("order_status"));
                tmp.add(res.getDate("material_order_date").toString());
                tmp.add(Float.toString(res.getFloat("price")));
                if (res.getDate("report_date") == null)
                    tmp.add("");
                else
                    tmp.add(res.getDate("report_date").toString());
                materialOrders.add(tmp);
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
        return materialOrders;
    }

}
