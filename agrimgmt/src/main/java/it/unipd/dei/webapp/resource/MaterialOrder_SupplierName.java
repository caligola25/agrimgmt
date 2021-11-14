package it.unipd.dei.webapp.resource;

import java.sql.Date;

/**
 * Represents the data about the report.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class MaterialOrder_SupplierName {
    private final String supplierName;
    private final Date date;
    private final float price;

    /**
     * @param supplierName Name of the supplier that provided the considered raw material
     *                     (linked to the relative material order)
     * @param date Date on which the material order was executed
     * @param price Cumulative price of the material order
     */
    public MaterialOrder_SupplierName(final String supplierName, final Date date, final float price) {
        this.supplierName = supplierName;
        this.date = date;
        this.price = price;
    }

    /**
     * @return the supplier of the material order
     */
    public final String getSupplierName(){ return supplierName; }

    /**
     * @return the material order's date
     */
    public final Date getDate(){
        return date;
    }

    /**
     * @return the material order's price
     */
    public final float getPrice(){
        return price;
    }
}
