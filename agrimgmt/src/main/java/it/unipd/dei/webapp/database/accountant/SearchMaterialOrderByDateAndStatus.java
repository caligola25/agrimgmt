package it.unipd.dei.webapp.database.accountant;

import it.unipd.dei.webapp.resource.MaterialOrder_SupplierName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to access the database and search all the material order with a specific status into a date interval
 */
public final class SearchMaterialOrderByDateAndStatus {
    /**
     * SQL statement to search all the material order with a specific status inside a date interval
     * column value: supplier_name, material_order_date, price
     */
    private static final String STATEMENT = "SELECT DISTINCT SU.supplier_name, MO.material_order_date, MO.price " +
            "FROM (Factory.Material_order AS MO INNER JOIN Factory.Supplying AS SU ON MO.material_order_id = SU.material_order_id) " +
            "WHERE (MO.material_order_date >= ?) AND (MO.material_order_date <= ?) AND (MO.order_status = ?::factory.material_order_status) " +
            "ORDER BY MO.material_order_date ASC";

    /**
     * Connection to the database
     */
    private final Connection con;
    /**
     * Starting date of the interested period of time
     */
    private final Date startDate;
    /**
     * End date of the interested period of time
     */
    private final Date endDate;
    /**
     * Interested state
     */
    private final String status;

    /**
     * Create a new object to search the interested material order
     *
     * @param con Connection to the database
     * @param startDate Starting date of the interested period of time
     * @param endDate End date of the interested period of time
     * @param status Interested state
     */
    public SearchMaterialOrderByDateAndStatus(final Connection con, final Date startDate, final Date endDate, final String status) {
        this.con = con;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    /**
     * Return all the material order with state = status within the period startDate - endDate
     * with the corresponding supplier name
     *
     * @return A List of type MaterialOrder_SupplierName containing in each element a row of the corresponding database table
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<MaterialOrder_SupplierName> searchMaterialOrder() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<MaterialOrder_SupplierName> matOrders = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
            pstmt.setString(3, status);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                matOrders.add(new MaterialOrder_SupplierName(
                        rs.getString("supplier_name"),
                        rs.getDate("material_order_date"),
                        rs.getFloat("price")
                ));
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

        return matOrders;
    }
}
