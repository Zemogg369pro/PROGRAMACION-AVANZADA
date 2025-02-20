package Tarea07;

import javax.swing.*;
import java.awt.event.*;

public class RadioButtonEjemplo extends JFrame {
    private JLabel label;

    public RadioButtonEjemplo() {
        setTitle("Radio Buttons Ejemplo");
        setLayout(new java.awt.FlowLayout());

        JRadioButton rbRojo = new JRadioButton("Rojo");
        JRadioButton rbVerde = new JRadioButton("Verde");
        JRadioButton rbAzul = new JRadioButton("Azul");
        label = new JLabel("Selección: Ninguna");

        // agrupar los botones
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbRojo);
        grupo.add(rbVerde);
        grupo.add(rbAzul);

        // listener comun
        ItemListener listener = e -> {
            if (rbRojo.isSelected()) label.setText("Selección: Rojo");
            else if (rbVerde.isSelected()) label.setText("Selección: Verde");
            else if (rbAzul.isSelected()) label.setText("Selección: Azul");
        };

        rbRojo.addItemListener(listener);
        rbVerde.addItemListener(listener);
        rbAzul.addItemListener(listener);

        add(rbRojo);
        add(rbVerde);
        add(rbAzul);
        add(label);

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RadioButtonEjemplo());
    }
}