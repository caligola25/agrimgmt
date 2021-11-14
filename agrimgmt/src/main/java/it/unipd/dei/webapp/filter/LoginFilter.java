package it.unipd.dei.webapp.filter;

import it.unipd.dei.webapp.database.CheckCredential;
import it.unipd.dei.webapp.resource.Employee;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;


/**
 * Check for successful authentication to allow for accessing protected resources.
 */
public class LoginFilter implements Filter {
    private FilterConfig config = null;
    private DataSource dsr;
    private static final Base64.Decoder DECODER = Base64.getDecoder();
    private Employee employee=null;

    /**
     * When container initializes the Filter, this is the method that gets invoked.
     * This method is called only once in the lifecycle of filter.
     *
     * @param config used by container to provide init parameters and it.unipd.dei.webapp.servlet context object to the Filter
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     */
    @Override
    public void init(final FilterConfig config) throws ServletException {
        if (config == null) {
            throw new ServletException("Filter configuration cannot be null.");
        }
        this.config = config;
        InitialContext cxt;
        try {
            cxt = new InitialContext();
            dsr = (DataSource) cxt.lookup("java:/comp/env/jdbc/readonly");
        } catch (NamingException e) {
            dsr = null;
            throw new ServletException( String.format("Impossible to access the connection pool to the database: %s", e.getMessage()));
        }
    }

    /**
     * the method is invoked every time by container when it has to apply filter to a it.unipd.dei.webapp.resource
     * @param servletRequest ServletRequest
     * @param servletResponse ServletResponse
     * @param chain FilterChain is used to invoke the next filter in the chain.
     * @throws IOException if any error occurs in the client/server communication.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     */
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {

        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
            throw new ServletException("Only HTTP requests/responses are allowed.");
        }
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession(false);
        if (session == null) {
            if (!authenticateUser(req, res)) {
                return;
            }
        } else {
            employee = (Employee) session.getAttribute("employee");
            if (employee == null) {
                session.invalidate();
                if (!authenticateUser(req, res)) {
                    return;
                }
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    /**This is the method where we can close any resources opened by filter.
     * This method is called only once in the lifetime of filter.
     */
    @Override
    public void destroy() {
        config = null;
        dsr = null;
    }

    /**
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @return TRUE if the employee is correctly authenticated otherwise return FALSE
     * @throws IOException send for error done by sendAuthenticationChallenge method
     */
    private boolean authenticateUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
        final String auth = req.getHeader("Authorization");
        if (auth == null || auth.equals("")) {
            sendAuthenticationChallenge(req, res);
            return false;
        }
        if (!auth.toUpperCase().startsWith("BASIC ")) {
            sendAuthenticationChallenge(req, res);
            return false;
        }

        final String pair = new String(DECODER.decode(auth.substring(6)));
        // userDetails[0] is the username; userDetails[1] is the password
        final String[] userDetails = pair.split(":", 2);
        try {
            if (new CheckCredential(dsr.getConnection()).checkCredential(userDetails[0], userDetails[1])) {
                // create a  new session
                HttpSession session = req.getSession(true);
                employee = new CheckCredential(dsr.getConnection()).getEmployeeFromUsername(userDetails[0]);
                session.setAttribute("employee", employee);
                return true;
            }else {
                sendAuthenticationChallenge(req, res);
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Sends the authentication challenge.
     *
     * @param res the HTTP it.unipd.dei.webapp.servlet response.
     * @throws IOException if anything goes wrong while sending the authentication challenge.
     */
    private void sendAuthenticationChallenge(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //res.setHeader("WWW-Authenticate", "Basic realm=Employee");
        res.sendRedirect("http://localhost:8080/agrimgmt-1.0/jsp/login.jsp");

        if(req.getHeader("referer") != null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

