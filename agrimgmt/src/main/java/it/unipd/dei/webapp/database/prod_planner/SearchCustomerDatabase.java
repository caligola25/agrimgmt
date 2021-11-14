package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Search for a Customer stored in the database.
 */
public final class SearchCustomerDatabase {

    /**
     * The SQL statement to be executed to search a customer given his UUID.
     */
    private static final String STATEMENT_ID = "SELECT * FROM Factory.customer WHERE customer_id = ?";

    /**
     * The SQL statement to be executed to search a customer given his name.
     */
    private static final String STATEMENT_NAME = "SELECT * FROM Factory.customer WHERE customer_name = ?";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Attribute of the Customer to be found
     */
    private final UUID id;
    private final String name;

    /**
     * Create an object to search for a Customer given its UUID.
     * @param con the connection to the database.
     * @param id the id one wants to search.
     */
    public SearchCustomerDatabase(final Connection con, final UUID id){
        this.con = con;
        this.id = id;
        this.name = null;
    }

    /**
     * Create an object to search for a Customer given its name.
     * @param con the connection to the database.
     * @param name the name one wants to search.
     */
    public SearchCustomerDatabase(final Connection con, final String name){
        this.con = con;
        this.name = name;
        this.id = null;
    }

    /**
     * Performs the search in the database for the Customer given the UUID.
     * @return An List of Customer representing the customer with that id
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<Customer> searchCustomerById() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // Results of the search
        final List<Customer> customers = new ArrayList<Customer>();

        try{
            pstmt = con.prepareStatement(STATEMENT_ID);
            pstmt.setObject(1, id);

            rs = pstmt.executeQuery();

            while(rs.next()){
                customers.add(new Customer(UUID.fromString(rs.getString("customer_Id")),
                        rs.getString("customer_name"), rs.getString("country"),
                        rs.getString("city"), rs.getString("street")));
            }
        }finally {
            if (rs != null){
                rs.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return customers;
    }

    /**
     * Performs the search in the database for the Customer given the Name.
     * @return An List of Customer representing the customer with that name
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<Customer> searchCustomerByName() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // Results of the search
        final List<Customer> customers = new ArrayList<Customer>();

        try{
            pstmt = con.prepareStatement(STATEMENT_NAME);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            while(rs.next()){
                customers.add(new Customer(UUID.fromString(rs.getString("customer_Id")),
                        rs.getString("customer_name"), rs.getString("country"),
                        rs.getString("city"), rs.getString("street")));
            }
        }finally {
            if (rs != null){
                rs.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return customers;
    }
}
