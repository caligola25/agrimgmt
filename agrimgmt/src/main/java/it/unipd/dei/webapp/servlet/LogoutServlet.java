package it.unipd.dei.webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Does the logout of the emplpoyee

 * @version 1.00
 * @since 1.00
 */
public final class LogoutServlet extends AbstractDatabaseServlet{

    /**
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.setAttribute("employee",null);
            session.invalidate();
        }

        res.sendRedirect(req.getContextPath() + "/jsp/homepage.jsp");
    }
}
