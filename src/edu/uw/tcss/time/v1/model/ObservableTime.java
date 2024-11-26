package edu.uw.tcss.time.v1.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents an observable time object. 
 * @author Charles Bryan
 * @version Winter 2018
 */
public class ObservableTime implements PropertyChangeEnabledTimeControls {

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
     * Construct an Observable time object with a start time of 0.  
     */
    public ObservableTime() {
        this(DEFAULT_START_TIME);
    }
    
    /**
     * Construct an Observable time object with a specific start time. 
     * @param theStartTime the start time for this object
     * @throws IllegalArgumentException if theStartTime is < 0
     */
    public ObservableTime(final int theStartTime) {
        super();
        if (theStartTime < 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        myTime = theStartTime;
        myPcs = new PropertyChangeSupport(this);
    }

    @Override
    public void advance() {
        advance(1);
        
    }

    @Override
    public void advance(final int theMillisecond) {
        if (theMillisecond < 0) {
            throw new IllegalArgumentException("theMillisecond must be positive.");
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
    
    @Override
    public void adjustTime(final int theDelta) {
        if (myTime + theDelta < 0) {
            changeTime(0);
        } else {
            changeTime(myTime + theDelta);
        }
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
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
        
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
        
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
        
    }
}
