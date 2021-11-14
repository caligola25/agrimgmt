package it.unipd.dei.webapp.resource;

import java.sql.Date;

/**
 * Represents the data about the report.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class Report {
    private final Date date;
    private final byte[] documentFile;

    /**
     * @param date Date of the report
     * @param documentFile byte[] file containing the monthly report produced by the company's accountant
     */
    public Report(final Date date, final byte[] documentFile) {
        this.date = date;
        this.documentFile = documentFile;
    }

    /**
     * @return the report's date
     */
    public final Date getDate() { return date; }

    /**
     * @return the report file produced by the accountant as a byte[] data type
     */
    public final byte[] getDocumentFile() { return documentFile; }
}
