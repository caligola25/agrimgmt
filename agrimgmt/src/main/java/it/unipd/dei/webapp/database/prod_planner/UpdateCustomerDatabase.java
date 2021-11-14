package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Update a {@link Customer} stored in the database
 */
public final class UpdateCustomerDatabase {

    /**
     * The SQL statement to be executed to update the name of a Customer.
     */
    private static final String STATEMENT_NAME = "UPDATE Factory.customer " +
            "SET customer_name = ? " +
            "WHERE customer_id = ?";

    /**
     * The SQL statement to be executed to update the address of a Customer.
     */
    private static final String STATEMENT_ADDRESS = "UPDATE Factory.customer " +
            "SET country = ? , city = ?, street = ?" +
            "WHERE customer_id = ?";

    /**
     * The SQL statement to be executed to update every attribute of a Customer.
     */
    private static final String STATEMENT_ALL = "UPDATE Factory.customer " +
            "SET customer_name = ?, country = ? , city = ?, street = ?" +
            "WHERE customer_id = ?";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * Attributes of the Customer to be updated.
     */
    private final UUID id;
    private final String newName;
    private final String newCountry;
    private final String newCity;
    private final String newStreet;


    /**
     * Create an object to update a Customer.
     *
     * @param con connection to the database
     * @param id the UUID of the Customer to be updated
     * @param newName the new name of the Customer
     * @param newCountry the new country of the Customer
     * @param newCity the new city of the Customer
     * @param newStreet the new street of the Customer
     */
    public UpdateCustomerDatabase(final Connection con, final UUID id, final String newName, final String newCountry, final String newCity, final String newStreet) {
        this.con = con;
        this.id = id;
        this.newName = newName;
        this.newCountry = newCountry;
        this.newCity = newCity;
        this.newStreet = newStreet;
    }


    /**
     * Performs the update of the given customer name
     *
     * @throws SQLException if any error occurs accessing the database
     */
    public void updateCustomerName() throws SQLException {
        PreparedStatement pstmt = null;

        if (newName.equals(""))
            throw new IllegalArgumentException("New Name must not be empty.");

        try{
            pstmt = con.prepareStatement(STATEMENT_NAME);
            pstmt.setString(1, newName);
            pstmt.setObject(2, id);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }

    /**
     * Performs the update of the given customer address
     *
     * @throws SQLException if any error occurs accessing the database
     */
    public void updateCustomerAddress() throws SQLException, IllegalArgumentException {
        PreparedStatement pstmt = null;

        if (newCountry.equals("") || newCity.equals("") || newStreet.equals(""))
            throw new IllegalArgumentException("New Country, New City and New Street must not be empty.");
        
        try{
            pstmt = con.prepareStatement(STATEMENT_ADDRESS);
            pstmt.setString(1, newCountry);
            pstmt.setString(2, newCity);
            pstmt.setString(3, newStreet);
            pstmt.setObject(4, id);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }

    /**
     * Performs the update of the given customer
     *
     * @throws SQLException if any error occurs accessing the database
     */
    public void updateCustomerAll() throws SQLException, IllegalArgumentException {
        PreparedStatement pstmt = null;

        if (newName.equals("") || newCountry.equals("") || newCity.equals("") || newStreet.equals(""))
            throw new IllegalArgumentException("New Name, New Country, New City and New Street must not be empty.");
        
        try{
            pstmt = con.prepareStatement(STATEMENT_ALL);
            pstmt.setString(1, newName);
            pstmt.setString(2, newCountry);
            pstmt.setString(3, newCity);
            pstmt.setString(4, newStreet);
            pstmt.setObject(5, id);

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}