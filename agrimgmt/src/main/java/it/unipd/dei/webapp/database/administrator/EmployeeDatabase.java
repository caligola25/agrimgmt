package it.unipd.dei.webapp.database.administrator;

import it.unipd.dei.webapp.resource.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class to access the database and mange the employee entity
 */
public final class EmployeeDatabase {

    /**
     * SQL statement to retrieve a specific employee starting from its id
     */
    private static final String STATEMENT = "SELECT * " +
            "FROM Factory.Employee " +
            "WHERE Factory.Employee.employee_id= ?";
    /**
     * SQL statement to retrieve all the employee in the database
     */
    private static final String STATEMENT_TOTAL = "SELECT * " +
            "FROM Factory.Employee AS E " +
            "ORDER BY E.employee_surname ASC, E.employee_name ASC";
    /**
     * SQL statement to insert a new employee entity in the database
     */
    private static final String STATEMENT_ADD = "INSERT INTO Factory.Employee VALUES(?, ?, ?, ?::Factory.ROLE, ?, ?::Factory.OPERATIONS) RETURNING *";
    /**
     * SQL statement to update an already existing employee entity
     */
    private static final String STATEMENT_UPDATE = "UPDATE Factory.Employee " +
            "SET employee_name = ?, employee_surname = ?, employee_role = ?::Factory.ROLE, salary = ?, operations = ?::Factory.OPERATIONS " +
            "WHERE employee_id = ? RETURNING *";
    /**
     * SQL statement to delete an employee entity from the database
     */
    private static final String STATEMENT_DELETE = "DELETE FROM Factory.Employee " +
            "WHERE employee_id = ? RETURNING *";

    /**
     * Connection to the database
     */
    private final Connection con;
    /**
     * employee_id used to identify a specific employee
     */
    private final UUID employee_id;
    /**
     * Employee resources used to update or insert an employee in the database
     */
    private final Employee employee;

    /**
     * Create a new object to search a specific employee in the database
     *
     * @param con Connection to the database
     * @param employee_id employee_id used to identify a specific employee
     */
    public EmployeeDatabase(final Connection con, final UUID employee_id) {
        this.con = con;
        this.employee_id = employee_id;
        this.employee = null;
    }

    /**
     * Create a new object to retrieve all the employee in the database
     *
     * @param con Connection to the database
     */
    public EmployeeDatabase(final Connection con) {
        this.con = con;
        this.employee_id = null;
        this.employee = null;
    }

    /**
     * Create a new object to update or insert an employee in the database
     *
     * @param con Connection to the database
     * @param employee Employee resources used to update or insert an employee in the database
     */
    public EmployeeDatabase(final Connection con, final Employee employee) {
        this.con = con;
        this.employee_id = null;
        this.employee = employee;
    }

    /**
     * Method that return all the information about a specific employee starting from his id
     *
     * @return an Employee resources containing all the information about the employee
     * @throws SQLException if any error occurs while inserting the item
     */
    public Employee viewEmployeeById() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Employee> employees = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, employee_id);

            rs = pstmt.executeQuery();

            rs.next();

            employees.add(new Employee(
                    (UUID) rs.getObject("employee_id"),
                    rs.getString("employee_surname"),
                    rs.getString("employee_name"),
                    rs.getInt("operations"),
                    rs.getFloat("salary"),
                    rs.getString("employee_role")
            ));
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return employees.get(0);
    }

    /**
     * Method that returns all the employee in the database
     *
     * @return a List of Employee resources
     * @throws SQLException if any error occurs while inserting the item
     */
    public List<Employee> viewEmployee() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Employee> employees = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT_TOTAL);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                employees.add(new Employee(
                        UUID.fromString(rs.getString("employee_id")),
                        rs.getString("employee_surname"),
                        rs.getString("employee_name"),
                        rs.getInt("operations"),
                        rs.getFloat("salary"),
                        rs.getString("employee_role")
                ));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return employees;
    }

    /**
     * Method that add a new employee entity in the database
     *
     * @return the just added Employee it.unipd.dei.webapp.resource
     * @throws SQLException if any error occurs while inserting the item
     */
    public Employee addEmployee() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;

        Employee e = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_ADD);
            pstmt.setObject(1, employee.getEmployeeId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getSurname());
            pstmt.setString(4, employee.getRole());
            pstmt.setFloat(5, employee.getSalary());

            if (employee.getRole().equals("Production line worker"))
                pstmt.setInt(6, employee.getnOperation());
            else
                pstmt.setObject(6, null);

            rs = pstmt.executeQuery();

            if (rs.next())
                e = new Employee(UUID.fromString(rs.getString("employee_id")), rs.getString("employee_surname"),
                        rs.getString("employee_name"), rs.getInt("operations"), rs.getFloat("salary"),
                        rs.getString("employee_role"));
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return e;
    }

    /**
     * Method that update an already existing employee entity in the database
     *
     * @return the just updated Employee it.unipd.dei.webapp.resource
     * @throws SQLException if any error occurs while inserting the item
     */
    public Employee updateEmployee() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;

        Employee e = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_UPDATE);
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getSurname());
            pstmt.setString(3, employee.getRole());
            pstmt.setFloat(4, employee.getSalary());
            pstmt.setObject(6, employee.getEmployeeId());

            if (employee.getRole().equals("Production line worker"))
                pstmt.setInt(5, employee.getnOperation());
            else
                pstmt.setObject(5, null);

            rs = pstmt.executeQuery();

            if (rs.next())
                e = new Employee(UUID.fromString(rs.getString("employee_id")), rs.getString("employee_surname"),
                        rs.getString("employee_name"), rs.getInt("operations"), rs.getFloat("salary"),
                        rs.getString("employee_role"));
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return e;
    }

    /**
     * Method that remove an employee entity in the database
     *
     * @return the just deleted Employee resources
     * @throws SQLException if any error occurs while inserting the item
     */
    public Employee deleteEmployee() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;

        Employee e = null;

        try{
            pstmt = con.prepareStatement(STATEMENT_DELETE);
            pstmt.setObject(1, employee_id);

            rs = pstmt.executeQuery();

            if (rs.next())
                e = new Employee(UUID.fromString(rs.getString("employee_id")), rs.getString("employee_surname"),
                        rs.getString("employee_name"), rs.getInt("operations"), rs.getFloat("salary"),
                        rs.getString("employee_role"));
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return e;
    }
}
