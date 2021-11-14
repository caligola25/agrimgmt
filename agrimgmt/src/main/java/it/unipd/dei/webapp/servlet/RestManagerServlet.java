package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.resource.Employee;
import it.unipd.dei.webapp.resource.Credential;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.rest.CredentialRestResource;
import it.unipd.dei.webapp.rest.EmployeeRestResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Manages the REST API for the different REST resources.
 */
public final class RestManagerServlet extends AbstractDatabaseServlet {

    /**
     * The JSON MIME media type
     */
    private static final String JSON_MEDIA_TYPE = "application/json";

    /**
     * The JSON UTF-8 MIME media type
     */
    private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

    /**
     * The any MIME media type
     */
    private static final String ALL_MEDIA_TYPE = "*/*";

    @Override
    protected final void service(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType(JSON_UTF_8_MEDIA_TYPE);
        final OutputStream out = res.getOutputStream();

        try {
            // if the request method and/or the MIME media type are not allowed, return.
            // Appropriate error message sent by {@code checkMethodMediaType}
            if (!checkMethodMediaType(req, res)) {
                return;
            }

            // if the requested it.unipd.dei.webapp.resource was an Employee, delegate its processing and return
            if (processEmployee(req, res)) {
                return;
            }

            // if the requested it.unipd.dei.webapp.resource was a Credential, delegate its processing and return
            if (processCredential(req, res)) {
                return;
            }

            // if none of the above process methods succeeds, it means an unknown it.unipd.dei.webapp.resource has been requested
            final Message m = new Message("Unknown it.unipd.dei.webapp.resource requested.", "E4A6",
                    String.format("Requested it.unipd.dei.webapp.resource is %s.", req.getRequestURI()));
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            m.toJSON(out);
        } finally {
            // ensure to always flush and close the output stream
            out.flush();
            out.close();
        }
    }

    /**
     * Checks that the request method and MIME media type are allowed.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request method and the MIME type are allowed; {@code false} otherwise.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */
    private boolean checkMethodMediaType(final HttpServletRequest req, final HttpServletResponse res)
            throws IOException {

        final String method = req.getMethod();
        final String contentType = req.getHeader("Content-Type");
        final String accept = req.getHeader("Accept");
        final OutputStream out = res.getOutputStream();

        Message m = null;

        if(accept == null) {
            m = new Message("Output media type not specified.", "E4A1", "Accept request header missing.");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(out);
            return false;
        }

        if(!accept.contains(JSON_MEDIA_TYPE) && !accept.equals(ALL_MEDIA_TYPE)) {
            m = new Message("Unsupported output media type. Resources are represented only in application/json.",
                    "E4A2", String.format("Requested representation is %s.", accept));
            res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            m.toJSON(out);
            return false;
        }

        switch(method) {
            case "GET":
            case "DELETE":
                // nothing to do
                break;

            case "POST":
            case "PUT":
                if(contentType == null) {
                    m = new Message("Input media type not specified.", "E4A3", "Content-Type request header missing.");
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    m.toJSON(out);
                    return false;
                }

                if(!contentType.contains(JSON_MEDIA_TYPE)) {
                    m = new Message("Unsupported input media type. Resources are represented only in application/json.",
                            "E4A4", String.format("Submitted representation is %s.", contentType));
                    res.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                    m.toJSON(out);
                    return false;
                }

                break;
            default:
                m = new Message("Unsupported operation.",
                        "E4A5", String.format("Requested operation %s.", method));
                res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                m.toJSON(out);
                return false;
        }

        return true;
    }

