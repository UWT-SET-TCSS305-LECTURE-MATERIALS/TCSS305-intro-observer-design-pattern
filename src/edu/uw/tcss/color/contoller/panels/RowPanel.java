package edu.uw.tcss.color.contoller.panels;

import edu.uw.tcss.color.model.ColorModel;
import edu.uw.tcss.color.model.PropertyChangeEnabledMutableColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;


/**
 * Represents a Panel with components used to change and display the Blue value for the 
 * backing Color model.
 *
 * @author Charles Bryan
 * @author Your Name
 * @version Autumn 2019
 */
public class RowPanel extends JPanel implements PropertyChangeListener {

    /**
     * A generated serial version UID for object Serialization. 
     * <a href="http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">...</a>
     */
    @Serial
    private static final long serialVersionUID = 2284116355218892348L;
    
    /** The size of the increase/decrease buttons. */
    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
    
    /** The size of the text label. */
    private static final Dimension LABEL_SIZE = new Dimension(45, 26);
    
    /** The number of columns in width of the TextField. */
    private static final int TEXT_FIELD_COLUMNS = 3;
    
    /** The amount of padding for the change panel. */
    private static final int HORIZONTAL_PADDING = 30;
    
    /** The backing model for the system. */
    private final PropertyChangeEnabledMutableColor myColor;

    /** The CheckBox that enables/disables editing of the TextField. */
    private final JCheckBox myEnableEditButton;
    
    /** The TextField that allows the user to type input for the color value. */
    private final JTextField myValueField;
    
    /** The Button that when clicked increases the color value. */
    private final JButton myIncreaseButton;
    
    /** The Button that when clicked decreases the color value. */
    private final JButton myDecreaseButton;
    
    /** The Slider that when adjusted, changes the color value. */
    private final JSlider myValueSlider;
    
    /** The panel that visually displays ONLY the BLUE value for the color. */
    private final JPanel myColorDisplayPanel;
    
    
    private final Consumer<Integer> mySetter;
    
    private final Consumer<Integer> myAdjuster;
    
    private final Supplier<Integer> myGetter;
    
    private final Supplier<Color> myColorSupplier;
    
    private final Predicate<Object> myEqualityCheck;
    
    private final String myLabel;
    
    /**
     * Creates a Panel with components used to change and display the Blue value for the 
     * backing Color model. 
     * @param theColor the backing model for the system
     */
    public RowPanel(final PropertyChangeEnabledMutableColor theColor,
                    final String theLabel,
                    final Consumer<Integer> theSetter,
                    final Consumer<Integer> theAdjuster,
                    final Supplier<Integer> theGetter,
                    final Supplier<Color> theColorSupplier,
                    final Predicate<Object> theEqualityCheck) {
        super();
        myColor = theColor;
        myEnableEditButton = new JCheckBox("Enable edit");
        myValueField = new JTextField();
        myIncreaseButton = new JButton();
        myDecreaseButton = new JButton();
        myValueSlider = new JSlider();
        myColorDisplayPanel = new JPanel();
        myLabel = theLabel;
        myGetter = theGetter;
        mySetter = theSetter;
        myAdjuster = theAdjuster;
        myEqualityCheck = theEqualityCheck;
        myColorSupplier = theColorSupplier;
        
        layoutComponents();
        addListeners();
    }
    
    /**
     * Setup and add the GUI components for this panel. 
     */
    private void layoutComponents() {
        myColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        myColorDisplayPanel.setBackground(myColorSupplier.get());
        final JLabel rowLabel = new JLabel(myLabel);
        rowLabel.setPreferredSize(LABEL_SIZE);
        myValueField.setText(String.valueOf(myGetter.get()));
        myValueField.setEditable(false);
        myValueField.setColumns(TEXT_FIELD_COLUMNS);
        myValueField.setHorizontalAlignment(JTextField.RIGHT);
        
        final JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 
                                                             HORIZONTAL_PADDING, 
                                                             0, 
                                                             HORIZONTAL_PADDING));
        rightPanel.setBackground(rightPanel.getBackground().darker());
        myIncreaseButton.setIcon(new ImageIcon("assets/images/ic_increase_value.png"));
        myIncreaseButton.setPreferredSize(BUTTON_SIZE);
        myValueSlider.setMaximum(ColorModel.MAX_VALUE);
        myValueSlider.setMinimum(ColorModel.MIN_VALUE);
        myValueSlider.setValue(myGetter.get());
        myValueSlider.setBackground(rightPanel.getBackground());
        myDecreaseButton.setIcon(new ImageIcon("assets/images/ic_decrease_value.png"));
        myDecreaseButton.setPreferredSize(BUTTON_SIZE);
        rightPanel.add(myDecreaseButton);
        rightPanel.add(myValueSlider);
        rightPanel.add(myIncreaseButton);
        
        add(myColorDisplayPanel);
        add(rowLabel);
        add(myValueField);
        add(myEnableEditButton);
        add(rightPanel);
        checkMaxMin();
    }
    
    /**
     * Add listeners (event handlers) to any GUI components that require handling.  
     */
    private void addListeners() {
        //DO not remove the following statement.
        myColor.addPropertyChangeListener(this);
        myIncreaseButton.addActionListener(theEvent -> {
            myAdjuster.accept(1);
        });
        myDecreaseButton.addActionListener(theEvent -> {
            myAdjuster.accept(-1);
        });
        myValueSlider.addChangeListener(theEvent -> {
            if (myValueSlider.getValueIsAdjusting()) {
                mySetter.accept(myValueSlider.getValue());
            }
        });
        myEnableEditButton.addActionListener(theEvent -> {
            myValueField.setEditable(myEnableEditButton.isSelected());
        });
        myValueField.addActionListener(theEvent -> myValueField.transferFocus());
        myValueField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent theEvent) {
                updateValue();
            }
        });
    }
    
    /**
     * Helper for changes to the TextField. 
     */
    private void updateValue() {
        final String text = myValueField.getText().trim();
        final int number;
        try {
            number = Integer.parseInt(text);
            if (number < ColorModel.MIN_VALUE || number > ColorModel.MAX_VALUE) {
                // disallow numbers outside of [0-255]
                throw new NumberFormatException();
            }
            mySetter.accept(number);
        } catch (final NumberFormatException e) {
            myValueField.setText(String.valueOf(myGetter.get()));
        }
    } 
    
    /**
     * Helper to enable/disable the increase/decrease buttons when appropriate. 
     */
    private void checkMaxMin() {
        myDecreaseButton.setEnabled(myGetter.get() != ColorModel.MIN_VALUE);
        myIncreaseButton.setEnabled(myGetter.get() != ColorModel.MAX_VALUE);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (myEqualityCheck.test(theEvent.getPropertyName())) {
            myValueField.setText(String.valueOf(myGetter.get()));
            myValueSlider.setValue(myGetter.get());
            myColorDisplayPanel.
                setBackground(myColorSupplier.get());
            checkMaxMin();
        }
    }
}
