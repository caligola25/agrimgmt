package it.unipd.dei.webapp.servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * Gets the {@code DataSource} for managing the connection pool to the database.
 */
public abstract class AbstractDatabaseServlet extends HttpServlet {

    /**
     * The connection pool to the database.
     */
    private DataSource dsr;
    /**
     * The connection pool to the database.
     */
    private DataSource dsw;

    /**
     * Gets the {@code DataSource} for managing the connection pool to the database.
     * @param config
     *          a {@code ServletConfig} object containing the it.unipd.dei.webapp.servlet's
     *          configuration and initialization parameters.
     *
     * @throws ServletException
     *          if an exception has occurred that interferes with the it.unipd.dei.webapp.servlet's normal operation
     */
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        InitialContext cxt;

        try {
            cxt = new InitialContext();
            dsr = (DataSource) cxt.lookup("java:/comp/env/jdbc/readonly");
            dsw = (DataSource) cxt.lookup("java:/comp/env/jdbc/readwrite");
        } catch (NamingException e) {
            dsr = null;
            dsw = null;

            throw new ServletException(
                    String.format("Impossible to access the connection pool to the database: %s",
                            e.getMessage()));
        }
    }

    /**
     * Releases the {@code DataSource} for managing the connection pool to the database.
     */
    public void destroy() {
        dsr = null;
        dsw = null;
    }

    /**
     * Returns the {@code DataSource} for managing the connection pool to the database in readonly mode.
     *
     * @return the {@code DataSource} for managing the connection pool to the database in readonly mode.
     */
    protected final DataSource getDataSourceR() {
        return dsr;
    }

    /**
     * Returns the {@code DataSource} for managing the connection pool to the database in readwrite mode.
     *
     * @return the {@code DataSource} for managing the connection pool to the database in readwrite mode.
     */
    protected final DataSource getDataSourceW() {
        return dsw;
    }
}
