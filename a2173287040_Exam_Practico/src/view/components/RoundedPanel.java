package view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * A custom panel with rounded corners and shadow effects.
 */
public class RoundedPanel extends JPanel {
    private static final int ARC_WIDTH = 15;
    private static final int ARC_HEIGHT = 15;
    private static final int SHADOW_SIZE = 5;
    
    private Color backgroundColor = new Color(245, 245, 250); // Light lavender
    private Color borderColor = new Color(70, 130, 180);      // Steel Blue
    private boolean hasShadow = true;
    private String title = null;
    private Font titleFont = new Font("Tahoma", Font.BOLD, 14);
    private Color titleColor = new Color(70, 130, 180);       // Steel Blue
    
    /**
     * Creates a new rounded panel with the default layout.
     */
    public RoundedPanel() {
        this(new BorderLayout());
    }
    
    /**
     * Creates a new rounded panel with the specified layout.
     * 
     * @param layout The layout manager
     */
    public RoundedPanel(LayoutManager layout) {
        super(layout);
        setup();
    }
    
    /**
     * Sets up the panel appearance.
     */
    private void setup() {
        setOpaque(false);
        setBorder(new EmptyBorder(SHADOW_SIZE + 10, SHADOW_SIZE + 10, SHADOW_SIZE + 10, SHADOW_SIZE + 10));
    }
    
    /**
     * Sets the colors for the panel.
     * 
     * @param backgroundColor The background color
     * @param borderColor The border color
     */
    public void setColors(Color backgroundColor, Color borderColor) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        repaint();
    }
    
    /**
     * Sets whether the panel has a shadow.
     * 
     * @param hasShadow Whether the panel has a shadow
     */
    public void setHasShadow(boolean hasShadow) {
        this.hasShadow = hasShadow;
        repaint();
    }
    
    /**
     * Sets the title for the panel.
     * 
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
        repaint();
    }
    
    /**
     * Sets the title font for the panel.
     * 
     * @param titleFont The title font
     */
    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
        repaint();
    }
    
    /**
     * Sets the title color for the panel.
     * 
     * @param titleColor The title color
     */
    public void setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth() - (SHADOW_SIZE * 2);
        int height = getHeight() - (SHADOW_SIZE * 2);
        
        // Draw shadow if enabled
        if (hasShadow) {
            g2.setColor(new Color(0, 0, 0, 50));
            for (int i = 0; i < SHADOW_SIZE; i++) {
                g2.setStroke(new BasicStroke(SHADOW_SIZE - i));
                g2.draw(new RoundRectangle2D.Double(
                    SHADOW_SIZE + i, 
                    SHADOW_SIZE + i, 
                    width - (i * 2), 
                    height - (i * 2), 
                    ARC_WIDTH, 
                    ARC_HEIGHT
                ));
            }
        }
        
        // Fill the rounded rectangle
        g2.setColor(backgroundColor);
        g2.fill(new RoundRectangle2D.Double(SHADOW_SIZE, SHADOW_SIZE, width, height, ARC_WIDTH, ARC_HEIGHT));
        
        // Draw the border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1));
        g2.draw(new RoundRectangle2D.Double(SHADOW_SIZE, SHADOW_SIZE, width, height, ARC_WIDTH, ARC_HEIGHT));
        
        // Draw the title if set
        if (title != null && !title.isEmpty()) {
            g2.setFont(titleFont);
            g2.setColor(titleColor);
            
            FontMetrics fm = g2.getFontMetrics();
            int titleWidth = fm.stringWidth(title);
            int titleHeight = fm.getHeight();
            
            // Draw title background
            g2.setColor(backgroundColor);
            g2.fillRect(SHADOW_SIZE + 20, SHADOW_SIZE - titleHeight/2, titleWidth + 10, titleHeight);
            
            // Draw title text
            g2.setColor(titleColor);
            g2.drawString(title, SHADOW_SIZE + 25, SHADOW_SIZE + titleHeight/2 - 2);
        }
        
        g2.dispose();
    }
    
    /**
     * Creates a standard panel with the default styling.
     * 
     * @return The created panel
     */
    public static RoundedPanel createPanel() {
        return createPanel(null);
    }
    
    /**
     * Creates a standard panel with the default styling and title.
     * 
     * @param title The title
     * @return The created panel
     */
    public static RoundedPanel createPanel(String title) {
        RoundedPanel panel = new RoundedPanel();
        panel.setColors(
            new Color(245, 245, 250), // Light lavender
            new Color(70, 130, 180)   // Steel Blue
        );
        if (title != null && !title.isEmpty()) {
            panel.setTitle(title);
        }
        return panel;
    }
    
    /**
     * Creates a standard panel with the default styling, title, and layout.
     * 
     * @param title The title
     * @param layout The layout manager
     * @return The created panel
     */
    public static RoundedPanel createPanel(String title, LayoutManager layout) {
        RoundedPanel panel = new RoundedPanel(layout);
        panel.setColors(
            new Color(245, 245, 250), // Light lavender
            new Color(70, 130, 180)   // Steel Blue
        );
        if (title != null && !title.isEmpty()) {
            panel.setTitle(title);
        }
        return panel;
    }
}