package it.unipd.dei.webapp.resource;
import java.sql.Date;

/**
 * Represents the data about the fixed cost.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class FixedCost {
    private final String type;
    private final double price;
    private final Date date;
    private final Date report_date;

    /**
     * @param date Date of the fixed cost
     * @param price Price of the fixed cost
     * @param type Type of the fixed cost
     * @param report_date Date of the report to which the fixed cost is linked
     */
    public FixedCost(final Date date, final double price, final String type, final Date report_date) {
        this.type = type;
        this.price = price;
        this.date = date;
        this.report_date = report_date;
    }

    /**
     * @return the fixed cost's type
     */
    public final String getType() { return type; }

    /**
     * @return the fixed cost's price
     */
    public final double getPrice() { return price; }

    /**
     * @return the fixed cost's date
     */
    public final Date getDate() { return date; }

    /**
     * @return the date of the report linked to the fixed cost
     */
    public final Date getReport_date() { return report_date; }
}