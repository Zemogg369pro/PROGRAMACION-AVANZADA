package view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

/**
 * A custom text field with rounded corners and focus effects.
 */
public class RoundedTextField extends JTextField {
    private static final int ARC_WIDTH = 10;
    private static final int ARC_HEIGHT = 10;
    
    private Color backgroundColor = new Color(240, 248, 255); // Alice Blue
    private Color borderColor = new Color(70, 130, 180);      // Steel Blue
    private Color focusBorderColor = new Color(100, 149, 237); // Cornflower Blue
    private boolean isFocused = false;
    
    /**
     * Creates a new rounded text field.
     */
    public RoundedTextField() {
        this("");
    }
    
    /**
     * Creates a new rounded text field with the specified text.
     * 
     * @param text The initial text
     */
    public RoundedTextField(String text) {
        this(text, 0);
    }
    
    /**
     * Creates a new rounded text field with the specified text and columns.
     * 
     * @param text The initial text
     * @param columns The number of columns
     */
    public RoundedTextField(String text, int columns) {
        super(text, columns);
        setup();
    }
    
    /**
     * Sets up the text field appearance and behavior.
     */
    private void setup() {
        setOpaque(false);
        setBorder(new EmptyBorder(8, 15, 8, 15));
        setFont(new Font("Tahoma", Font.PLAIN, 14));
        setBackground(backgroundColor);
        
        // Add focus listener for focus effects
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isFocused = true;
                repaint();
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                isFocused = false;
                repaint();
            }
        });
    }
    
    /**
     * Sets the colors for the text field.
     * 
     * @param backgroundColor The background color
     * @param borderColor The border color
     * @param focusBorderColor The border color when focused
     */
    public void setColors(Color backgroundColor, Color borderColor, Color focusBorderColor) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.focusBorderColor = focusBorderColor;
        setBackground(backgroundColor);
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fill the rounded rectangle
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT));
        
        g2.dispose();
        
        // Let the original paintComponent handle the text
        super.paintComponent(g);
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw the border with the appropriate color
        g2.setColor(isFocused ? focusBorderColor : borderColor);
        g2.setStroke(new BasicStroke(isFocused ? 2 : 1));
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT));
        
        // Add a subtle glow effect when focused
        if (isFocused) {
            g2.setColor(new Color(focusBorderColor.getRed(), focusBorderColor.getGreen(), focusBorderColor.getBlue(), 50));
            g2.setStroke(new BasicStroke(3));
            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 3, getHeight() - 3, ARC_WIDTH, ARC_HEIGHT));
        }
        
        g2.dispose();
    }
    
    /**
     * Creates a standard text field with the default styling.
     * 
     * @return The created text field
     */
    public static RoundedTextField createTextField() {
        return createTextField("");
    }
    
    /**
     * Creates a standard text field with the default styling and initial text.
     * 
     * @param text The initial text
     * @return The created text field
     */
    public static RoundedTextField createTextField(String text) {
        RoundedTextField textField = new RoundedTextField(text);
        textField.setColors(
            new Color(240, 248, 255), // Alice Blue
            new Color(70, 130, 180),  // Steel Blue
            new Color(100, 149, 237)  // Cornflower Blue
        );
        return textField;
    }
    
    /**
     * Creates a standard text field with the default styling, initial text, and columns.
     * 
     * @param text The initial text
     * @param columns The number of columns
     * @return The created text field
     */
    public static RoundedTextField createTextField(String text, int columns) {
        RoundedTextField textField = new RoundedTextField(text, columns);
        textField.setColors(
            new Color(240, 248, 255), // Alice Blue
            new Color(70, 130, 180),  // Steel Blue
            new Color(100, 149, 237)  // Cornflower Blue
        );
        return textField;
    }
}