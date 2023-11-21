package edu.uw.tcss.time.v1.model;

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
     * Advances the time by n (positive) milliseconds where n is the value of
     * theMillsecond.  All registered observers will be notified of the "time" change. 
     * 
     * @param theMillisecond the amount of milliseconds to advance the time
     * @throws IllegalArgumentException when theMillisecond is negative
     */
    void advance(int theMillisecond);
    
    /**
     * Move the time's internal "clock" to n milliseconds where n is the value of 
     * theMillisecond. All registered observers will be notified of the "time" change. 
     * 
     * @param theMillisecond the time to move the time to
     * @throws IllegalArgumentException when theMillisecond is negative
     */
    void moveTo(int theMillisecond);
    
    /**
     * Adjusts the time's internal "clock" by delta milliseconds where delta is the value of 
     * theDelta. All registered observers will be notified of the "time" change. When theDelta 
     * is negative, the time's internal clock is moved "backward". When a negative theDelta is 
     * greater than the current time, the time is set to Zero instead of throwing an 
     * IllegalArgumentException.
     * 
     * @param theDelta the amount to adjust the time by
     */
    void adjustTime(int theDelta);
    
}
