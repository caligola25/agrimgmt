package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represents Process entity of the database.
 */
public class Process {

    private final UUID processId;

    private final String name;

    private final int sequenceNumber;

    private final String estimatedTime;

    private final UUID productId;

    private final UUID materialId;

    private final int quantity;

    /**
     * Creates an object representing a process
     *
     * @param processId the UUID of the process
     * @param name the name of the process
     * @param sequenceNumber the sequence number of the process
     * @param estimatedTime the estimated time to complete the process
     * @param productId the product id of the product which the process belongs to
     * @param materialId the material id of the material necessary to perform the process
     * @param quantity the quantity of the material needed to carry out the process
     */
    public Process(final UUID processId, final String name, final int sequenceNumber,
                   final String estimatedTime, final UUID productId, final UUID materialId,
                   final int quantity){
        this.processId = processId;
        this.name = name;
        this.sequenceNumber = sequenceNumber;
        this.estimatedTime = estimatedTime;
        this.productId = productId;
        this.materialId = materialId;
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
     * @return the product id of the product which the process belongs to
     */
    public final UUID getProductId(){
        return productId;
    }

    /**
     * @return the material id of the material necessary to perform the process
     */
    public final UUID getMaterialId(){
        return materialId;
    }

    /**
     * @return the quantity of the material needed to carry out the process
     */
    public final int getQuantity(){
        return quantity;
    }
}
