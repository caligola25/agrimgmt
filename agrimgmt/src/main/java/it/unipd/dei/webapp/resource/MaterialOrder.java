package it.unipd.dei.webapp.resource;

import java.sql.Date;
import java.util.UUID;

/**
 * Represents data about a material order.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class MaterialOrder {

    private final UUID material_order_id;
    private final float price;
    private final Date material_order_date;
    private String order_status;
    private final Date report_date;

    /**
     * Creates a new MaterialOrder
     * @param material_order_id the UUID of the material order
     * @param price the price of the material order
     * @param material_order_date the date of the material order
     * @param order_status the status of the material order
     * @param report_date the report date of the material order
     */
    public MaterialOrder(final UUID material_order_id, final float price, final Date material_order_date, final String order_status, final Date report_date ){
        this.material_order_id=material_order_id;
        this.price=price;
        this.material_order_date=material_order_date;
        this.order_status=order_status;
        this.report_date=report_date;
    }

    /**
     * @return the UUID of the material order
     */
    public UUID getMaterialOrderId() {
        return material_order_id;
    }

    /**
     * @return the price of the material order
     */
    public float getPrice() {return price; }

    /**
     * @return the date of the material order
     */
    public Date getMaterialOrderDate() { return material_order_date; }

    /**
     * @return the status of the material order
     */
    public String getOrderStatus() { return order_status; }

    /**
     * @return the report date of the material order
     */
    public Date getReportDate () { return report_date; }
}
