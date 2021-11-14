package it.unipd.dei.webapp.resource;

/**
 * Represents the data about the time difference.
 *
 * @author ---
 * @version 1.00
 * @since 1.00
 */
public class TimeDifference {
    private final String processName;
    private final String estimatedTime;
    private final String averageActualTime;
    private final String timeDifference;

    /**
     * @param processName Name of the process
     * @param estimatedTime Estimated time to complete the process
     * @param averageActualTime Time actually needed to complete the process
     *                          (computed as the average of all the production phases that corresponds to the process)
     * @param timeDifference Difference from the estimated time and the actual time needed to complete the process
     */
    public TimeDifference(final String processName, final String estimatedTime, final String averageActualTime, final String timeDifference) {
        this.processName = processName;
        this.estimatedTime = estimatedTime;
        this.averageActualTime = averageActualTime;
        this.timeDifference = timeDifference;
    }

    /**
     * Return the process name as a String
     *
     * @return the process name
     */
    public final String getProcessName(){
        return processName;
    }

    /**
     * Return the estimated time as a String
     *
     * @return the estimated time of the process
     */
    public final String getEstimatedTime(){
        return estimatedTime;
    }

    /**
     * Return the average time as a String
     *
     * @return the (average) actual time needed to complete the process
     */
    public final String getAverageActualTime(){
        return averageActualTime;
    }

    /**
     * Return the time difference as a String
     *
     * @return the time difference between the estimated and the actual time
     */
    public final String getTimeDifference(){
        return timeDifference;
    }
}
