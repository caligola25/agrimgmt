package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represents the data about the supplying.
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class Supplying {
    private final UUID supplying_id;
    private final UUID material_id;
    private final int quantity;
    private final UUID material_order_id;
    private final String supplier_name;

    /**
     * Creates a new Supplying
     * @param supplying_id the UUID of the supplying
     * @param material_id the UUID of the raw material associated with this supplying
     * @param quantity the quantity of the supplying
     * @param material_order_id the UUID of the material order associated with this supplying
     * @param supplier_name the name of the supplier associated with this supplying
     */
    public Supplying(final UUID supplying_id, final UUID material_id, final int quantity, final UUID material_order_id, final String supplier_name){
        this.supplying_id=supplying_id;
        this.material_id=material_id;
        this.quantity=quantity;
        this.material_order_id=material_order_id;
        this.supplier_name=supplier_name;
    }

    /**
     * @return the UUID of the supplying
     */
    public UUID getSupplyingId() {
        return supplying_id;
    }

    /**
     * @return the UUID od the raw material associated with this supplying
     */
    public UUID getMaterialId() {
        return material_id;
    }

    /**
     * @return the quantity of the supplying
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the UUID od the material order associated with this supplying
     */
    public UUID getMaterialIdOrder() {
        return material_order_id;
    }

    /**
     * @return the name od the supplier associated with this supplying
     */
    public String getSupplierName() { return supplier_name; }
}
