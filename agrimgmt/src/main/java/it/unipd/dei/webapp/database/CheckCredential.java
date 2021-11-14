package it.unipd.dei.webapp.database;

import it.unipd.dei.webapp.resource.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Check the credential of the employee

 * @version 1.00
 * @since 1.00
 */

public final class CheckCredential {
    private static final String STATEMENT_1 ="Select * From Factory.credential where Factory.credential.username=? AND Factory.credential.passwrd=md5(?);";
    private static final String STATEMENT_2 = "Select * From Factory.credential join Factory.employee on Factory.credential.employee_id=Factory.employee.employee_id where Factory.credential.username=?;";
    private final Connection con;

    /**
     * Creates a new object for checking the credential
     *
     * @param e
     *            the connection to the database.
     */
    public CheckCredential (final Connection e){
        this.con = e;
    }


    /**
     * Check the credential
     * @param username of the employee
     * @param password of the employee
     * @return TRUE if the passwords match else return FALSE
     * @throws SQLException if any error occurs while checking the credential
     */
    public boolean checkCredential(String username, String password) throws  SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs;
        boolean authenticationResult = false;
        try {

            pstmt = con.prepareStatement(STATEMENT_1);
            pstmt.setObject(1, username);
            pstmt.setObject(2, password);
            rs = pstmt.executeQuery();
            if(rs.next()){
                authenticationResult=true;
            }
            return authenticationResult;
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
    }


    /**
     * Get the employee object from his username
     * @param username of the employee
     * @return the employee associated to the username given by the param
     * @throws SQLException if any error occurs while getting the employee
     */

    public Employee getEmployeeFromUsername(String username) throws  SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //avrò sicuramente un solo elemento
        final List<Employee> employees = new ArrayList<Employee>();
        try {
            pstmt = con.prepareStatement(STATEMENT_2);
            pstmt.setObject(1, username);
            rs = pstmt.executeQuery();
            rs.next();
            employees.add(new Employee((UUID) rs.getObject("employee_id"),
                    rs.getString("employee_surname"),
                    rs.getString("employee_name"),
                    rs.getInt("operations"),
                    rs.getFloat("salary"),
                    rs.getString("employee_role")
                    ));
        }finally {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            con.close();
        }
        return employees.get(0);
    }
}
