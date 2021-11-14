package it.unipd.dei.webapp.database.accountant;

import it.unipd.dei.webapp.resource.FixedCost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to access the database and search a specific fixed cost entity or a specific set of them
 */
public final class SearchFixedCostDatabase {

    /**
     * SQL statement to search all the fixed cost within a specific date interval
     */
    private static final String STATEMENT = "SELECT * " +
            "FROM Factory.Fixed_cost AS FC " +
            "WHERE (FC.fixed_cost_date >= ?) AND (FC.fixed_cost_date <= ?) " +
            "ORDER BY FC.fixed_cost_date ASC";
    /**
     * SQL statement to search a specific fixed cost given its date and type
     */
    private static final String STATEMENT_2  = "SELECT * " +
            "FROM Factory.Fixed_cost AS FC " +
            "WHERE (FC.fixed_cost_date = ?) AND (FC.fixed_cost_type = ?::Factory.FIXED_COST_TYPE)";
    /**
     * SQL statement to search all the fixed cost that are not already linked to a report
     */
    private static final String STATEMENT_3 = "SELECT * " +
            "FROM Factory.Fixed_cost AS FC " +
            "WHERE (FC.report_date IS NULL)";
    /**
     * SQL statement to search all the fixed cost
     */
    private static final String STATEMENT_4 = "SELECT * " +
            "FROM Factory.Fixed_cost AS FC " +
            "ORDER BY FC.fixed_cost_date ASC";

    /**
     * Connection to the database
     */
    private final Connection con;
    /**
     * type of the fixed cost to search for
     */
    private final String type;
    /**
     * date of the fixed cost to search for
     */
    private final Date date;
    /**
     * Starting date of the interested period of time
     */
    private final Date startDate;
    /**
     * End date of the interested period of time
     */
    private final Date endDate;

    /**
     * Create a new object to search all fixed costs within startDate and endDate
     *
     * @param con Connection to the database
     * @param startDate Starting date of the interested period of time
     * @param endDate End date of the interested period of time
     */
    public SearchFixedCostDatabase(final Connection con, final Date startDate, final Date endDate) {
        this.con = con;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = null;
        this.date = null;
    }

    /**
     * Create a new object to search the specific fixed cost
     *
     * @param con Connection to the database
     * @param type type of the fixed cost to search for
     * @param date date of the fixed cost to search for
     */
    public SearchFixedCostDatabase(final Connection con, final String type, final Date date) {
        this.con = con;
        this.type = type;
        this.date = date;
        this.startDate = null;
        this.endDate = null;
    }

    /**
     * Create a new object to search all fixed costs
     *
     * @param con Connection to the database
     */
    public SearchFixedCostDatabase(final Connection con) {
        this.con = con;
        this.startDate = null;
        this.endDate = null;
        this.type = null;
        this.date = null;
    }

    /**
     * Return the set of fixed costs within the period startDate - endDate
     *
     * @return A List of type FixedCost containing in each element a row of the corresponding database table
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<FixedCost> viewFixedCost() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<FixedCost> fixedCosts = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                fixedCosts.add(new FixedCost(
                        rs.getDate("fixed_cost_date"),
                        rs.getFloat("price"),
                        rs.getString("fixed_cost_type"),
                        rs.getDate("report_date")
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

        return fixedCosts;
    }

    /**
     * Return all the fixed costs saved in the database at the moment
     *
     * @return A List of type FixedCost containing in each element a row of the corresponding database table
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<FixedCost> allFixedCost() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<FixedCost> fixedCosts = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT_4);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                fixedCosts.add(new FixedCost(
                        rs.getDate("fixed_cost_date"),
                        rs.getFloat("price"),
                        rs.getString("fixed_cost_type"),
                        rs.getDate("report_date")
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

        return fixedCosts;
    }

    /**
     * Return the fixed costs entity that corresponds to the date and type given
     *
     * @return A FixedCost element containing the searched FixedCost entity
     * @throws SQLException if any error occurs while inserting the item
     */
    public FixedCost searchFixedCost() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        FixedCost fixedCost = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_2);
            pstmt.setDate(1, date);
            pstmt.setString(2, type);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                fixedCost = new FixedCost(
                        rs.getDate("fixed_cost_date"),
                        rs.getFloat("price"),
                        rs.getString("fixed_cost_type"),
                        rs.getDate("report_date")
                );
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

        return fixedCost;
    }

    /**
     * Return the set of fixed costs that are not already linked to a report
     *
     * @return A List of type FixedCost containing in each element a row of the corresponding database table
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<FixedCost> viewNoReportFixedCost() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<FixedCost> fixedCosts = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT_3);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                fixedCosts.add(new FixedCost(
                        rs.getDate("fixed_cost_date"),
                        rs.getFloat("price"),
                        rs.getString("fixed_cost_type"),
                        rs.getDate("report_date")
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

        return fixedCosts;
    }
}