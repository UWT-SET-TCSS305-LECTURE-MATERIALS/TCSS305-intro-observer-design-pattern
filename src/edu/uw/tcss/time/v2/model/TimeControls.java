package edu.uw.tcss.time.v2.model;

/**
 * Define the actions that may be performed on a Timed object. 
 * 
 * @author Charles Bryan
 * @version 1
 */
public interface TimeControls {

    /**
     * Advances the time by 1 millisecond. All registered observers 
     * will be notified of the "time" change 
     */
    void advance();
    
    /**
     * Advances the time by n milliseconds where n is the value of
     * theMillsecond.  All registered observers will be notified of the "time" change. 
     * 
     * @param theMillisecond the amount of milliseconds to advance the time
     * @throws IllegalArgumentException when theMillisecond is negative
     */
    void advance(int theMillisecond);
    
    /**
     * Move the times internal "clock" to n milliseconds where n is the value of 
     * theMillisecond. All registered observers will be notified of the "time" change. 
     * 
     * @param theMillisecond the time to move the time to
     * @throws IllegalArgumentException when theMillisecond is negative
     */
    void moveTo(int theMillisecond);
    
}
