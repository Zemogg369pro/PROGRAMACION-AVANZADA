package view;

import controller.MateriaController;
import controller.ProfesorController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main window of the application.
 * Implements the View part of the MVC pattern.
 */
public class MainWindow extends JFrame {
    // Define a consistent color palette
    private static final Color PRIMARY_COLOR = new Color(51, 98, 140);    // Dark blue
    private static final Color SECONDARY_COLOR = new Color(70, 130, 180); // Steel blue
    private static final Color ACCENT_COLOR = new Color(100, 149, 237);   // Cornflower blue
    private static final Color LIGHT_COLOR = new Color(240, 248, 255);    // Alice blue
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 250); // Light lavender
    private static final Color TEXT_COLOR = new Color(30, 30, 30);        // Almost black
    private static final Color TEXT_LIGHT_COLOR = new Color(250, 250, 250); // Almost white

    private ProfesorController profesorController;
    private MateriaController materiaController;
    private ProfesorManagementPanel profesorManagementPanel;
    private MateriaManagementPanel materiaManagementPanel;
    private JTabbedPane tabbedPane;
    private JLabel statusLabel;
    private JLabel dateLabel;
    private JLabel clockLabel;
    private Timer clockTimer;

    /**
     * Constructor for MainWindow.
     */
    public MainWindow() {
        super("Sistema de Gestión Académica");
        this.profesorController = new ProfesorController();
        this.materiaController = new MateriaController();

        // Set up the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);

        // Set a nice background color for the frame
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Add a window listener to save data and stop the timer when the window is closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveData();
                if (clockTimer != null && clockTimer.isRunning()) {
                    clockTimer.stop();
                }
            }
        });

        // Create the menu bar
        createMenuBar();

        // Create the toolbar
        createToolBar();

        // Create the panels with improved styling
        profesorManagementPanel = new ProfesorManagementPanel(profesorController);
        materiaManagementPanel = new MateriaManagementPanel(materiaController, profesorController);

        // Create a tabbed pane with improved styling
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
        tabbedPane.setBackground(BACKGROUND_COLOR);
        tabbedPane.setForeground(PRIMARY_COLOR);

        // Set custom tab appearance
        UIManager.put("TabbedPane.selected", new Color(220, 230, 240));
        UIManager.put("TabbedPane.contentAreaColor", LIGHT_COLOR);
        UIManager.put("TabbedPane.focus", PRIMARY_COLOR);
        UIManager.put("TabbedPane.highlight", SECONDARY_COLOR);
        UIManager.put("TabbedPane.light", new Color(220, 230, 240));
        UIManager.put("TabbedPane.tabAreaBackground", BACKGROUND_COLOR);
        UIManager.put("TabbedPane.unselectedBackground", new Color(200, 210, 220));

        // Add tabs with icons
        Icon profesorIcon = createImageIcon("/icons/profesor.png", "Profesor Icon");
        if (profesorIcon == null) {
            profesorIcon = UIManager.getIcon("FileView.directoryIcon");
        }

        Icon materiaIcon = createImageIcon("/icons/materia.png", "Materia Icon");
        if (materiaIcon == null) {
            materiaIcon = UIManager.getIcon("FileView.fileIcon");
        }

        // Add the tabs to the tabbed pane
        tabbedPane.addTab("Gestión de Profesores", profesorIcon, profesorManagementPanel);
        tabbedPane.addTab("Gestión de Materias", materiaIcon, materiaManagementPanel);

        // Add some padding around the tabbed pane
        tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add the tabbed pane to the window
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // Add a footer panel with version information and status
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(PRIMARY_COLOR);
        footerPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        // Version label on the right
        JLabel versionLabel = new JLabel("Sistema de Gestión Académica v1.0");
        versionLabel.setForeground(TEXT_LIGHT_COLOR);
        versionLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

        // Status label on the left
        statusLabel = new JLabel("Listo");
        statusLabel.setForeground(TEXT_LIGHT_COLOR);
        statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

        // Clock label in the bottom-left corner
        clockLabel = new JLabel();
        clockLabel.setForeground(TEXT_LIGHT_COLOR);
        clockLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

        // Create a panel for the left side of the footer to hold both status and clock
        JPanel leftFooterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftFooterPanel.setOpaque(false);
        leftFooterPanel.add(clockLabel);
        leftFooterPanel.add(statusLabel);

        footerPanel.add(leftFooterPanel, BorderLayout.WEST);
        footerPanel.add(versionLabel, BorderLayout.EAST);
        add(footerPanel, BorderLayout.SOUTH);

        // Add a header panel with a logo or title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Create a panel for the title with a gradient background
        JPanel titlePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                // Create a gradient from primary to secondary color
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY_COLOR, w, h, SECONDARY_COLOR);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);

                g2d.dispose();
            }
        };
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("SISTEMA DE GESTIÓN ACADÉMICA");
        titleLabel.setForeground(TEXT_LIGHT_COLOR);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Create panel for date
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        datePanel.setOpaque(false);

        // Date label
        dateLabel = new JLabel();
        dateLabel.setForeground(TEXT_LIGHT_COLOR);
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        // Add date label to panel
        datePanel.add(dateLabel);

        // Update date and time
        updateDateTime();

        // Create timer to update date and time every second
        clockTimer = new Timer(1000, e -> updateDateTime());
        clockTimer.start();

        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.add(datePanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // Set the tab change listener to update the status bar
        tabbedPane.addChangeListener(e -> {
            int index = tabbedPane.getSelectedIndex();
            if (index == 0) {
                setStatus("Gestión de Profesores");
            } else if (index == 1) {
                setStatus("Gestión de Materias");
            }
        });

        // Initial status
        setStatus("Gestión de Profesores");
    }

    /**
     * Creates the menu bar.
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(LIGHT_COLOR);
        menuBar.setBorder(new CompoundBorder(
            new LineBorder(PRIMARY_COLOR, 1),
            new EmptyBorder(1, 1, 1, 1)
        ));

        // File menu
        JMenu fileMenu = createMenu("Archivo", 'A');

        JMenuItem saveItem = createMenuItem("Guardar", 'G', "Guardar todos los datos", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(e -> saveData());

        JMenuItem exitItem = createMenuItem("Salir", 'S', "Salir de la aplicación", KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        exitItem.addActionListener(e -> {
            saveData();
            System.exit(0);
        });

        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // View menu
        JMenu viewMenu = createMenu("Ver", 'V');

        JMenuItem profesoresItem = createMenuItem("Profesores", 'P', "Ver gestión de profesores", KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_DOWN_MASK));
        profesoresItem.addActionListener(e -> tabbedPane.setSelectedIndex(0));

        JMenuItem materiasItem = createMenuItem("Materias", 'M', "Ver gestión de materias", KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_DOWN_MASK));
        materiasItem.addActionListener(e -> tabbedPane.setSelectedIndex(1));

        viewMenu.add(profesoresItem);
        viewMenu.add(materiasItem);

        // Help menu
        JMenu helpMenu = createMenu("Ayuda", 'y');

        JMenuItem aboutItem = createMenuItem("Acerca de", 'A', "Información sobre la aplicación", null);
        aboutItem.addActionListener(e -> showAboutDialog());

        helpMenu.add(aboutItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Creates a menu with the given name and mnemonic.
     * 
     * @param name The name of the menu
     * @param mnemonic The mnemonic for the menu
     * @return The created menu
     */
    private JMenu createMenu(String name, char mnemonic) {
        JMenu menu = new JMenu(name);
        menu.setMnemonic(mnemonic);
        menu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        menu.setForeground(TEXT_COLOR);
        return menu;
    }

    /**
     * Creates a menu item with the given name, mnemonic, tooltip, and accelerator.
     * 
     * @param name The name of the menu item
     * @param mnemonic The mnemonic for the menu item
     * @param tooltip The tooltip for the menu item
     * @param accelerator The accelerator for the menu item
     * @return The created menu item
     */
    private JMenuItem createMenuItem(String name, char mnemonic, String tooltip, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setMnemonic(mnemonic);
        menuItem.setToolTipText(tooltip);
        if (accelerator != null) {
            menuItem.setAccelerator(accelerator);
        }
        menuItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        menuItem.setForeground(TEXT_COLOR);

        // Add hover effect
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuItem.setBackground(LIGHT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuItem.setBackground(UIManager.getColor("MenuItem.background"));
            }
        });

        return menuItem;
    }

    /**
     * Creates the toolbar.
     */
    private void createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setBorder(new CompoundBorder(
            new LineBorder(PRIMARY_COLOR, 1),
            new EmptyBorder(5, 5, 5, 5)
        ));
        toolBar.setBackground(LIGHT_COLOR);

        // Save button
        JButton saveButton = createToolBarButton("Guardar", "Guardar todos los datos", UIManager.getIcon("FileView.floppyDriveIcon"));
        saveButton.addActionListener(e -> saveData());

        // Profesores button
        JButton profesoresButton = createToolBarButton("Profesores", "Ver gestión de profesores", UIManager.getIcon("FileView.directoryIcon"));
        profesoresButton.addActionListener(e -> tabbedPane.setSelectedIndex(0));

        // Materias button
        JButton materiasButton = createToolBarButton("Materias", "Ver gestión de materias", UIManager.getIcon("FileView.fileIcon"));
        materiasButton.addActionListener(e -> tabbedPane.setSelectedIndex(1));

        // Add buttons to toolbar
        toolBar.add(saveButton);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(profesoresButton);
        toolBar.add(materiasButton);

        add(toolBar, BorderLayout.PAGE_START);
    }

    /**
     * Creates a toolbar button with the given text, tooltip, and icon.
     * 
     * @param text The text for the button
     * @param tooltip The tooltip for the button
     * @param icon The icon for the button
     * @return The created button
     */
    private JButton createToolBarButton(String text, String tooltip, Icon icon) {
        JButton button = new JButton(text);
        button.setIcon(icon);
        button.setToolTipText(tooltip);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button.setForeground(TEXT_COLOR);
        button.setBackground(LIGHT_COLOR);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SECONDARY_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(220, 230, 240));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(LIGHT_COLOR);
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(SECONDARY_COLOR, 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });

        return button;
    }

    /**
     * Saves all data.
     */
    private void saveData() {
        profesorController.saveProfesores();
        materiaController.saveMaterias();
        setStatus("Datos guardados correctamente");

        // Show a success message
        JOptionPane.showMessageDialog(
            this,
            "Los datos se han guardado correctamente.",
            "Guardar",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Shows the about dialog.
     */
    private void showAboutDialog() {
        // Create a custom panel for the about dialog
        JPanel aboutPanel = new JPanel(new BorderLayout(10, 10));
        aboutPanel.setBackground(LIGHT_COLOR);
        aboutPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Sistema de Gestión Académica");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Version
        JLabel versionLabel = new JLabel("Versión 1.0");
        versionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        versionLabel.setForeground(TEXT_COLOR);
        versionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Description
        JTextArea descriptionArea = new JTextArea(
            "Sistema de gestión académica para administrar profesores y materias.\n\n" +
            "Desarrollado como parte del examen práctico."
        );
        descriptionArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        descriptionArea.setForeground(TEXT_COLOR);
        descriptionArea.setBackground(LIGHT_COLOR);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        // Add components to the panel
        JPanel headerPanel = new JPanel(new BorderLayout(5, 5));
        headerPanel.setBackground(LIGHT_COLOR);
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(versionLabel, BorderLayout.CENTER);

        aboutPanel.add(headerPanel, BorderLayout.NORTH);
        aboutPanel.add(descriptionArea, BorderLayout.CENTER);

        // Show the dialog
        JOptionPane.showMessageDialog(
            this,
            aboutPanel,
            "Acerca de",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    /**
     * Sets the status message.
     * 
     * @param message The status message
     */
    private void setStatus(String message) {
        statusLabel.setText(message);
    }

    /**
     * Updates the date and time labels with the current date and time.
     */
    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();

        dateLabel.setText("Fecha: " + dateFormat.format(now));
        clockLabel.setText("Hora: " + timeFormat.format(now));
    }

    /**
     * Creates an ImageIcon from the specified path.
     * 
     * @param path The path to the image
     * @param description The description of the image
     * @return The ImageIcon, or null if the path was invalid
     */
    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
