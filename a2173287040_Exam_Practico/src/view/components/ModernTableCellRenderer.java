package view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * A modern table cell renderer with improved styling.
 */
public class ModernTableCellRenderer extends DefaultTableCellRenderer {
    private static final Color HEADER_BACKGROUND = new Color(70, 130, 180); // Steel Blue
    private static final Color HEADER_FOREGROUND = Color.WHITE;
    private static final Color EVEN_ROW_COLOR = new Color(240, 248, 255);   // Alice Blue
    private static final Color ODD_ROW_COLOR = Color.WHITE;
    private static final Color SELECTION_BACKGROUND = new Color(100, 149, 237); // Cornflower Blue
    private static final Color SELECTION_FOREGROUND = Color.WHITE;
    private static final Color GRID_COLOR = new Color(220, 220, 220);       // Light Gray
    
    private boolean isHeader;
    
    /**
     * Creates a new modern table cell renderer.
     * 
     * @param isHeader Whether this renderer is for a header cell
     */
    public ModernTableCellRenderer(boolean isHeader) {
        this.isHeader = isHeader;
        setOpaque(true);
        setBorder(new EmptyBorder(8, 10, 8, 10));
        setFont(new Font("Tahoma", isHeader ? Font.BOLD : Font.PLAIN, 12));
        setHorizontalAlignment(isHeader ? SwingConstants.CENTER : SwingConstants.LEFT);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (isHeader) {
            c.setBackground(HEADER_BACKGROUND);
            c.setForeground(HEADER_FOREGROUND);
            ((JComponent) c).setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 1, GRID_COLOR),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
            ));
        } else {
            if (isSelected) {
                c.setBackground(SELECTION_BACKGROUND);
                c.setForeground(SELECTION_FOREGROUND);
            } else {
                c.setBackground(row % 2 == 0 ? EVEN_ROW_COLOR : ODD_ROW_COLOR);
                c.setForeground(Color.BLACK);
            }
            
            ((JComponent) c).setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, GRID_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
        }
        
        return c;
    }
    
    /**
     * Applies modern styling to a JTable.
     * 
     * @param table The table to style
     */
    public static void applyTo(JTable table) {
        // Set row height and other properties
        table.setRowHeight(35);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);
        table.setSelectionBackground(SELECTION_BACKGROUND);
        table.setSelectionForeground(SELECTION_FOREGROUND);
        table.setGridColor(GRID_COLOR);
        
        // Set header styling
        table.getTableHeader().setDefaultRenderer(new ModernTableCellRenderer(true));
        table.getTableHeader().setBackground(HEADER_BACKGROUND);
        table.getTableHeader().setForeground(HEADER_FOREGROUND);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        
        // Set cell renderer for all columns
        table.setDefaultRenderer(Object.class, new ModernTableCellRenderer(false));
    }
}