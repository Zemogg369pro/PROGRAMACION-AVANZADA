package Tarea07;

import javax.swing.*;
import java.awt.event.*;

public class CheckBoxEjemplo extends JFrame {
    
	private JLabel label;

    public CheckBoxEjemplo() {
        setTitle("Checkbox Ejemplo");
        setLayout(new java.awt.FlowLayout());

        JCheckBox check = new JCheckBox("Acepto los términos");
        label = new JLabel("Estado: No seleccionado");

        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (check.isSelected()) {
                    label.setText("Estado: Seleccionado");
                } else {
                    label.setText("Estado: No seleccionado");
                }
            }
        });

        add(check);
        add(label);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CheckBoxEjemplo());
    }
}