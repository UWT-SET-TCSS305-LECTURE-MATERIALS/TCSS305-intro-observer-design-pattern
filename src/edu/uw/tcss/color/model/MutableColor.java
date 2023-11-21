package edu.uw.tcss.color.model;

import java.awt.Color;

/**
 * Defines mutable behaviors for a Color with RGB values. 
 * 
 * @author Charles Bryan
 * @version 1
 */
public interface MutableColor {

    /**
     * Returns the red component in the range 0-255 in the default RGB space.
     *  
     * @return the red component
     */
    int getRed();
    
    /**
     * Sets Red component with the specified value in the range (0 - 255).
     * 
     * @param theRed the red component
     * @throws IllegalArgumentException if theRed is 
     *          outside of the range 0 to 255, inclusive
     */
    void setRed(int theRed);
    
    /**
     * Adjust the Red component by the specified positive or negative offset value.
     * 
     * @param theOffSet the value to offset the red value, may be positive or negative
     * @throws IllegalArgumentException if the red value is adjusted
     *          outside of the range 0 to 255, inclusive
     */
    void adjustRed(int theOffSet);
    
    /**
     * Returns the green component in the range 0-255 in the default RGB space.
     *  
     * @return the green component
     */
    int getGreen();
    
    /**
     * Sets Green component with the specified value in the range (0 - 255).
     * 
     * @param theGreen the green component
     * @throws IllegalArgumentException if theGreen is 
     *          outside of the range 0 to 255, inclusive
     */
    void setGreen(int theGreen);
    
    /**
     * Adjust the Green component by the specified positive or negative offset value.
     * 
     * @param theOffSet the value to offset the green value, may be positive or negative
     * @throws IllegalArgumentException if the green value is adjusted
     *          outside of the range 0 to 255, inclusive
     */
    void adjustGreen(int theOffSet);
    
    /**
     * Returns the Blue component in the range 0-255 in the default RGB space.
     *  
     * @return the blue component
     */
    int getBlue();
    
    /**
     * Sets Blue component with the specified value in the range (0 - 255).
     * 
     * @param theBlue the blue component
     * @throws IllegalArgumentException if theBlue is 
     *          outside of the range 0 to 255, inclusive
     */
    void setBlue(int theBlue);
    
    /**
     * Adjust the Blue component by the specified positive or negative offset value.
     * 
     * @param theOffSet the value to offset the blue value, may be positive or negative
     * @throws IllegalArgumentException if the blue value is adjusted
     *          outside of the range 0 to 255, inclusive
     */
    void adjustBlue(int theOffSet);

    /**
     * Gets an instance of the java.awt.Color object based on this Color's
     * component colors.
     * @return the java.awt.Color object
     */
    Color getColor();

    /**
     * Returns a hexadecimal representation of this Color's component values.
     *
     * @return the hexadecimal representation
     */
    String getHexCode();
}
