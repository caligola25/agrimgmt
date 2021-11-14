package it.unipd.dei.webapp.rest;

import it.unipd.dei.webapp.database.administrator.EmployeeDatabase;
import it.unipd.dei.webapp.resource.Employee;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.ResourceList;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manages the REST API for the {@link Employee} it.unipd.dei.webapp.resource.
 */
public final class EmployeeRestResource extends RestResource {

    /**
     * Creates a new REST it.unipd.dei.webapp.resource for managing {@code Employee} resources.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public EmployeeRestResource(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super(req, res, con);
    }


    /**
     * Creates a new employee into the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void createEmployee() throws IOException {

        Employee e  = null;
        Message m = null;

        try{

            final Employee employee =  Employee.fromJSON(req.getInputStream());

            // creates a new object for accessing the database and stores the employee
            e = new EmployeeDatabase(con, employee).addEmployee();

            if(e != null) {
                res.setStatus(HttpServletResponse.SC_CREATED);
                e.toJSON(res.getOutputStream());
            } else {
                // it should not happen
                m = new Message("Cannot create the employee: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23505")) {
                m = new Message("Cannot create the employee: it already exists.", "E5A2", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot create the employee: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }

    /**
     * Reads an employee from the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void readEmployee() throws IOException {

        Employee e  = null;
        Message m = null;

        try{
            // parse the URI path to extract the id
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("employee") + 8);

            final String id = path.substring(1);


            // creates a new object for accessing the database and reads the employee
            e = new EmployeeDatabase(con, UUID.fromString(id)).viewEmployeeById();

            if(e != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                e.toJSON(res.getOutputStream());
            } else {
                m = new Message(String.format("Employee %s not found.", id), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot read employee: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }

    /**
     * Updates an employee in the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void updateEmployee() throws IOException {

        Employee e  = null;
        Message m = null;

        try {
            // parse the URI path to extract the id
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("employee") + 8);

            final String id = path.substring(1);
            final Employee employee = Employee.fromJSON(req.getInputStream());

            if (!id.equals(employee.getEmployeeId().toString())) {
                m = new Message(
                        "Wrong request for URI /employee/{badge}: URI request and employee it.unipd.dei.webapp.resource badges differ.",
                        "E4A7", String.format("Request URI badge %s; employee it.unipd.dei.webapp.resource badge %s.",
                        id, employee.getEmployeeId().toString()));
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                m.toJSON(res.getOutputStream());
                return;
            }

            // creates a new object for accessing the database and updates the employee
            e = new EmployeeDatabase(con, employee).updateEmployee();

            if(e != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                e.toJSON(res.getOutputStream());
            } else {
                m = new Message(String.format("Employee %s not found.", employee.getEmployeeId().toString()), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23503")) {
                m = new Message("Cannot update the employee: other resources depend on it.", "E5A4", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot update the employee: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }


    /**
     * Deletes an employee from the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void deleteEmployee() throws IOException {

        Employee e  = null;
        Message m = null;

        try{
            // parse the URI path to extract the badge
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("employee") + 8);

            final String id = path.substring(1);


            // creates a new object for accessing the database and deletes the employee
            e = new EmployeeDatabase(con, UUID.fromString(id)).deleteEmployee();

            if(e != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                e.toJSON(res.getOutputStream());
            } else {
                m = new Message(String.format("Employee %s not found.", id), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23503")) {
                m = new Message("Cannot delete the employee: other resources depend on it.", "E5A4", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot delete the employee: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }

    /**
     * Lists all the employees.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void listEmployee() throws IOException {

        List<Employee> el  = null;
        Message m = null;

        try{
            // creates a new object for accessing the database and lists all the employees
            el = new EmployeeDatabase(con).viewEmployee();

            if(el != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList<>(el).toJSON(res.getOutputStream());
            } else {
                // it should not happen
                m = new Message("Cannot list employees: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search employee: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}