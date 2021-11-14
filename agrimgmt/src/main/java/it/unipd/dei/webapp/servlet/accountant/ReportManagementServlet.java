package it.unipd.dei.webapp.servlet.accountant;

import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Report;

import it.unipd.dei.webapp.database.accountant.ReportManagementDatabase;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import it.unipd.dei.webapp.servlet.AbstractDatabaseServlet;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Class in charge of retrieving all the information needed to build the report management page
 */
public final class ReportManagementServlet extends AbstractDatabaseServlet {

    /**
     * This method retrieves all the information needed to populate the report management page:
     * <ul>
     *     <li>All the years that have a report linked</li>
     *     <li>All the report produced in the year selected (the year is collected from res)</li>
     * </ul>
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Message> m = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        List<Report> reportList = null;
        List<Report> requestedReports = null;
        int selectedYear = 0;

        //FIND THE YEARS THAT HAVE A REPORT
        try {
            reportList = new ReportManagementDatabase(getDataSourceR().getConnection()).viewReport();

            ListIterator itr = reportList.listIterator();
            while(itr.hasNext()) {
                Report tmp = (Report)itr.next();
                int year = Integer.parseInt(tmp.getDate().toString().split("-")[0]); // extract the year from the reportDate
                if (!years.contains(year)) { // add the year to the years list only if not already present
                    years.add(year);
                }
            }

            m.add(new Message("YEAR RESEARCH - Possible year correctly retrieved"));
        } catch (SQLException ex) {
            m.add(new Message("YEAR RESEARCH - Cannot complete the operation. Unexpected error while accessing the database", "E200", ex.getMessage()));
        }

        //FIND ALL THE REPORT OF A SELECTED YEAR
        try {
            //retrieve the selected year from the request
            selectedYear = Integer.parseInt(req.getParameter("selectYear"));

            requestedReports = new ReportManagementDatabase(getDataSourceR().getConnection(), selectedYear).searchReportByYear();
        } catch (SQLException ex) {
            m.add(new Message("REPORT RESEARCH - Cannot complete the operation. Unexpected error while accessing the database", "E200", ex.getMessage()));
        } catch (NumberFormatException ex) {
            selectedYear = 0;
        }

        //SEND DATA TO JSP
        req.setAttribute("posYears", years);
        req.setAttribute("selectedYear", selectedYear);
        req.setAttribute("reportList", requestedReports);
        req.setAttribute("messageList", m);

        req.getRequestDispatcher("/protected/jsp/accountant/monthly-report-management.jsp").forward(req, res);
    }

    /**
     * This method is responsible for the upload of the Report in the system
     * <ul>
     *     <li>
     *         Collects the PDF file uploaded by the user and convert it in a byte[] data type
     *     </li>
     *     <li>
     *         Inserts the obtained element in the database
     *     </li>
     *     <li>
     *         Retrieve all the years that have a report linked
     *     </li>
     * </ul>
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException if any error occurs while executing the it.unipd.dei.webapp.servlet.
     * @throws IOException if any error occurs in the client/server communication.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Date date = null;
        byte[] reportFile = null;
        boolean isEmpty = true;
        boolean correctFileFormat = false;

        Report report = null;
        Message m = null;

        List<Integer> years = new ArrayList<>();
        List<Report> reportList = null;

        //UPLOAD PROCESS
        try{
            //RETRIEVE THE FILE FROM THE REQUEST

            //create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            //create a new file uploader handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            //parse the request
            List<FileItem> items = upload.parseRequest(req);


            //PROCESS THE UPLOADED ITEMS

            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                //process a normal form field
                if (item.isFormField()) {
                    date = Date.valueOf(item.getString());
                } else { //process a file upload
                    String contentType = item.getContentType();
                    reportFile = item.get();

                    if (contentType.equals("application/octet-stream")) {
                        m = new Message("Cannot complete the insertion. You doesn't upload any file", "E600", "No file data");
                    } else if (!contentType.equals("application/octet-stream") && !contentType.equals("application/pdf")) {
                        m = new Message("Cannot complete the insertion. Unsupported file type (you can only upload .pdf file)", "E600", "Incorrect file extension");
                    } else if (contentType.equals("application/pdf")) {
                        correctFileFormat = true;
                        isEmpty = false;
                    }
                }
            }


            //DATABASE INSERTION
            report = new Report(date, reportFile);

            if (!isEmpty && correctFileFormat) {
                new ReportManagementDatabase(getDataSourceW().getConnection(), report).addReport();

                m = new Message(String.format("Successfully inserted in the system the report with date %s", date.toString()));
            }
        } catch (IllegalArgumentException ex) {
            m = new Message("Cannot complete the insertion. Invalid input parameters: the date has to be inserted as (yyyy-mm-dd)", "E100", ex.getMessage());
        } catch (FileUploadException ex) {
            m = new Message("Cannot complete the insertion. Unexpected error while uploading the file", "E600", ex.getMessage());
        } catch (SQLException ex) {
            switch (ex.getSQLState()) {
                case "23502":
                    m = new Message("Cannot complete the insertion. It is necessary to insert a date", "E500", ex.getMessage());
                    break;
                case "23505":
                    m = new Message(String.format("Cannot complete the insertion. A report with date %s already exist.", date.toString()), "E300", ex.getMessage());
                    break;
                default:
                    m = new Message("Cannot complete the insertion. Unexpected error while accessing the database", "E200", ex.getMessage());
                    break;
            }
        }

        //FIND THE YEAR THAT HAS A REPORT
        try {
            reportList = new ReportManagementDatabase(getDataSourceR().getConnection()).viewReport();

            ListIterator itr = reportList.listIterator();
            while(itr.hasNext()) {
                Report tmp = (Report)itr.next();
                int year = Integer.parseInt(tmp.getDate().toString().split("-")[0]); // extract the year from the reportDate
                if (!years.contains(year)) { // add the year to the years list only if not already present
                    years.add(year);
                }
            }
        } catch (SQLException ex) {
            m = new Message("YEAR RESEARCH - Cannot complete the operation. Unexpected error while accessing the database", "E200", ex.getMessage());
        }


        //SEND DATA TO JSP
        req.setAttribute("message", m);
        req.setAttribute("posYears", years);

        req.getRequestDispatcher("/protected/jsp/accountant/monthly-report-management.jsp").forward(req, res);
    }

}