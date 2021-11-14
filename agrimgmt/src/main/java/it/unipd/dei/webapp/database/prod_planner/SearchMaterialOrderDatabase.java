package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.MaterialOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Search for {@link MaterialOrder} stored in the database.
 */
public final class SearchMaterialOrderDatabase {

    /**
     * The SQL statementS to be executed to retrieve a list of material orders given their UUID.
     */
    private static final String STATEMENT_ID = "SELECT M.material_order_id, SP.supplier_name, order_status, material_order_date, price, report_date " +
            "FROM (Factory.material_order AS M INNER JOIN Factory.supplying AS S ON M.material_order_id = S.material_order_id " +
            "INNER JOIN Factory.supplier AS SP ON S.supplier_name = SP.supplier_name) WHERE M.material_order_id = ? " +
            "GROUP BY M.material_order_id, SP.supplier_name ORDER BY material_order_date DESC";

    /**
     * The SQL statementS to be executed to retrieve a list of material orders given their Supplier name.
     */
    private static final String STATEMENT_NAME = "SELECT M.material_order_id, SP.supplier_name, order_status, material_order_date, price, report_date " +
            "FROM (Factory.material_order AS M INNER JOIN Factory.supplying AS S ON M.material_order_id = S.material_order_id " +
            "INNER JOIN Factory.supplier AS SP ON S.supplier_name = SP.supplier_name) WHERE SP.supplier_name = ? " +
            "GROUP BY M.material_order_id, SP.supplier_name ORDER BY material_order_date DESC";

    /**
     * The SQL statementS to be executed to retrieve a list of material orders given their status.
     */
    private static final String STATEMENT_STATUS = "SELECT M.material_order_id, SP.supplier_name, order_status, material_order_date, price, report_date " +
            "FROM (Factory.material_order AS M INNER JOIN Factory.supplying AS S ON M.material_order_id = S.material_order_id " +
            "INNER JOIN Factory.supplier AS SP ON S.supplier_name = SP.supplier_name) WHERE order_status = ?::factory.material_order_status " +
            "GROUP BY M.material_order_id, SP.supplier_name ORDER BY material_order_date DESC";

    /**
     * The SQL statementS to be executed to retrieve a list of material orders given their date.
     */
    private static final String STATEMENT_DATE = "SELECT M.material_order_id, SP.supplier_name, order_status, material_order_date, price, report_date " +
            "FROM (Factory.material_order AS M INNER JOIN Factory.supplying AS S ON M.material_order_id = S.material_order_id " +
            "INNER JOIN Factory.supplier AS SP ON S.supplier_name = SP.supplier_name) WHERE material_order_date = ? " +
            "GROUP BY M.material_order_id, SP.supplier_name ORDER BY material_order_date DESC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Attribute of the Material Order to be found
     */
    private final UUID id;
    private final String nameOrStatus;
    private final Date date;

    /**
     * Create an object to search for a Material Order given its UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchMaterialOrderDatabase(Connection con, UUID id) {
        this.con = con;
        this.id = id;
        this.nameOrStatus = null;
        this.date = null;
    }

    /**
     * Create an object to search for all Material Orders given the Supplier name or the Status.
     * @param con the connection to the database.
     * @param nameOrStatus the supplier name or the status of orders we want to find.
     */
    public SearchMaterialOrderDatabase(Connection con, String nameOrStatus) {
        this.con = con;
        this.nameOrStatus = nameOrStatus;
        this.id = null;
        this.date = null;
    }

    /**
     * Create an object to search for all Material Orders given the Date.
     * @param con the connection to the database.
     * @param date the date of orders we want to find.
     */
    public SearchMaterialOrderDatabase(Connection con, Date date) {
        this.con = con;
        this.date = date;
        this.id = null;
        this.nameOrStatus = null;
    }

    /**
     * Performs the search in the database for the Material Order given the UUID.
     * @return An List of Lists representing the material orders with that id
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchMaterialOrderById() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet res = null;

        List<List<String>> orders = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(STATEMENT_ID);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            //interested in the only result
            if (res.next()) {
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
                orders.add(tmp);
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
        return orders;
    }

    /**
     * Performs the search in the database for Material Orders given the name of the Supplier.
     * @return A List of Lists representing the material orders made to the Supplier
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchMaterialOrderBySupplierName() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        List<List<String>> orders = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(STATEMENT_NAME);
            pstmt.setObject(1, nameOrStatus);

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
                orders.add(tmp);
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
        return orders;
    }

    /**
     * Performs the search in the database for Material Orders given the status.
     * @return An List of Lists representing the material orders with that status
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchMaterialOrderByStatus() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        List<List<String>> orders = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(STATEMENT_STATUS);
            pstmt.setObject(1, nameOrStatus);

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
                orders.add(tmp);
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
        return orders;
    }

    /**
     * Performs the search in the database for Material Orders given the date.
     * @return A List of Lists representing the material orders with that date
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchMaterialOrderByDate() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        List<List<String>> orders = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(STATEMENT_DATE);
            pstmt.setObject(1, date);

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
                orders.add(tmp);
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
        return orders;
    }
}
