package Tarea07;

import javax.swing.*;
import java.awt.event.*;

public class ComboBoxEjemplo extends JFrame {
    private JLabel label;

    public ComboBoxEjemplo() {
        setTitle("ComboBox Ejemplo");
        setLayout(new java.awt.FlowLayout());

        String[] opciones = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        JComboBox<String> combo = new JComboBox<>(opciones);
        label = new JLabel("Día seleccionado: " + opciones[0]);

        combo.addActionListener(e -> {
            String seleccion = (String) combo.getSelectedItem();
            label.setText("Día seleccionado: " + seleccion);
        });

        add(combo);
        add(label);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ComboBoxEjemplo());
    }
}