    /**
     * Checks whether the request if for an {@link Employee} it.unipd.dei.webapp.resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code Employee}; {@code false} otherwise.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */
    private boolean processEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {

        final String method = req.getMethod();
        final OutputStream out = res.getOutputStream();

        String path = req.getRequestURI();
        Message m = null;

        // the requested it.unipd.dei.webapp.resource was not an employee
        if(path.lastIndexOf("protected/administrator/rest/employee") <= 0) {
            return false;
        }

        try {
            // strip everyhing until after the /employee
            path = path.substring(path.lastIndexOf("employee") + 8);

            // the request URI is: /employee
            // if method GET, list employees
            // if method POST, create employee
            if (path.length() == 0 || path.equals("/")) {

                switch (method) {
                    case "GET":
                        new EmployeeRestResource(req, res, getDataSourceR().getConnection()).listEmployee();
                        break;
                    case "POST":
                        new EmployeeRestResource(req, res, getDataSourceW().getConnection()).createEmployee();
                        break;
                    default:
                        m = new Message("Unsupported operation for URI /employee.",
                                "E4A5", String.format("Requested operation %s.", method));
                        res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        m.toJSON(res.getOutputStream());
                        break;
                }
            } else {
                    // the request URI is: /employee/{id}
                    try {

                        // check that the parameter is actually a valid UUID
                        UUID.fromString(path.substring(1));

                        switch (method) {
                            case "GET":
                                new EmployeeRestResource(req, res, getDataSourceR().getConnection()).readEmployee();
                                break;
                            case "PUT":
                                new EmployeeRestResource(req, res, getDataSourceW().getConnection()).updateEmployee();
                                break;
                            case "DELETE":
                                new EmployeeRestResource(req, res, getDataSourceW().getConnection()).deleteEmployee();
                                break;
                            default:
                                m = new Message("Unsupported operation for URI /employee/{id}.",
                                        "E4A5", String.format("Requested operation %s.", method));
                                res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                                m.toJSON(res.getOutputStream());
                        }
                    } catch (IllegalArgumentException e) {
                        m = new Message("Wrong format for URI /employee/{id}: {id} is not a UUID.",
                                "E4A7", e.getMessage());
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        m.toJSON(res.getOutputStream());
                    } catch (SQLException ex) {
                        m = new Message("Error while accessing the database", "E200", ex.getMessage());
                        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        m.toJSON(res.getOutputStream());
                    }
            }
        } catch(Throwable t) {
            m = new Message("Unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }

        return true;

    }

    /**
     * Checks whether the request if for a {@link Credential} it.unipd.dei.webapp.resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for a {@code Credential}; {@code false} otherwise.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */
    private boolean processCredential(HttpServletRequest req, HttpServletResponse res) throws IOException {

        final String method = req.getMethod();
        final OutputStream out = res.getOutputStream();

        String path = req.getRequestURI();
        Message m = null;

        // the requested it.unipd.dei.webapp.resource was not a credential
        if(path.lastIndexOf("protected/administrator/rest/credential") <= 0) {
            return false;
        }

        try {
            // strip everyhing until after the /credential
            path = path.substring(path.lastIndexOf("credential") + 10);

            // the request URI is: /credential
            // if method POST, create credential
            if (path.length() == 0 || path.equals("/")) {

                switch (method) {
                    case "POST":
                        new CredentialRestResource(req, res, getDataSourceW().getConnection()).createCredential();
                        break;
                    default:
                        m = new Message("Unsupported operation for URI /credential.",
                                "E4A5", String.format("Requested operation %s.", method));
                        res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        m.toJSON(res.getOutputStream());
                        break;
                }
            } else {
                // the request URI is: /credential/{id}
                try {

                    // check that the parameter is actually a valid UUID
                    UUID.fromString(path.substring(1));

                    switch (method) {
                        case "GET":
                            new CredentialRestResource(req, res, getDataSourceR().getConnection()).readCredential();
                            break;
                        case "PUT":
                            new CredentialRestResource(req, res, getDataSourceW().getConnection()).updateCredential();
                            break;
                        case "DELETE":
                            new CredentialRestResource(req, res, getDataSourceW().getConnection()).deleteCredential();
                            break;
                        default:
                            m = new Message("Unsupported operation for URI /credential/{id}.",
                                    "E4A5", String.format("Requested operation %s.", method));
                            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                            m.toJSON(res.getOutputStream());
                    }
                } catch (NumberFormatException e) {
                    m = new Message("Wrong format for URI /credential/{id}: {id} is not a UUID.",
                            "E4A7", e.getMessage());
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    m.toJSON(res.getOutputStream());
                } catch (SQLException ex) {
                    m = new Message("Error while accessing the database", "E200", ex.getMessage());
                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    m.toJSON(res.getOutputStream());
                }
            }
        } catch(Throwable t) {
            m = new Message("Unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }

        return true;

    }
}