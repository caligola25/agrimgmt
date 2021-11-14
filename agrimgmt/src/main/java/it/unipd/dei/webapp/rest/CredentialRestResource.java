package it.unipd.dei.webapp.rest;

import it.unipd.dei.webapp.database.administrator.CredentialDatabase;
import it.unipd.dei.webapp.resource.Credential;
import it.unipd.dei.webapp.resource.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Manages the REST API for the {@link Credential} it.unipd.dei.webapp.resource.
 */
public final class CredentialRestResource extends RestResource {

    /**
     * Creates a new REST it.unipd.dei.webapp.resource for managing {@code Credential} resources.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public CredentialRestResource(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super(req, res, con);
    }


    /**
     * Creates new credential into the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void createCredential() throws IOException {

        Credential c  = null;
        Message m = null;

        try{

            final Credential credential =  Credential.fromJSON(req.getInputStream());

            // creates a new object for accessing the database and stores the credential
            c = new CredentialDatabase(con, credential).addCredential();

            if(c != null) {
                res.setStatus(HttpServletResponse.SC_CREATED);
                c.toJSON(res.getOutputStream());
            } else {
                // it should not happen
                m = new Message("Cannot create the credential: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23505")) {
                m = new Message("Cannot create the credential: it already exists.", "E5A2", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot create the credential: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }

    /**
     * Reads a credential from the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void readCredential() throws IOException {

        Credential e  = null;
        Message m = null;

        try{
            // parse the URI path to extract the id
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("credential") + 10);

            final String id = path.substring(1);


            // creates a new object for accessing the database and reads the credential
            e = new CredentialDatabase(con, UUID.fromString(id)).searchCredentialById();

            if(e != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                e.toJSON(res.getOutputStream());
            } else {
                m = new Message(String.format("Credential %s not found.", id), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot read credential: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }

    /**
     * Updates a credential in the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void updateCredential() throws IOException {

        Credential e  = null;
        Message m = null;

        try {
            // parse the URI path to extract the id
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("credential") + 10);

            final String id = path.substring(1);
            final Credential credential = Credential.fromJSON(req.getInputStream());

            if (!id.equals(credential.getEmployeeId().toString())) {
                m = new Message(
                        "Wrong request for URI /credential/{badge}: URI request and credential it.unipd.dei.webapp.resource badges differ.",
                        "E4A7", String.format("Request URI id %s; credential of employee it.unipd.dei.webapp.resource id %s.",
                        id, credential.getEmployeeId().toString()));
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                m.toJSON(res.getOutputStream());
                return;
            }

            // creates a new object for accessing the database and updates the credential
            e = new CredentialDatabase(con, credential).updateCredential();

            if(e != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                e.toJSON(res.getOutputStream());
            } else {
                m = new Message(String.format("Credential of Employee %s not found.", credential.getEmployeeId().toString()), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23503")) {
                m = new Message("Cannot update the credential: other resources depend on it.", "E5A4", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot update the credential: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }


    /**
     * Deletes a credential from the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void deleteCredential() throws IOException {

        Credential e  = null;
        Message m = null;

        try{
            // parse the URI path to extract the badge
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("credential") + 10);

            final String id = path.substring(1);


            // creates a new object for accessing the database and deletes the credential
            e = new CredentialDatabase(con, UUID.fromString(id)).deleteCredential();

            if(e != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                e.toJSON(res.getOutputStream());
            } else {
                m = new Message(String.format("Credential od Employee %s not found.", id), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23503")) {
                m = new Message("Cannot delete the credential: other resources depend on it.", "E5A4", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot delete the credential: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }
}
