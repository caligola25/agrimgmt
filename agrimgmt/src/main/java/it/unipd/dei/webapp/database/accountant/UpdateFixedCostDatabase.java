package it.unipd.dei.webapp.database.accountant;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class to access the database and update a fixed cost entity
 */
public final class UpdateFixedCostDatabase {
    /**
     * SQL statement to update the selected fixed cost date
     */
    private static final String STATEMENT_date = "UPDATE Factory.Fixed_cost " +
            "SET fixed_cost_date = ? " +
            "WHERE (fixed_cost_date = ?) AND (fixed_cost_type = ?::Factory.FIXED_COST_TYPE)";
    /**
     * SQL statement to update the selected fixed cost type
     */
    private static final String STATEMENT_type = "UPDATE Factory.Fixed_cost " +
            "SET fixed_cost_type = ?::Factory.FIXED_COST_TYPE " +
            "WHERE (fixed_cost_date = ?) AND (fixed_cost_type = ?::Factory.FIXED_COST_TYPE)";
    /**
     * SQL statement to update the selected fixed cost price
     */
    private static final String STATEMENT_price = "UPDATE Factory.Fixed_cost " +
            "SET price = ? " +
            "WHERE (fixed_cost_date = ?) AND (fixed_cost_type = ?::Factory.FIXED_COST_TYPE)";
    /**
     * SQL statement to update the selected fixed cost report_date
     */
    private static final String STATEMENT_reportDate = "UPDATE Factory.Fixed_cost " +
            "SET report_date = ? " +
            "WHERE (fixed_cost_date = ?) AND (fixed_cost_type = ?::Factory.FIXED_COST_TYPE)";
    /**
     * Connection to the database
     */
    private final Connection con;
    /**
     * Original fixed cost type used to select the correct fixed cost entity (in conjunction with date)
     */
    private final String type;
    /**
     * New type used to update the fixed cost entity
     */
    private final String newType;
    /**
     * New price value used to update the fixed cost entity
     */
    private final double price;
    /**
     * Original fixed cost date used to select the correct fixed cost entity (in conjunction with type)
     */
    private final Date date;
    /**
     * New date used to update the fixed cost entity
     */
    private final Date newDate;
    /**
     * New report_date used to update the fixed cost entity
     */
    private final Date report_date;

    /**
     * Create a new object to update a fixed cost into the database
     *
     * @param con Connection to the database
     * @param newType New type used to update the fixed cost entity
     * @param price New price value used to update the fixed cost entity
     * @param newDate New date used to update the fixed cost entity
     * @param report_date New report_date used to update the fixed cost entity
     * @param type Original fixed cost type used to select the correct fixed cost entity (in conjunction with date)
     * @param date Original fixed cost date used to select the correct fixed cost entity (in conjunction with type)
     */
    public UpdateFixedCostDatabase(final Connection con, final String newType, final double price, final Date newDate, final Date report_date, final String type, final Date date) {
        this.con = con;
        this.type = type;
        this.newType = newType;
        this.price = price;
        this.date = date;
        this.newDate = newDate;
        this.report_date = report_date;
    }

    /**
     * Update the date of the selected fixed cost entity
     *
     * @throws SQLException if any error occurs while inserting the item
     */
    public void updateFixedCostDate() throws SQLException {
        PreparedStatement pstmt = null;

        try{
            pstmt = con.prepareStatement(STATEMENT_date);
            pstmt.setDate(1, newDate);
            pstmt.setDate(2, date);
            pstmt.setString(3, type);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }

    /**
     * Update the type of the selected fixed cost entity
     *
     * @throws SQLException if any error occurs while inserting the item
     */
    public void updateFixedCostType() throws SQLException {
        PreparedStatement pstmt = null;

        try{
            pstmt = con.prepareStatement(STATEMENT_type);
            pstmt.setString(1, newType);
            pstmt.setDate(2, date);
            pstmt.setString(3, type);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }

    /**
     * Update the price of the selected fixed cost entity
     *
     * @throws SQLException if any error occurs while inserting the item
     */
    public void updateFixedCostPrice() throws SQLException {
        PreparedStatement pstmt = null;

        try{
            pstmt = con.prepareStatement(STATEMENT_price);
            pstmt.setDouble(1, price);
            pstmt.setDate(2, date);
            pstmt.setString(3, type);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }

    /**
     * Update the report_date  of the selected fixed cost entity
     *
     * @throws SQLException if any error occurs while inserting the item
     */
    public void updateFixedCostReportDate() throws SQLException {
        PreparedStatement pstmt = null;

        try{
            pstmt = con.prepareStatement(STATEMENT_reportDate);
            pstmt.setDate(1, report_date);
            pstmt.setDate(2, date);
            pstmt.setString(3, type);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
