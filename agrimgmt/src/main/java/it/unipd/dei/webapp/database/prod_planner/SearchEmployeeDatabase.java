package it.unipd.dei.webapp.database.prod_planner;

import it.unipd.dei.webapp.resource.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Search for {@link Employee} stored in the database.
 */
public final class SearchEmployeeDatabase {
    /**
     * The SQL statement to be executed to search for the employees with less than 10 assigned operations.
     */
    private static final String STATEMENT = "SELECT * FROM Factory.Employee AS Employee WHERE Employee.operations < 10 ORDER BY Employee.operations ASC";

    /**
     * The SQL statement to be executed to retrieve the lists of Employees with role Production line worker.
     */
    private static final String STATEMENT_LIST = "SELECT * FROM Factory.employee WHERE employee_role = 'Production line worker'";

    /**
     * The connection to the database.
     */
    private final Connection con;

    /**
     * Create an object to search for an employee who has the least activities already assigned.
     * @param con the connection to the database.
     */
    public SearchEmployeeDatabase(final Connection con) {
        this.con = con;
    }

    /**
     * Search for the employee with the least activities already assigned.
     * At a later stage we can decide to return more than one employee at a time.
     * @return a List containing the employee with the least amount of operations already assigned.
     * @throws SQLException if there is any problem in the execution of the SQL statement.
     */
    public Employee searchFreeEmployee() throws SQLException{
        ArrayList<Employee> employees = this.orderEmployees();

        return employees.get(0);
    }

    /**
     * Get all the employees ordered by ascending number of assigned operations.
     * @return a list of employees order by the number of assigned operations.
     * @throws SQLException if there is any problem in the execution of the SQL statement.
     */
    private ArrayList<Employee> orderEmployees() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // results of the query
        final ArrayList<Employee> employees = new ArrayList<Employee>();

        try{
            pstmt = con.prepareStatement(STATEMENT);
            res = pstmt.executeQuery();
            
            // go through all the results
            while (res.next()) {
                //final UUID employeeId,final String surname,final String name, int nOperation,final float salary,final String role
                employees.add(new Employee(UUID.fromString(res.getString("employee_id")), 
                        res.getString("employee_name"), res.getString("employee_surname"),
                        res.getInt("operations"), res.getFloat("salary"),
                        res.getString("employee_role")));
            }
        }
        finally{
            if (res != null){
                res.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }

        return employees;
    }

    /**
     * Performs the search in the database for a list of employees.
     * @return A list of employees.
     * @throws SQLException If there is any problem in the SQL execution
     */
    public List<Employee> employeeList() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet res = null;

        // Results of the search
        final List<Employee> employees = new ArrayList<>();

        try{
            pstmt = con.prepareStatement(STATEMENT_LIST);

            res = pstmt.executeQuery();

            while (res.next()) {
                employees.add(new Employee(UUID.fromString(res.getString("employee_id")), res.getString("employee_name"),
                        res.getString("employee_surname"), res.getInt("operations"),
                        res.getFloat("salary"), res.getString("employee_role")));
            }

        }finally {
            if (res != null){
                res.close();
            }
            if (pstmt != null){
                pstmt.close();
            }

            con.close();
        }
        return employees;
    }
}