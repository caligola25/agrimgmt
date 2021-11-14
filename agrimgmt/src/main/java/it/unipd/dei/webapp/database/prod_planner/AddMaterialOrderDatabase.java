package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.MaterialOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add a new {@link MaterialOrder} into the database.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class AddMaterialOrderDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Factory.material_order (material_order_id, price, material_order_date, order_status, report_date ) " +
            "VALUES (?, ?, ?, ?::Factory.MATERIAL_ORDER_STATUS, ?)";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The material order to be added into the database
     */
    private final MaterialOrder order;

    /**
     * Creates a new object for adding a material order into the database.
     *
     * @param con     the connection to the database.
     * @param order the material order to be added into the database.
     */
    public AddMaterialOrderDatabase(final Connection con, final MaterialOrder order) {
        this.con = con;
        this.order = order;
    }

    /**
     * Adds a new material order into the database
     *
     * @throws SQLException if any error occurs while adding the material order.
     */
    public void addMaterialOrder() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, order.getMaterialOrderId());
            pstmt.setFloat(2, order.getPrice());
            pstmt.setDate(3, order.getMaterialOrderDate());
            pstmt.setObject(4, order.getOrderStatus());
            pstmt.setDate(5, order.getReportDate());

            pstmt.execute();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

    }

}