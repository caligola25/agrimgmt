package it.unipd.dei.webapp.servlet.accountant;

import it.unipd.dei.webapp.database.accountant.ReportManagementDatabase;
import it.unipd.dei.webapp.resource.Message;

import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Class in charge of the downloading process of the monthly report
 */
public final class DownloadReportServlet extends AbstractDatabaseServlet {

    /**
     * This method collect the date of the report and send back the corresponding PDF file to the user
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Message> m = new ArrayList<>();
        Date date = null;
        byte[] file = null;

        OutputStream outStream = res.getOutputStream();

        try {
            date = Date.valueOf(req.getParameter("selDate"));
            file = new ReportManagementDatabase(getDataSourceR().getConnection(), date).searchReportByDate().getDocumentFile();

            outStream.write(file);
            outStream.close();

            m.add(new Message("Data retrieved correctly"));
        } catch (SQLException ex) {
            m.add(new Message("Cannot complete the insertion. Unexpected error while accessing the database", "E200", ex.getMessage()));
        }

        //SEND DATA TO JSP
        //res.setContentLength(file.length);
        res.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=Report_%s.pdf", date.toString());
        res.setHeader(headerKey, headerValue);

        req.setAttribute("messageList", m);

        req.getRequestDispatcher("/protected/jsp/accountant/monthly-report-management.jsp").forward(req, res);
    }
}
