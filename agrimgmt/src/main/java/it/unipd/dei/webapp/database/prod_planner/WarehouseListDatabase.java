package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Item;
import it.unipd.dei.webapp.resource.RawMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Search for a list of {@link RawMaterial} or of {@link Item} stored in the warehouse from tha database.
 */
public final class WarehouseListDatabase {

    /**
     * The SQL statement to be executed to retrieve a list of stored raw materials with their quantity.
     */
    private static final String STATEMENT_MATERIAL = "SELECT MAT_B.material_id, material_name, COALESCE(SUM(mat_bought), 0) - COALESCE(SUM(mat_used), 0) AS quantity " +
                                                        "FROM (SELECT material_id, SUM(quantity) AS mat_used " +
                                                            "FROM Factory.Production_phase AS PP " +
                                                                "INNER JOIN Factory.Process AS PR " +
                                                                "ON PP.process_id = PR.process_id " +
                                                                "GROUP BY material_id " +
                                                        ") AS MAT_U " +
                                                        "RIGHT OUTER JOIN ( " +
                                                            "SELECT RM.material_id, RM.material_name, SUM(S.quantity) AS mat_bought " +
                                                            "FROM Factory.Supplying AS S " +
                                                            "RIGHT OUTER JOIN Factory.Raw_Material AS RM " +
                                                            "ON S.material_id = RM.material_id " +
                                                            "GROUP BY RM.material_id " +
                                                        ") AS MAT_B " +
                                                        "ON MAT_U.material_id = MAT_B.material_id " +
                                                        "GROUP BY MAT_B.material_id, material_name " +
                                                        "ORDER BY material_name ASC";

    /**
     * The SQL statement to be executed to retrieve a list of stored items with their quantity.
     */
    private static final String STATEMENT_ITEM = "SELECT item_id, product_id, product_name, PO.price FROM Factory.item AS I " +
            "INNER JOIN Factory.product AS P ON I.product_id = P.product_id INNER JOIN Factory.product_order AS PO ON I.product_order_id = PO.product_order_id " +
            "WHERE I.item_status = 'stored' AND (PO.order_status = 'not_paid' OR PO.order_status = 'cancelled') " +
            "GROUP BY I.item_id, product_name, PO.price ORDER BY product_name ASC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Create an object to search for all stored materials and items.
     * @param con the connection to the database.
     */
    public WarehouseListDatabase(Connection con) {
        this.con = con;
    }

    /**
     * Performs the search in the database for stored materials.
     * @return A List of Lists containing all stored materials.
     * @throws SQLException If there is any problem in the SQL execution.
     */
    public List<List<String>> warehouseMaterialList() throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet res = null;
        final List<List<String>> materials = new ArrayList<>();

        try {

            pstmt = con.prepareStatement(STATEMENT_MATERIAL);
            res = pstmt.executeQuery();

            //interested in the only result
            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("material_id"));
                tmp.add(res.getString("material_name"));
                tmp.add(res.getString("quantity"));
                materials.add(tmp);
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
        return materials;
    }

    /**
     * Performs the search in the database for stored items not sold.
     * @return A List of Lists containing all stored items not sold.
     * @throws SQLException If there is any problem in the SQL execution.
     */
    public List<List<String>> warehouseItemList() throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet res = null;
        final List<List<String>> items = new ArrayList<>();

        try {

            pstmt = con.prepareStatement(STATEMENT_ITEM);
            res = pstmt.executeQuery();

            //interested in the only result
            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("item_id"));
                tmp.add(res.getString("product_id"));
                tmp.add(res.getString("product_name"));
                tmp.add(Integer.toString(res.getInt("price")));
                items.add(tmp);
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
        return items;
    }
}
