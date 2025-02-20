package tarea05;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class tarea05 extends JFrame {
	
    private JButton startButton, stopButton, exitButton;
    private JLabel startLabel, stopLabel, elapsedLabel;
    private JTextField startTextField, stopTextField, elapsedTextField;
    private long startTime, stopTime;

    public tarea05() {
        setTitle("Stopwatch Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
 
        startButton = new JButton("Start Timing");
        stopButton = new JButton("Stop Timing");
        exitButton = new JButton("Exit");

        startLabel = new JLabel("Start Time:");
        stopLabel = new JLabel("Stop Time:");
        elapsedLabel = new JLabel("Elapsed Time (sec):");

        startTextField = new JTextField(15);
        stopTextField = new JTextField(15);
        elapsedTextField = new JTextField(15);

        startTextField.setEditable(false);
        stopTextField.setEditable(false);
        elapsedTextField.setEditable(false);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(startButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(startLabel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(startTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(stopButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(stopLabel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(stopTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(exitButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(elapsedLabel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        add(elapsedTextField, constraints);
 
        startButton.addActionListener(e -> startButtonActionPerformed());
        stopButton.addActionListener(e -> stopButtonActionPerformed());
        exitButton.addActionListener(e -> exitButtonActionPerformed());

        pack();
        setLocationRelativeTo(null);
    }
    
    private void startButtonActionPerformed() {
        startTime = System.currentTimeMillis();
        startTextField.setText(String.valueOf(startTime));
        stopTextField.setText("");
        elapsedTextField.setText("");
    }

    private void stopButtonActionPerformed() {
        stopTime = System.currentTimeMillis();
        stopTextField.setText(String.valueOf(stopTime));

        double elapsedTime = (stopTime - startTime) / 1000.0;
        elapsedTextField.setText(String.valueOf(elapsedTime));
    }

    private void exitButtonActionPerformed() {
        System.exit(0);
    }
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
	        tarea05 stopwatch = new tarea05();
	        stopwatch.setVisible(true);
	    });
	}

}