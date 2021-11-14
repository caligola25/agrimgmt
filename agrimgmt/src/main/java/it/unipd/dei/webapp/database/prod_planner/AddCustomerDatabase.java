package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Performs the query to add a {@link Customer} to the database
 */
public final class AddCustomerDatabase {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Factory.Customer VALUES (?, ?, ?, ?, ?)";

    /**
     * Connection to the database.
     */
    private final Connection con;

    /**
     * The customer to be added to the database
     */
    private final Customer customer;

    /**
     * Create a new it.unipd.dei.webapp.resource AddCustomerDatabase it.unipd.dei.webapp.resource
     *
     * @param con Connection to the database
     * @param customer Customer to add to the database
     */
    public AddCustomerDatabase(final Connection con, final Customer customer){
        this.con = con;
        this.customer = customer;
    }

    /**
     * Execute the query to add the new Customer
     *
     * @throws SQLException if any error occurs while adding the customer to the database
     */
    public void createCustomer() throws SQLException{

        PreparedStatement pstmt = null;

        //check if customer fields empty
        if (customer.getName().equals("") || customer.getCountry().equals("") || customer.getCity().equals("") || customer.getStreet().equals(""))
            throw new IllegalArgumentException("All Customer's data fields must not be empty");
        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, customer.getCustomerId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getCountry());
            pstmt.setString(4, customer.getCity());
            pstmt.setString(5, customer.getStreet());

            pstmt.execute();
        }finally {
            if (pstmt != null){
                pstmt.close();
            }
        }
        con.close();
    }
}
