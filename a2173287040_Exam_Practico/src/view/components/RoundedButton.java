package view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * A custom button with rounded corners and hover effects.
 */
public class RoundedButton extends JButton {
    private static final int ARC_WIDTH = 15;
    private static final int ARC_HEIGHT = 15;
    
    private Color normalColor;
    private Color hoverColor;
    private Color pressedColor;
    private Color borderColor;
    private Color textColor;
    
    private boolean isHovered = false;
    private boolean isPressed = false;
    
    /**
     * Creates a new rounded button with the specified text.
     * 
     * @param text The text to display on the button
     */
    public RoundedButton(String text) {
        this(text, null);
    }
    
    /**
     * Creates a new rounded button with the specified text and icon.
     * 
     * @param text The text to display on the button
     * @param icon The icon to display on the button
     */
    public RoundedButton(String text, Icon icon) {
        super(text, icon);
        
        // Default colors - professional blue theme
        normalColor = new Color(70, 130, 180);  // Steel blue
        hoverColor = new Color(100, 149, 237);  // Cornflower blue
        pressedColor = new Color(51, 98, 140);  // Darker blue
        borderColor = new Color(51, 98, 140);   // Darker blue
        textColor = Color.WHITE;
        
        setup();
    }
    
    /**
     * Sets up the button appearance and behavior.
     */
    private void setup() {
        setForeground(textColor);
        setFont(new Font("Tahoma", Font.BOLD, 14));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        
        // Add some padding
        setMargin(new Insets(8, 15, 8, 15));
        setBorder(new EmptyBorder(8, 15, 8, 15));
        
        // Add mouse listeners for hover and press effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }
    
    /**
     * Sets the color scheme for the button.
     * 
     * @param normalColor The normal background color
     * @param hoverColor The hover background color
     * @param pressedColor The pressed background color
     * @param borderColor The border color
     * @param textColor The text color
     */
    public void setColors(Color normalColor, Color hoverColor, Color pressedColor, Color borderColor, Color textColor) {
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.pressedColor = pressedColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
        setForeground(textColor);
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Determine the current background color based on state
        Color background;
        if (isPressed) {
            background = pressedColor;
        } else if (isHovered) {
            background = hoverColor;
        } else {
            background = normalColor;
        }
        
        // Fill the rounded rectangle
        g2.setColor(background);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT));
        
        // Draw the border
        g2.setColor(borderColor);
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT));
        
        // Add a subtle shadow effect when hovered
        if (isHovered && !isPressed) {
            g2.setColor(new Color(0, 0, 0, 30));
            g2.setStroke(new BasicStroke(3));
            g2.draw(new RoundRectangle2D.Double(2, 2, getWidth() - 5, getHeight() - 5, ARC_WIDTH, ARC_HEIGHT));
        }
        
        g2.dispose();
        
        // Let the original paintComponent handle the text and icon
        super.paintComponent(g);
    }
    
    /**
     * Creates a primary action button (blue).
     * 
     * @param text The text to display on the button
     * @return The created button
     */
    public static RoundedButton createPrimaryButton(String text) {
        return createPrimaryButton(text, null);
    }
    
    /**
     * Creates a primary action button (blue) with an icon.
     * 
     * @param text The text to display on the button
     * @param icon The icon to display on the button
     * @return The created button
     */
    public static RoundedButton createPrimaryButton(String text, Icon icon) {
        RoundedButton button = new RoundedButton(text, icon);
        button.setColors(
            new Color(70, 130, 180),   // Steel blue
            new Color(100, 149, 237),  // Cornflower blue
            new Color(51, 98, 140),    // Darker blue
            new Color(51, 98, 140),    // Darker blue
            Color.WHITE
        );
        return button;
    }
    
    /**
     * Creates a success action button (green).
     * 
     * @param text The text to display on the button
     * @return The created button
     */
    public static RoundedButton createSuccessButton(String text) {
        return createSuccessButton(text, null);
    }
    
    /**
     * Creates a success action button (green) with an icon.
     * 
     * @param text The text to display on the button
     * @param icon The icon to display on the button
     * @return The created button
     */
    public static RoundedButton createSuccessButton(String text, Icon icon) {
        RoundedButton button = new RoundedButton(text, icon);
        button.setColors(
            new Color(46, 139, 87),    // Sea Green
            new Color(60, 179, 113),   // Medium Sea Green
            new Color(35, 120, 75),    // Darker green
            new Color(35, 120, 75),    // Darker green
            Color.WHITE
        );
        return button;
    }
    
    /**
     * Creates a danger action button (red).
     * 
     * @param text The text to display on the button
     * @return The created button
     */
    public static RoundedButton createDangerButton(String text) {
        return createDangerButton(text, null);
    }
    
    /**
     * Creates a danger action button (red) with an icon.
     * 
     * @param text The text to display on the button
     * @param icon The icon to display on the button
     * @return The created button
     */
    public static RoundedButton createDangerButton(String text, Icon icon) {
        RoundedButton button = new RoundedButton(text, icon);
        button.setColors(
            new Color(178, 34, 34),    // Firebrick
            new Color(220, 20, 60),    // Crimson
            new Color(139, 0, 0),      // Dark Red
            new Color(139, 0, 0),      // Dark Red
            Color.WHITE
        );
        return button;
    }
    
    /**
     * Creates a secondary action button (light blue).
     * 
     * @param text The text to display on the button
     * @return The created button
     */
    public static RoundedButton createSecondaryButton(String text) {
        return createSecondaryButton(text, null);
    }
    
    /**
     * Creates a secondary action button (light blue) with an icon.
     * 
     * @param text The text to display on the button
     * @param icon The icon to display on the button
     * @return The created button
     */
    public static RoundedButton createSecondaryButton(String text, Icon icon) {
        RoundedButton button = new RoundedButton(text, icon);
        button.setColors(
            new Color(100, 149, 237),  // Cornflower Blue
            new Color(135, 206, 250),  // Light Sky Blue
            new Color(70, 130, 180),   // Steel Blue
            new Color(70, 130, 180),   // Steel Blue
            Color.WHITE
        );
        return button;
    }
}