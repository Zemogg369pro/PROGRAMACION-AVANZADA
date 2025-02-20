package Tarea02;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ejemploJframe_03_b extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Programa 2 - Java");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear etiqueta
        JLabel label = new JLabel("Ingresa tu nombre:");
        label.setBounds(50, 30, 150, 30);
        frame.add(label);

        // Crear campo de texto
        JTextField textField = new JTextField();
        textField.setBounds(50, 80, 200, 30);
        frame.add(textField);

        // Crear botón
        JButton button = new JButton("Enviar");
        button.setBounds(50, 130, 100, 30);
        frame.add(button);

        // Acción del botón
        button.addActionListener(e -> {
            String name = textField.getText();
            JOptionPane.showMessageDialog(frame, "¡Hola, " + name + "!");
        });

        // Mostrar ventana
        frame.setVisible(true);

    }
}