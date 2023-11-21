package edu.uw.tcss.time.v2.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents an observable clock object that keeps time. 
 * @author Charles Bryan
 * @version Winter 2018
 */
public class Clock implements PropertyChangeEnabledTimeControls {

    /** The default starting time. */
    public static final int DEFAULT_START_TIME = 0;
    
    /** An error message for illegal arguments. */
    private static final String ERROR_MESSAGE = "Time may not be less than 0.";
    
    /** Stores this objects time. */
    private int myTime;
    
    /**
     * Manager for Property Change Listeners. 
     */
    private final PropertyChangeSupport myPcs;
        
    /**
     * Construct an Observable Clock object with a start time of 0.  
     */
    public Clock() {
        this(DEFAULT_START_TIME);
    }
    
    /**
     * Construct an Observable Clock object with a specific start time. 
     * @param theStartTime the start time for this object
     * @throws IllegalArgumentException if theStartTime is < 0
     */
    public Clock(final int theStartTime) {
        super();
        if (theStartTime < 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        myPcs = new PropertyChangeSupport(this);
        myTime = theStartTime;
    }

    @Override
    public void advance() {
        advance(1);
        
    }

    @Override
    public void advance(final int theMillisecond) {
        if (theMillisecond < 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        changeTime(myTime + theMillisecond);
    }

    @Override
    public void moveTo(final int theMillisecond) {
        if (theMillisecond < 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        changeTime(theMillisecond);
    }
    
    /**
     * Helper method to change the value of time and notify observers. 
     * Functional decomposition. 
     * @param theMillisecond the time to change to
     */
    private void changeTime(final int theMillisecond) {
        final int old = myTime;
        myTime = theMillisecond;
        myPcs.firePropertyChange(PROPERTY_TIME, old, myTime);
        
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }
    

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }
    
    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
        
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
        
    }
}
