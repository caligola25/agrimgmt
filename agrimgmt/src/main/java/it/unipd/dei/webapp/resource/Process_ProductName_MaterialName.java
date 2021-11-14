package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represents Process entity of the database (with product name and material name in place of product id and material id).
 * @author --
 * @version 1.00
 * @since 1.00
 */
public class Process_ProductName_MaterialName {

    private final UUID processId;

    private final String name;

    private final int sequenceNumber;

    private final String estimatedTime;

    private final String productName;

    private final String materialName;

    private final int quantity;

    /**
     * Create an object representing a process (with product name and material name in place of product id and material id)
     *
     * @param processId the UUID of the process
     * @param name the name of the process
     * @param sequenceNumber the sequence number of the process
     * @param estimatedTime the estimated time to complete the process
     * @param productName the product name of the product which the process belongs to
     * @param materialName the material name of the material necessary to perform the process
     * @param quantity the quantity of the material needed to carry out the process
     */
    public Process_ProductName_MaterialName(final UUID processId, final String name, final int sequenceNumber,
                                            final String estimatedTime, final String productName, final String materialName,
                                            final int quantity){
        this.processId = processId;
        this.name = name;
        this.sequenceNumber = sequenceNumber;
        this.estimatedTime = estimatedTime;
        this.productName = productName;
        this.materialName = materialName;
        this.quantity = quantity;
    }

    /**
     * @return the UUID of the process
     */
    public final UUID getProcessId(){
        return processId;
    }

    /**
     * @return the name of the process
     */
    public final String getName(){
        return name;
    }

    /**
     * @return the sequence number of the process
     */
    public final int getSequenceNumber(){
        return sequenceNumber;
    }

    /**
     * @return the estimated time number to complete the process
     */
    public final String getEstimatedTime(){
        return estimatedTime;
    }

    /**
     * @return the product name of the product which the process belongs to
     */
    public final String getProductName(){
        return productName;
    }

    /**
     * @return the material name of the material necessary to perform the process
     */
    public final String getMaterialName(){
        return materialName;
    }

    /**
     * @return the quantity of the material needed to carry out the process
     */
    public final int getQuantity(){
        return quantity;
    }
}
