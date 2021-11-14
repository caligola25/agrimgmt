package it.unipd.dei.webapp.filter;

import it.unipd.dei.webapp.resource.Employee;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Check if the employee is an warehouse worker or an administrator and let that the employee uses the resocurces
 */
public class WarehouseWorkerFilter implements Filter {
    private FilterConfig config = null;
    private Employee employee=null;

    /**
     * When container initializes the Filter, this is the method that gets invoked.
     * This method is called only once in the lifecycle of filter.
     *
     * @param config used by container to provide init parameters and it.unipd.dei.webapp.servlet context object to the Filter
     */
    @Override
    public void init(final FilterConfig config) throws ServletException {
        if (config == null) {
            throw new ServletException("Filter configuration cannot be null.");
        }
        this.config = config;
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

        if (session != null) {
            employee = (Employee) session.getAttribute("employee");
            if (employee == null) {
                session.invalidate();
                res.sendRedirect(req.getContextPath() + "/protected/jsp/not-authorized.jsp");
            } else if(employee.getRole().equals("Warehouse worker")||employee.getRole().equals("Administrator")){
                chain.doFilter(servletRequest, servletResponse);
            } else {
                res.sendRedirect(req.getContextPath() + "/protected/jsp/not-authorized.jsp");
            }
        }
    }
    /**This is the method where we can close any resources opened by filter.
     * This method is called only once in the lifetime of filter.
     */
    @Override
    public void destroy() {
        config = null;
    }

}

