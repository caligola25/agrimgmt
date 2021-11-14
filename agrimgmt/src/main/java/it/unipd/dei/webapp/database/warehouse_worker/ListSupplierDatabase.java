package it.unipd.dei.webapp.database.warehouse_worker;

import it.unipd.dei.webapp.resource.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Show the list of suppliers

 * @author ---
 * @version 1.00
 * @since 1.00
 */
public final class ListSupplierDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT supplier_name FROM Factory.supplier ORDER BY supplier_name ASC";

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT_full = "SELECT * FROM Factory.supplier";

    /**
     * Connection to the database
     */
    private final Connection con;

    /**
     * The Supplier to be searched into the database
     */
    private final Supplier supplier;

    /**
     * Creates a new object for adding a supplier into the database.
     *
     * @param con
     *            the connection to the database.
     * @param supplier
     *            the supplier to be added into the database.
     */
    public ListSupplierDatabase(final Connection con, final Supplier supplier){
        this.con = con;
        this.supplier = supplier;
    }

    /**
     * Creates a new object for retrieve all the supplier from the database.
     *
     * @param con
     *            the connection to the database.
     */
    public ListSupplierDatabase(final Connection con) {
        this.con = con;
        this.supplier = null;
    }

    /**
     * Shows all names of suppliers
     *
     * @return a list which represents all names of suppliers.
     *
     * @throws SQLException
     *             if any error occurs while searching for suppliers.
     */
    public List<String> listSupplier() throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<String> listSupp = new ArrayList<String>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                listSupp.add(rs.getString("supplier_name"));
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return listSupp;
    }

    /**
     * Shows a list of suppliers.
     *
     * @return a list of {@code Supplier} object.
     *
     * @throws SQLException
     *             if any error occurs while searching for suppliers.
     */
    public List<Supplier> listSupplierFull() throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Supplier> listsup = new ArrayList<Supplier>();

        try {
            pstmt = con.prepareStatement(STATEMENT_full);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                //create object
                Supplier mat = new Supplier(rs.getString("supplier_name"), rs.getString("country"));
                listsup.add(mat);
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return listsup;
    }
}
