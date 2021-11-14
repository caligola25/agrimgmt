package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add a new {@link Supplier} to the database.
 */
public final class AddSupplierDatabase {

    /**
     * The SQL statement to be executed.
     */
    private static final String STATEMENT = "INSERT INTO Factory.Supplier VALUES (?, ?)";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * The supplier to be added to the database
     */
    private final Supplier supplier;

    /**
     * Creates an object for adding a Supplier to the database.
     * @param con The connection to the database.
     * @param supplier The supplier object to be added.
     */
    public AddSupplierDatabase(final Connection con, final Supplier supplier){
        this.con = con;
        this.supplier = supplier;
    }

    /**
     * Inserts the new supplier in the database.
     * @throws SQLException In case a problem with the database happens.
     * @throws IllegalArgumentException If any of the supplier's object fields are empty.
     */
    public void createSupplier() throws SQLException, IllegalArgumentException{

        PreparedStatement pstmt = null;

        //check if supplier fields are empty
        if (supplier.getName().equals("") || supplier.getCountry().equals(""))
            throw new IllegalArgumentException("All Supplier's data fields must not be empty");
        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getCountry());

            pstmt.execute();
        }finally {
            if (pstmt != null){
                pstmt.close();
            }
        }
        con.close();
    }
}
