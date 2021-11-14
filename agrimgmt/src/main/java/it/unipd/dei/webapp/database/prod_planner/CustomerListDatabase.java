package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Retrieve the list of {@link Customer} stored in the database.
 */
public final class CustomerListDatabase {

    /**
     * The SQL statement to be executed.
     */
    private static final String STATEMENT = "SELECT * FROM Factory.customer ORDER BY customer_name ASC";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Create an object to search for all Customers.
     * @param con the connection to the database.
     */
    public CustomerListDatabase(Connection con) {
        this.con = con;
    }

    /**
     * Performs the search in the database for Customers.
     * @return A ArrayList of all the Customers.
     * @throws SQLException If there is any problem in the SQL execution.
     */
    public List<Customer> customerList() throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet res = null;
        final List<Customer> customers = new ArrayList<>();

        try {

            pstmt = con.prepareStatement(STATEMENT);
            res = pstmt.executeQuery();

            //interested in the only result
            while (res.next()) {
                customers.add(new Customer(UUID.fromString(res.getString("customer_Id")),
                        res.getString("customer_name"), res.getString("country"),
                        res.getString("city"), res.getString("street")));
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
        return customers;
    }
}
