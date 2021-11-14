package it.unipd.dei.webapp.database.accountant;

import it.unipd.dei.webapp.resource.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to access the database and perform various operation on the report entity
 */
public final class ReportManagementDatabase {

    /**
     * SQL statement to insert a new report in the database
     */
    private static final String STATEMENT = "INSERT INTO Factory.Report VALUES(?, ?)";
    /**
     * SQL statement to retrieve all the reports entity
     */
    private static final String STATEMENT_2 = "SELECT * FROM Factory.Report";
    /**
     * SQL statement to retrieve all the report entity with date within a specified interval
     */
    private static final String STATEMENT_3 = "SELECT * " +
            "FROM Factory.Report AS R " +
            "WHERE (R.report_date >= ?) AND (R.report_date <= ?)";
    /**
     * SQL statement to retrieve all the information about a specific report entity identified using its date
     */
    private static final String STATEMENT_4 = "SELECT * " +
            "FROM Factory.Report AS R " +
            "WHERE (R.report_date = ?)";
    /**
     * SQL statement to retrieve all the report date of the reports saved in the database
     */
    private static final String STATEMENT_DATE = "SELECT report_date FROM Factory.Report";

    /**
     * Connection to the database
     */
    private final Connection con;
    /**
     * Report it.unipd.dei.webapp.resource that need to be inserted in the database
     */
    private final Report report;
    /**
     * Year value used to retrieve information about all the reports produced in this year
     */
    private final int year;
    /**
     * Date of a specific report
     */
    private final Date date;

    /**
     * Create a new object to retrieve information about all the reports produced in this year
     *
     * @param con Connection to the database
     * @param year Year value used to retrieve information about all the reports produced in this year
     */
    public ReportManagementDatabase(final Connection con, final int year) {
        this.con = con;
        this.report = null;
        this.year = year;
        this.date = null;
    }

    /**
     * Create a new object to retrieve information about a reports with a specific date
     *
     * @param con Connection to the database
     * @param date Date of a specific report to search for
     */
    public ReportManagementDatabase(final Connection con, final Date date) {
        this.con = con;
        this.report = null;
        this.year = 0;
        this.date = date;
    }

    /**
     * Create a new object to retrieve general information on the reports in the database
     *
     * @param con Connection to the database
     */
    public ReportManagementDatabase(final Connection con) {
        this.con = con;
        this.report = null;
        this.year = 0;
        this.date = null;
    }

    /**
     * Create a new object to add a new report in the database
     *
     * @param con Connection to the database
     * @param report Report it.unipd.dei.webapp.resource that need to be inserted in the database
     */
    public ReportManagementDatabase(final Connection con, final Report report) {
        this.con = con;
        this.report = report;
        this.year = 0;
        this.date = null;
    }

    /**
     * Returns all the report entities saved in the database
     *
     * @return A List of type Report containing in each element one of the report saved in the database
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<Report> viewReport() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Report> reports = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT_2);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                reports.add(new Report(
                        rs.getDate("report_date"),
                        rs.getBytes("document_file")
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

        return reports;
    }

    /**
     * Returns the dates of all the reports saved in the database
     *
     * @return A List of type Date containing in each element one of the report's date
     * @throws SQLException  if any error occurs while inserting the item
     */
    public List<Date> viewDates() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Date> dates = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT_DATE);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                dates.add(rs.getDate("report_date"));
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

        return dates;
    }

    /**
     * Returns all the reports produced in the requested year
     *
     * @return A List of type Report containing in each element one of the report produced in that year
     * @throws SQLException  if any error occurs while inserting the item
     */
    public List<Report> searchReportByYear() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Date startDate = Date.valueOf(year + "-01-01");
        Date endDate = Date.valueOf(year + "-12-31");

        final List<Report> reports = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT_3);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                reports.add(new Report(
                        rs.getDate("report_date"),
                        rs.getBytes("document_file")
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

        return reports;
    }

    /**
     * Returns the requested report entity identified by its date
     *
     * @return A it.unipd.dei.webapp.resource of type Report
     * @throws SQLException  if any error occurs while inserting the item
     */
    public Report searchReportByDate() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Report report = null;

        try{
            pstmt = con.prepareStatement(STATEMENT_4);
            pstmt.setDate(1, date);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                report = new Report(
                        rs.getDate("report_date"),
                        rs.getBytes("document_file")
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

        return report;
    }

    /**
     * Inserts in the database the given report entity
     *
     * @throws SQLException  if any error occurs while inserting the item
     */
    public void addReport() throws SQLException {
        PreparedStatement pstmt = null;

        try{
            pstmt = con.prepareStatement(STATEMENT);

            pstmt.setDate(1, report.getDate());
            pstmt.setBytes(2, report.getDocumentFile());

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
