package it.unipd.dei.webapp.database.line_worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Class to access the database and update the number of operations of a employee
 */
public class UpdateEmployeeBusyDatabase {

    /**
     * The SQL statement to be executed.
     */
    private static final String STATEMENT = "UPDATE Factory.employee SET operations = ? WHERE employee_id = ?";
    private final Connection con;

    /**
     * Creates a new object to modify the number of assigned operations to an Employee
     * @param con the connection to the database.
     */
    public UpdateEmployeeBusyDatabase(Connection con) {
        this.con = con;
    }


    /**
     * Updates the number of operations assigned to the employee.
     * @param newOperations  new number of operations
     * @param employeeId employee's id
     * @throws SQLException If there is any problem in the SQL execution.
     */
    public void updateEmployeeBusy(int newOperations, UUID employeeId) throws SQLException {
        PreparedStatement pstmt = null;

        try{
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setInt(1, newOperations);
            pstmt.setObject(2, employeeId);;

            pstmt.execute();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
    }
    
}
