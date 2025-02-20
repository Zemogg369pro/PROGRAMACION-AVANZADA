package Tarea02;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ejemploJframe_03_a extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        JFrame frame = new JFrame("Interfaz Gráfica - Java");
	        frame.setSize(400, 300);
	        frame.setLayout(null);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        
	        JLabel label = new JLabel("Presiona el botón:");
	        label.setBounds(50, 30, 150, 30);
	        frame.add(label);

	       
	        JButton button = new JButton("Calcular");
	        button.setBounds(50, 80, 100, 30);
	        frame.add(button);

	        
	        frame.setVisible(true);
	    }
	
		;
	

	/**
	 * Create the frame.
	 */
	public ejemploJframe_03_a() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
