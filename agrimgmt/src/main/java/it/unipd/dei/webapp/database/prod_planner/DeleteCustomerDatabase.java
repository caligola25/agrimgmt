package it.unipd.dei.webapp.database.prod_planner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unipd.dei.webapp.resource.Customer;

/**
 * Delete a specific {@link Customer} from the database.
 */
public final class DeleteCustomerDatabase {

    /**
     * The query to be executed
     */
    private static final String STATEMENT_DELETE = "DELETE FROM Factory.customer WHERE customer_id = ?";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * The customer to be deleted from the database
     */
    private final Customer customer;

    /**
     * Create a new Object for deleting a Customer entry in the database.
     * @param con The connection to the database.
     * @param customer The object to be deleted.
     */
    public DeleteCustomerDatabase(Connection con, Customer customer) {
        this.con = con;
        this.customer = customer;
    }
    
    /**
     * Deletes the Customer from the database.
     * @throws SQLException In case anything goes wrong in the database.
     */
    public void deleteCustomer() throws SQLException{
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_DELETE);
            pstmt.setObject(1, customer.getCustomerId());

            pstmt.execute();
        } finally{
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
