package view;

import controller.ProfesorController;
import model.Profesor;
import model.observer.ProfesorObserver;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Panel for managing profesores.
 */
public class ProfesorManagementPanel extends JPanel implements ProfesorObserver {
    private ProfesorController controller;
    private JTable profesorTable;
    private DefaultTableModel tableModel;
    private JTextField idField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField especialidadField;
    private JSpinner edadSpinner;
    private JTextField emailField;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;

    /**
     * Constructor for ProfesorManagementPanel.
     *
     * @param controller The profesor controller
     */
    public ProfesorManagementPanel(ProfesorController controller) {
        this.controller = controller;
        controller.addObserver(this);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(240, 240, 245)); // Light gray-blue background

        // Create the table panel with a title and border
        JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2), "Lista de Profesores", 
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
                new Font("Arial", Font.BOLD, 14), new Color(70, 130, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        tablePanel.setBackground(new Color(245, 245, 250)); // Light lavender background

        // Configure the table with improved styling
        String[] columnNames = {"ID", "Nombre", "Apellido", "Especialidad", "Edad", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        profesorTable = new JTable(tableModel);
        profesorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        profesorTable.setRowHeight(30);
        profesorTable.setIntercellSpacing(new Dimension(5, 5));
        profesorTable.setShowGrid(true);
        profesorTable.setFillsViewportHeight(true);
        profesorTable.setFont(new Font("Arial", Font.PLAIN, 12));

        // Set alternating row colors
        profesorTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(240, 248, 255) : Color.WHITE); // AliceBlue for even rows
                }
                return c;
            }
        });

        // Enhance header appearance
        profesorTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        profesorTable.getTableHeader().setBackground(new Color(70, 130, 180)); // Steel Blue
        profesorTable.getTableHeader().setForeground(Color.WHITE);
        profesorTable.getTableHeader().setPreferredSize(new Dimension(0, 35));

        // Add the table to a scroll pane
        JScrollPane tableScrollPane = new JScrollPane(profesorTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // Create the form panel with a title and border
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2), "Detalles del Profesor", 
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
                new Font("Arial", Font.BOLD, 14), new Color(70, 130, 180)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        formPanel.setBackground(new Color(245, 245, 250)); // Light lavender background

        // Set preferred size for the form panel
        formPanel.setPreferredSize(new Dimension(320, 420));

        // Add the form panel to a scroll pane
        JScrollPane formScrollPane = new JScrollPane(formPanel);
        formScrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(formScrollPane, BorderLayout.EAST);

        // Create form fields
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // ID field
        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 12));
        idLabel.setForeground(new Color(70, 130, 180)); // Steel Blue
        idLabel.setToolTipText("Este campo es el identificador único");

        idField = new JTextField(20);
        idField.setPreferredSize(new Dimension(150, 30));
        idField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        idField.setBackground(new Color(240, 248, 255)); // AliceBlue
        idField.setFont(new Font("Arial", Font.PLAIN, 12));
        idField.setToolTipText("Este campo es el identificador único");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(idField, gbc);

        // Nombre field
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nombreLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        nombreField = new JTextField(20);
        nombreField.setPreferredSize(new Dimension(150, 30));
        nombreField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        nombreField.setBackground(new Color(240, 248, 255)); // AliceBlue
        nombreField.setFont(new Font("Arial", Font.PLAIN, 12));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(nombreField, gbc);

        // Apellido field
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        apellidoLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        apellidoField = new JTextField(20);
        apellidoField.setPreferredSize(new Dimension(150, 30));
        apellidoField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        apellidoField.setBackground(new Color(240, 248, 255)); // AliceBlue
        apellidoField.setFont(new Font("Arial", Font.PLAIN, 12));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(apellidoLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(apellidoField, gbc);

        // Especialidad field
        JLabel especialidadLabel = new JLabel("Especialidad:");
        especialidadLabel.setFont(new Font("Arial", Font.BOLD, 12));
        especialidadLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        especialidadField = new JTextField(20);
        especialidadField.setPreferredSize(new Dimension(150, 30));
        especialidadField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        especialidadField.setBackground(new Color(240, 248, 255)); // AliceBlue
        especialidadField.setFont(new Font("Arial", Font.PLAIN, 12));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        formPanel.add(especialidadLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(especialidadField, gbc);

        // Edad field
        JLabel edadLabel = new JLabel("Edad:");
        edadLabel.setFont(new Font("Arial", Font.BOLD, 12));
        edadLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        edadSpinner = new JSpinner(new SpinnerNumberModel(30, 18, 100, 1));
        edadSpinner.setPreferredSize(new Dimension(150, 30));
        edadSpinner.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        edadSpinner.getEditor().getComponent(0).setBackground(new Color(240, 248, 255)); // AliceBlue
        JComponent editor = edadSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor)editor).getTextField().setFont(new Font("Arial", Font.PLAIN, 12));
        }

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        formPanel.add(edadLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(edadSpinner, gbc);

        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 12));
        emailLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(150, 30));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        emailField.setBackground(new Color(240, 248, 255)); // AliceBlue
        emailField.setFont(new Font("Arial", Font.PLAIN, 12));

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(emailField, gbc);

        // Add some padding at the bottom
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        formPanel.add(Box.createVerticalGlue(), gbc);

        // Create the button panel with styled buttons and icons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(70, 130, 180)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        buttonPanel.setBackground(new Color(230, 240, 250)); // Light blue background

        // Create buttons with icons and improved styling
        createButton = new JButton("Crear");
        createButton.setIcon(UIManager.getIcon("FileView.fileIcon"));
        createButton.setFocusPainted(false);
        createButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Arial", Font.BOLD, 12));
        createButton.setBorder(BorderFactory.createRaisedBevelBorder());

        updateButton = new JButton("Actualizar");
        updateButton.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        updateButton.setFocusPainted(false);
        updateButton.setBackground(new Color(46, 139, 87)); // Sea Green
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 12));
        updateButton.setBorder(BorderFactory.createRaisedBevelBorder());

        deleteButton = new JButton("Eliminar");
        deleteButton.setIcon(UIManager.getIcon("FileChooser.detailsViewIcon"));
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(new Color(178, 34, 34)); // Firebrick
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setBorder(BorderFactory.createRaisedBevelBorder());

        clearButton = new JButton("Limpiar");
        clearButton.setIcon(UIManager.getIcon("FileChooser.homeFolderIcon"));
        clearButton.setFocusPainted(false);
        clearButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearButton.setBorder(BorderFactory.createRaisedBevelBorder());

        // Add buttons to the panel
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProfesor();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProfesor();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProfesor();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Add selection listener to the table
        profesorTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = profesorTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String id = (String) profesorTable.getValueAt(selectedRow, 0);
                    Profesor profesor = controller.findProfesor(id);
                    if (profesor != null) {
                        populateForm(profesor);
                    }
                }
            }
        });

        // Initialize the table
        refreshTable();
    }

    /**
     * Refreshes the table with the current profesores.
     */
    public void refreshTable() {
        // Clear the table
        tableModel.setRowCount(0);

        // Add rows for each profesor
        List<Profesor> profesores = controller.getAllProfesores();
        for (Profesor profesor : profesores) {
            Object[] row = {
                profesor.getId(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getEspecialidad(),
                profesor.getEdad(),
                profesor.getEmail()
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Populates the form with the values from the given profesor.
     *
     * @param profesor The profesor
     */
    private void populateForm(Profesor profesor) {
        idField.setText(profesor.getId());
        nombreField.setText(profesor.getNombre());
        apellidoField.setText(profesor.getApellido());
        especialidadField.setText(profesor.getEspecialidad());
        edadSpinner.setValue(profesor.getEdad());
        emailField.setText(profesor.getEmail());
    }

    /**
     * Clears the form.
     */
    private void clearForm() {
        idField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        especialidadField.setText("");
        edadSpinner.setValue(30);
        emailField.setText("");
    }

    /**
     * Creates a new profesor from the form values.
     */
    private void createProfesor() {
        String id = idField.getText().trim();
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String especialidad = especialidadField.getText().trim();
        int edad = (Integer) edadSpinner.getValue();
        String email = emailField.getText().trim();

        // Validate the input
        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos ID, Nombre y Apellido son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the profesor
        Profesor profesor = new Profesor(id, nombre, apellido, especialidad, edad, email);
        boolean success = controller.addProfesor(profesor);
        if (success) {
            refreshTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear el profesor. El ID ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates the selected profesor with the form values.
     */
    private void updateProfesor() {
        int selectedRow = profesorTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún profesor", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String oldId = (String) profesorTable.getValueAt(selectedRow, 0);
        String id = idField.getText().trim();
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String especialidad = especialidadField.getText().trim();
        int edad = (Integer) edadSpinner.getValue();
        String email = emailField.getText().trim();

        // Validate the input
        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos ID, Nombre y Apellido son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the profesor
        Profesor profesor = new Profesor(id, nombre, apellido, especialidad, edad, email);
        boolean success = controller.updateProfesor(oldId, profesor);
        if (success) {
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el profesor. El ID ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Deletes the selected profesor.
     */
    private void deleteProfesor() {
        int selectedRow = profesorTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún profesor", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = (String) profesorTable.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este profesor?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = controller.removeProfesor(id);
            if (success) {
                refreshTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el profesor", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void onProfesorAdded(Profesor profesor) {
        refreshTable();
    }

    @Override
    public void onProfesorUpdated(Profesor oldProfesor, Profesor newProfesor) {
        refreshTable();
    }

    @Override
    public void onProfesorRemoved(Profesor profesor) {
        refreshTable();
    }

    @Override
    public void onProfesoresCleared() {
        refreshTable();
    }
}
