package it.unipd.dei.webapp.database.administrator;

import it.unipd.dei.webapp.resource.Credential;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class to access the database and mange the credential entity
 */
public final class CredentialDatabase {

    /**
     * SQL statement to retrieve the credential linked to a specific employee from the database
     */
    private static final String STATEMENT = "SELECT * " +
            "FROM Factory.Credential " +
            "WHERE employee_id = ?";
    /**
     * SQL statement to insert a new credential entity in the database
     */
    private static final String STATEMENT_ADD = "INSERT INTO Factory.Credential VALUES(?::Factory.USRNAME, md5(?)::Factory.PSWD, ?) RETURNING *";
    /**
     * SQL statement to update an already existent credential entity
     */
    private static final String STATEMENT_UPDATE = "UPDATE Factory.Credential " +
            "SET username = ?::Factory.USRNAME, passwrd = md5(?)::Factory.PSWD WHERE employee_id = ? RETURNING *";
    /**
     * SQL statement to delete a fixed cost entity from the database
     */
    private static final String STATEMENT_DELETE = "DELETE FROM Factory.Credential " +
            "WHERE employee_id = ? RETURNING *";

    /**
     * Connection to the database
     */
    private final Connection con;
    /**
     * employee_id linked to the the credential to search
     */
    private final UUID employee_id;
    /**
     * Credential resources that need to be add or remove from the database
     */
    private final Credential credential;

    /**
     * Create a new object to retrieve or delete the credentials of an employee
     *
     * @param con Connection to the database
     * @param employee_id employee_id linked to the the credential to search
     */
    public CredentialDatabase(final Connection con, final UUID employee_id) {
        this.con = con;
        this.employee_id = employee_id;
        this.credential = null;
    }

    /**
     * Create a new standard object of the class
     *
     * @param con Connection to the database
     */
    public CredentialDatabase(final Connection con) {
        this.con = con;
        this.employee_id = null;
        this.credential = null;
    }

    /**
     * Create a new object to update an already existing credential entity
     *
     * @param con Connection to the database
     * @param credential Credential resources that need to be add or remove from the database
     */
    public CredentialDatabase(final Connection con, final Credential credential) {
        this.con = con;
        this.employee_id = null;
        this.credential = credential;
    }

    /**
     * Method that returns the credentials corresponding to a specific employee_id
     *
     * @return a Credential resources containing the credential info about the required employee
     * @throws SQLException if any error occurs while inserting the item
     */
    public Credential searchCredentialById() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        final List<Credential> credentials = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, employee_id);

            rs = pstmt.executeQuery();

            rs.next();

            credentials.add(new Credential(
                    rs.getString("username"),
                    rs.getString("passwrd"),
                    UUID.fromString(rs.getString("employee_id"))));
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            con.close();
        }
        return credentials.get(0);
    }

    /**
     * Method that insert in the database a new credential entity
     *
     * @return the just inserted Credential it.unipd.dei.webapp.resource
     * @throws SQLException if any error occurs while inserting the item
     */
    public Credential addCredential() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;

        Credential c = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_ADD);
            pstmt.setString(1, credential.getUsername());
            pstmt.setString(2, credential.getPassword());
            pstmt.setObject(3, credential.getEmployeeId());

            rs = pstmt.executeQuery();

            if (rs.next())
                c = new Credential(rs.getString("username"),
                        rs.getString("passwrd"),
                        UUID.fromString(rs.getString("employee_id")));
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return c;
    }

    /**
     * Method that update an already existing credential resources
     *
     * @return the Credential resources after the update
     * @throws SQLException if any error occurs while inserting the item
     */
    public Credential updateCredential() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;

        Credential c = null;

        try {
            pstmt = con.prepareStatement(STATEMENT_UPDATE);
            pstmt.setString(1, credential.getUsername());
            pstmt.setString(2, credential.getPassword());
            pstmt.setObject(3, credential.getEmployeeId());

            rs = pstmt.executeQuery();

            if (rs.next())
                c = new Credential(rs.getString("username"),
                        rs.getString("passwrd"),
                        UUID.fromString(rs.getString("employee_id")));
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return c;
    }

    /**
     * Method that delete a credential entity from the database
     *
     * @return the just deleted Credential resources
     * @throws SQLException if any error occurs while inserting the item
     */
    public Credential deleteCredential() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs;

        Credential c = null;

        try{
            pstmt = con.prepareStatement(STATEMENT_DELETE);
            pstmt.setObject(1, employee_id);

            rs = pstmt.executeQuery();

            if (rs.next())
                c = new Credential(rs.getString("username"),
                        rs.getString("passwrd"),
                        UUID.fromString(rs.getString("employee_id")));
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return c;
    }
}
