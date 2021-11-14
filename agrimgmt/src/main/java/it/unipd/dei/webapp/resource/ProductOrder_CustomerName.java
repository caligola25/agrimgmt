package it.unipd.dei.webapp.resource;

import java.sql.Date;

/**
 * Represents the data about the report.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class ProductOrder_CustomerName {
    private final String customerName;
    private final Date date;
    private final float price;

    /**
     * @param customerName Name of the customer that made the product order
     * @param date Date on which the product order was executed
     * @param price Cumulative price of the product order
     */
    public ProductOrder_CustomerName(final String customerName, final Date date, final float price) {
        this.customerName = customerName;
        this.date = date;
        this.price = price;
    }

    /**
     * @return the name of the customer that made the product order
     */
    public final String getCustomerName(){
        return customerName;
    }

    /**
     * @return the product order's date
     */
    public final Date getDate(){
        return date;
    }

    /**
     * @return the product order's price
     */
    public final float getPrice(){
        return price;
    }
}