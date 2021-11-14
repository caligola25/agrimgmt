package it.unipd.dei.webapp.database.prod_planner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.unipd.dei.webapp.resource.ProductionPhase;

/**
 * Search for {@link ProductionPhase} stored in the database.
 */
public final class SearchProdPhaseDatabase {
    /**
     * The SQL statement to be executed to retrieve a list of production phases given an item UUID.
     */
    private static final String STATEMENT = "SELECT * FROM Factory.production_phase WHERE item_id = ?";

    /**
     * The SQL statement to be executed to retrieve a list of in production production phases given.
     */
    private static final String STATEMENT_INPROD = "SELECT phase_id, P.process_id, process_name, E.employee_id, CONCAT(employee_surname, ' ', employee_name) AS full_name, " +
            "phase_status, estimated_time, actual_time FROM Factory.production_phase AS PP INNER JOIN Factory.process AS P ON P.process_id = PP.process_id " +
            "INNER JOIN Factory.employee AS E ON PP.employee_id = E.employee_id WHERE item_id = ? ORDER BY P.sequence_number ASC";

    /**
     * The SQL statement to be executed to retrieve a list of production phases given an employee UUID.
     */
    private static final String STATEMENT_EMPLOYEE = "SELECT phase_id, P.process_id, process_name, I.item_id, PR.product_name, " +
            "PO.product_order_id, phase_status, estimated_time, actual_time FROM Factory.production_phase AS PP " +
            "INNER JOIN Factory.process AS P ON P.process_id = PP.process_id INNER JOIN Factory.product AS PR ON PR.product_id = P.product_id " +
            "INNER JOIN Factory.item AS I ON PP.item_id = I.item_id INNER JOIN Factory.product_order AS PO ON PO.product_order_id = I.product_order_id " +
            "WHERE employee_id = ? ORDER BY PP.phase_status ASC, PO.product_order_date DESC, I.item_id ASC, P.sequence_number ASC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Attribute of the Production Phase or of the Employee.
     */
    private final UUID id;

    /**
     * Create an object to search for a list of production phases given the Item UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchProdPhaseDatabase(final Connection con, final UUID id){
        this.con = con;
        this.id = id;
    }

    /**
     * Performs the search in the database for a list of production phase given the Item UUID.
     * @return A list of production phase that are related to the given Item.
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<ProductionPhase> searchProdPhaseByItemId() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final List<ProductionPhase> prodPhases = new ArrayList<ProductionPhase>();

        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            while (res.next()) {
                UUID prodPhaseId = UUID.fromString(res.getString("phase_id"));
                UUID itemId = UUID.fromString(res.getString("item_id"));
                UUID processId = UUID.fromString(res.getString("process_id"));
                UUID employeeId = UUID.fromString(res.getString("employee_id"));
                String status = res.getString("phase_status");
                String actualTime = res.getString("actual_time");

                prodPhases.add(new ProductionPhase(prodPhaseId, itemId, processId, employeeId, status, actualTime));
            }

        }finally {
            if (res != null){
                res.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return prodPhases;
    }

    /**
     * Performs the search in the database for a list of production phase given the Item UUID with process name, employee name and time data.
     * @return A list of production phase that are related to the given Item.
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchProdPhaseDataByItemId() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final List<List<String>> prodPhases = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT_INPROD);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("phase_id"));
                tmp.add(res.getString("process_id"));
                tmp.add(res.getString("process_name"));
                tmp.add(res.getString("employee_id"));
                tmp.add(res.getString("full_name"));
                tmp.add(res.getString("phase_status"));
                tmp.add(res.getString("estimated_time"));
                tmp.add(res.getString("actual_time"));
                prodPhases.add(tmp);
            }

        }finally {
            if (res != null){
                res.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return prodPhases;
    }

    /**
     * Performs the search in the database for a list of production phase given the Employee UUID.
     * @return A list of production phase that are related to the given Employee.
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<List<String>> searchProdPhaseByEmployeeId() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final List<List<String>> prodPhases = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT_EMPLOYEE);
            pstmt.setObject(1, id);

            res = pstmt.executeQuery();

            while (res.next()) {
                List<String> tmp = new ArrayList<>();
                tmp.add(res.getString("phase_id"));
                tmp.add(res.getString("process_id"));
                tmp.add(res.getString("process_name"));
                tmp.add(res.getString("item_id"));
                tmp.add(res.getString("product_name"));
                tmp.add(res.getString("product_order_id"));
                tmp.add(res.getString("phase_status"));
                tmp.add(res.getString("estimated_time"));
                tmp.add(res.getString("actual_time"));
                prodPhases.add(tmp);
            }

        }finally {
            if (res != null){
                res.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return prodPhases;
    }
}
