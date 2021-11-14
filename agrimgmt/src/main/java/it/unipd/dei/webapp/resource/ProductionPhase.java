package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represents the entity Production Phase of the database.
 */
public class ProductionPhase {

    private final UUID prodPhaseId;

    private final UUID itemId;

    private final UUID processId;

    private final UUID employeeId;

    private final String status;

    private final String actualTime;

    /**
     * Create an object representing a production phase
     *
     * @param prodPhaseId the UUID of the production phase
     * @param itemId the UUID of item associated with the production phase
     * @param processId the UUID of the process associated with the production phase
     * @param employeeId the UUID of the employee associated to the production phase
     * @param status the status of the production phase
     * @param actualTime the time taken by the production phase
     */
    public ProductionPhase(final UUID prodPhaseId, final UUID itemId, final UUID processId, final UUID employeeId,
                           final String status, final String actualTime){
        this.prodPhaseId = prodPhaseId;
        this.itemId = itemId;
        this.processId = processId;
        this.employeeId = employeeId;
        this.status = status;
        this.actualTime = actualTime;
    }

    /**
     * @return the UUID of the production phase
     */
    public final UUID getProdPhaseId(){
        return prodPhaseId;
    }

    /**
     * @return the UUID of the item associated with the production phase
     */
    public final UUID getItemId(){
        return itemId;
    }

    /**
     * @return the UUID of the process associated with the production phase
     */
    public final UUID getProcessId(){
        return processId;
    }

    /**
     * @return the UUID of the employee associated with the production phase
     */
    public final UUID getEmployeeId(){
        return employeeId;
    }

    /**
     * @return the status of the production phase
     */
    public final String getStatus(){
        return status;
    }

    /**
     * @return the time taken by the production phase
     */
    public final String getActualTime(){
        return actualTime;
    }
}
