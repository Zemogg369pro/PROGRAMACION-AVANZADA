package view;

import controller.MateriaController;
import controller.ProfesorController;
import model.Materia;
import model.Profesor;
import model.observer.MateriaObserver;
import model.observer.ProfesorObserver;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Panel for managing materias.
 */
public class MateriaManagementPanel extends JPanel implements MateriaObserver, ProfesorObserver {
    private MateriaController controller;
    private ProfesorController profesorController;
    private JTable materiaTable;
    private DefaultTableModel tableModel;
    private JTextField idField;
    private JTextField nombreField;
    private JTextField salonField;
    private JTextField horarioField;
    private JComboBox<String> profesorComboBox;
    private JSpinner creditosSpinner;
    private JTextArea descripcionArea;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;

    /**
     * Constructor for MateriaManagementPanel.
     *
     * @param controller The materia controller
     * @param profesorController The profesor controller
     */
    public MateriaManagementPanel(MateriaController controller, ProfesorController profesorController) {
        this.controller = controller;
        this.profesorController = profesorController;
        controller.addObserver(this);
        profesorController.addObserver(this);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(240, 240, 245)); // Light gray-blue background

        // Create the table panel with a title and border
        JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2), "Lista de Materias", 
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
                new Font("Arial", Font.BOLD, 14), new Color(70, 130, 180)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        tablePanel.setBackground(new Color(245, 245, 250)); // Light lavender background

        // Configure the table with improved styling
        String[] columnNames = {"ID", "Nombre", "Salón", "Horario", "Profesor", "Créditos"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        materiaTable = new JTable(tableModel);
        materiaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        materiaTable.setRowHeight(30);
        materiaTable.setIntercellSpacing(new Dimension(5, 5));
        materiaTable.setShowGrid(true);
        materiaTable.setFillsViewportHeight(true);
        materiaTable.setFont(new Font("Arial", Font.PLAIN, 12));

        // Set alternating row colors
        materiaTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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
        materiaTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        materiaTable.getTableHeader().setBackground(new Color(70, 130, 180)); // Steel Blue
        materiaTable.getTableHeader().setForeground(Color.WHITE);
        materiaTable.getTableHeader().setPreferredSize(new Dimension(0, 35));

        // Add the table to a scroll pane
        JScrollPane tableScrollPane = new JScrollPane(materiaTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // Create the form panel with a title and border
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2), "Detalles de la Materia", 
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

        // Salon field
        JLabel salonLabel = new JLabel("Salón:");
        salonLabel.setFont(new Font("Arial", Font.BOLD, 12));
        salonLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        salonField = new JTextField(20);
        salonField.setPreferredSize(new Dimension(150, 30));
        salonField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        salonField.setBackground(new Color(240, 248, 255)); // AliceBlue
        salonField.setFont(new Font("Arial", Font.PLAIN, 12));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(salonLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(salonField, gbc);

        // Horario field
        JLabel horarioLabel = new JLabel("Horario:");
        horarioLabel.setFont(new Font("Arial", Font.BOLD, 12));
        horarioLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        horarioField = new JTextField(20);
        horarioField.setPreferredSize(new Dimension(150, 30));
        horarioField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        horarioField.setBackground(new Color(240, 248, 255)); // AliceBlue
        horarioField.setFont(new Font("Arial", Font.PLAIN, 12));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        formPanel.add(horarioLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(horarioField, gbc);

        // Profesor field
        JLabel profesorLabel = new JLabel("Profesor:");
        profesorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        profesorLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        profesorComboBox = new JComboBox<>();
        profesorComboBox.setPreferredSize(new Dimension(150, 30));
        profesorComboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        profesorComboBox.setBackground(new Color(240, 248, 255)); // AliceBlue
        profesorComboBox.setFont(new Font("Arial", Font.PLAIN, 12));

        // Populate the profesor combo box
        updateProfesorComboBox();

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        formPanel.add(profesorLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(profesorComboBox, gbc);

        // Creditos field
        JLabel creditosLabel = new JLabel("Créditos:");
        creditosLabel.setFont(new Font("Arial", Font.BOLD, 12));
        creditosLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        creditosSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 10, 1));
        creditosSpinner.setPreferredSize(new Dimension(150, 30));
        creditosSpinner.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        creditosSpinner.getEditor().getComponent(0).setBackground(new Color(240, 248, 255)); // AliceBlue
        JComponent editor = creditosSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor)editor).getTextField().setFont(new Font("Arial", Font.PLAIN, 12));
        }

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        formPanel.add(creditosLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(creditosSpinner, gbc);

        // Descripcion field
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setFont(new Font("Arial", Font.BOLD, 12));
        descripcionLabel.setForeground(new Color(70, 130, 180)); // Steel Blue

        descripcionArea = new JTextArea(4, 20);
        descripcionArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        descripcionArea.setBackground(new Color(240, 248, 255)); // AliceBlue
        descripcionArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descripcionArea.setLineWrap(true);
        descripcionArea.setWrapStyleWord(true);

        JScrollPane descripcionScrollPane = new JScrollPane(descripcionArea);
        descripcionScrollPane.setPreferredSize(new Dimension(150, 80));

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        formPanel.add(descripcionLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.gridheight = 2;
        formPanel.add(descripcionScrollPane, gbc);
        gbc.gridheight = 1;

        // Add some padding at the bottom
        gbc.gridx = 0;
        gbc.gridy = 8;
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
                createMateria();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMateria();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMateria();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Add selection listener to the table
        materiaTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = materiaTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String id = (String) materiaTable.getValueAt(selectedRow, 0);
                    Materia materia = controller.findMateria(id);
                    if (materia != null) {
                        populateForm(materia);
                    }
                }
            }
        });

        // Initialize the table
        refreshTable();
    }

    /**
     * Updates the profesor combo box with the current list of profesores.
     */
    private void updateProfesorComboBox() {
        profesorComboBox.removeAllItems();
        List<Profesor> profesores = profesorController.getAllProfesores();
        for (Profesor profesor : profesores) {
            profesorComboBox.addItem(profesor.getId() + " - " + profesor.getNombre() + " " + profesor.getApellido());
        }
    }

    /**
     * Refreshes the table with the current materias.
     */
    public void refreshTable() {
        // Clear the table
        tableModel.setRowCount(0);

        // Add rows for each materia
        List<Materia> materias = controller.getAllMaterias();
        for (Materia materia : materias) {
            // Find the profesor name
            String profesorName = "N/A";
            Profesor profesor = profesorController.findProfesor(materia.getProfesorId());
            if (profesor != null) {
                profesorName = profesor.getNombre() + " " + profesor.getApellido();
            }

            Object[] row = {
                materia.getId(),
                materia.getNombre(),
                materia.getSalon(),
                materia.getHorario(),
                profesorName,
                materia.getCreditos()
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Populates the form with the values from the given materia.
     *
     * @param materia The materia
     */
    private void populateForm(Materia materia) {
        idField.setText(materia.getId());
        nombreField.setText(materia.getNombre());
        salonField.setText(materia.getSalon());
        horarioField.setText(materia.getHorario());
        creditosSpinner.setValue(materia.getCreditos());
        descripcionArea.setText(materia.getDescripcion());

        // Select the profesor in the combo box
        String profesorId = materia.getProfesorId();
        for (int i = 0; i < profesorComboBox.getItemCount(); i++) {
            String item = profesorComboBox.getItemAt(i);
            if (item.startsWith(profesorId + " - ")) {
                profesorComboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * Clears the form.
     */
    private void clearForm() {
        idField.setText("");
        nombreField.setText("");
        salonField.setText("");
        horarioField.setText("");
        creditosSpinner.setValue(3);
        descripcionArea.setText("");
        if (profesorComboBox.getItemCount() > 0) {
            profesorComboBox.setSelectedIndex(0);
        }
    }

    /**
     * Creates a new materia from the form values.
     */
    private void createMateria() {
        String id = idField.getText().trim();
        String nombre = nombreField.getText().trim();
        String salon = salonField.getText().trim();
        String horario = horarioField.getText().trim();
        int creditos = (Integer) creditosSpinner.getValue();
        String descripcion = descripcionArea.getText().trim();

        // Get the profesor ID from the combo box
        String profesorId = "";
        if (profesorComboBox.getSelectedItem() != null) {
            String selectedItem = profesorComboBox.getSelectedItem().toString();
            profesorId = selectedItem.split(" - ")[0];
        }

        // Validate the input
        if (id.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos ID y Nombre son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the materia
        Materia materia = new Materia(id, nombre, salon, horario, profesorId, creditos, descripcion);
        boolean success = controller.addMateria(materia);
        if (success) {
            refreshTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Materia creada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear la materia. El ID ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates the selected materia with the form values.
     */
    private void updateMateria() {
        int selectedRow = materiaTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna materia", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String oldId = (String) materiaTable.getValueAt(selectedRow, 0);
        String id = idField.getText().trim();
        String nombre = nombreField.getText().trim();
        String salon = salonField.getText().trim();
        String horario = horarioField.getText().trim();
        int creditos = (Integer) creditosSpinner.getValue();
        String descripcion = descripcionArea.getText().trim();

        // Get the profesor ID from the combo box
        String profesorId = "";
        if (profesorComboBox.getSelectedItem() != null) {
            String selectedItem = profesorComboBox.getSelectedItem().toString();
            profesorId = selectedItem.split(" - ")[0];
        }

        // Validate the input
        if (id.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos ID y Nombre son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the materia
        Materia materia = new Materia(id, nombre, salon, horario, profesorId, creditos, descripcion);
        boolean success = controller.updateMateria(oldId, materia);
        if (success) {
            refreshTable();
            JOptionPane.showMessageDialog(this, "Materia actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la materia. El ID ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Deletes the selected materia.
     */
    private void deleteMateria() {
        int selectedRow = materiaTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna materia", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = (String) materiaTable.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta materia?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = controller.removeMateria(id);
            if (success) {
                refreshTable();
                clearForm();
                JOptionPane.showMessageDialog(this, "Materia eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la materia", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void onMateriaAdded(Materia materia) {
        refreshTable();
    }

    @Override
    public void onMateriaUpdated(Materia oldMateria, Materia newMateria) {
        refreshTable();
    }

    @Override
    public void onMateriaRemoved(Materia materia) {
        refreshTable();
    }

    @Override
    public void onMateriasCleared() {
        refreshTable();
    }

    @Override
    public void onProfesorAdded(Profesor profesor) {
        updateProfesorComboBox();
    }

    @Override
    public void onProfesorUpdated(Profesor oldProfesor, Profesor newProfesor) {
        updateProfesorComboBox();
        refreshTable(); // Refresh the table in case any materia references this profesor
    }

    @Override
    public void onProfesorRemoved(Profesor profesor) {
        updateProfesorComboBox();
        refreshTable(); // Refresh the table in case any materia references this profesor
    }

    @Override
    public void onProfesoresCleared() {
        updateProfesorComboBox();
        refreshTable(); // Refresh the table in case any materia references profesores
    }
}
