package it.unipd.dei.webapp.resource;

import java.sql.Date;
import java.util.UUID;

/**
 * Represents the entity Product Order of the database.
 */
public class ProductOrder {

    private final UUID prodOrderId;

    private final String status;

    private final float price;

    private final Date date;

    private final UUID customerId;

    private final Date reportDate;

    /**
     *  Create an object representing a production order
     *
     * @param prodOrderId UUID of the production order
     * @param status status of the production order
     * @param price price of the production order
     * @param date date of the production order
     * @param customerId UUID of the customer associated with the production order
     * @param reportDate date of the report which includes this production order
     */
    public ProductOrder(final UUID prodOrderId, final String status, final float price, final Date date, final UUID customerId, final Date reportDate){
        this.prodOrderId = prodOrderId;
        this.status = status;
        this.price = price;
        this.date = date;
        this.customerId = customerId;
        this.reportDate = reportDate;
    }

    /**
     * @return the UUID of the production order
     */
    public final UUID getProdOrderId(){
        return prodOrderId;
    }

    /**
     * @return the status of the production order
     */
    public final String getStatus(){
        return status;
    }

    /**
     * @return the price of the production order
     */
    public final float getPrice(){
        return price;
    }

    /**
     * @return the date of the production order
     */
    public final Date getDate(){
        return date;
    }

    /**
     * @return the UUID of the customer associated with this production order
     */
    public final UUID getCustomerId() {
        return customerId;
    }

    /**
     * @return the date of the report which includes this production order
     */
    public final Date getReportDate() {
        return reportDate;
    }
}
