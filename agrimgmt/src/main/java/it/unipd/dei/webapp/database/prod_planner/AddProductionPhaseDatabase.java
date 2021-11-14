package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.ProductionPhase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add a new {@link ProductionPhase} to the database.
 */
public final class AddProductionPhaseDatabase {

    /**
     * Connection to the database.
     */
    private final Connection con;
    /**
     * The production phase to be added to the database
     */
    private final ProductionPhase prodPhase;
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO Factory.production_phase VALUES (?, ?::factory.phase_status, ?::interval, ?, ?, ?)";

    /**
     * Creates a new object to add a production phase into the database.
     * @param con
     *          the connection to the database.
     * @param prodPhase
     *          the production phase to be added to the database.
     */
    public AddProductionPhaseDatabase(final Connection con, final ProductionPhase prodPhase) {
        this.con = con;
        this.prodPhase = prodPhase;
    }

    /**
     * Adds a new production phase to the database
     * @throws SQLException
     *          if any error occurs while inserting the production phase
     */
    public void addProductionPhase() throws SQLException {
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, prodPhase.getProdPhaseId());
            pstmt.setString(2, prodPhase.getStatus());
            pstmt.setString(3, prodPhase.getActualTime());
            pstmt.setObject(4, prodPhase.getItemId());
            pstmt.setObject(5, prodPhase.getProcessId());
            pstmt.setObject(6, prodPhase.getEmployeeId());

            pstmt.execute();
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }
}
