package edu.uw.tcss.color.view;

import static edu.uw.tcss.color.model.PropertyChangeEnabledMutableColor.PROPERTY_COLOR;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import javax.swing.JPanel;

/**
 * A demo of Observer design pattern implemented with PropertyChange API. 
 * 
 * @author Charles Bryan
 * @version Autumn 2015
 */
public final class ColorPanel extends JPanel implements PropertyChangeListener {

    /**
     * A generated serial version UID for object Serialization. 
     * <a href="http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">...</a>
     */
    @Serial
    private static final long serialVersionUID = 8385732728740430466L;
    
    /**
     * Create a color panel with the supplied color.
     * @param theColor the color for the background
     */
    public ColorPanel(final Color theColor) {
        super();
        setBackground(theColor);
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        Color c = getBackground();
        if (PROPERTY_COLOR.equals(theEvent.getPropertyName())) {
            c = (Color) theEvent.getNewValue();
        } 
        setBackground(c);
    }
}
