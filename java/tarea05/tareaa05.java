package tarea05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class tareaa05 extends JFrame{

	private JButton startStopButton, resetButton, exitButton;
    private JLabel runningTimeLabel, totalTimeLabel;
    private JTextField runningTimeTextField, totalTimeTextField;
    private Timer displayTimer;
    private long startTime, stoppedTime, stopTime;
    
    public tareaa05() {
        setTitle("DualMode Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
 
        startStopButton = new JButton("Start");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        runningTimeLabel = new JLabel("Running Time:");
        totalTimeLabel = new JLabel("Total Time:");

        runningTimeTextField = new JTextField(10);
        totalTimeTextField = new JTextField(10);
 
        runningTimeTextField.setEditable(false);
        totalTimeTextField.setEditable(false);
 
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(runningTimeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(runningTimeTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(totalTimeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(totalTimeTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(startStopButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(resetButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(exitButton, constraints);

      
        startStopButton.addActionListener(e -> startStopButtonActionPerformed());
        resetButton.addActionListener(e -> resetButtonActionPerformed());
        exitButton.addActionListener(e -> exitButtonActionPerformed());

         
        displayTimer = new Timer(1000, e -> displayTimerActionPerformed());

        pack();
        setLocationRelativeTo(null);  
    }
    
    private void startStopButtonActionPerformed() {
        if (startStopButton.getText().equals("Start")) {
            startTime = System.currentTimeMillis();
            stoppedTime = 0;
            startStopButton.setText("Stop");
            resetButton.setEnabled(false);
            exitButton.setEnabled(false);
            displayTimer.start();
        } else if (startStopButton.getText().equals("Stop")) {
            stopTime = System.currentTimeMillis();
            startStopButton.setText("Restart");
            resetButton.setEnabled(true);
            exitButton.setEnabled(true);
            displayTimer.stop();
        } else if (startStopButton.getText().equals("Restart")) {
            stoppedTime += System.currentTimeMillis() - stopTime;
            startStopButton.setText("Stop");
            resetButton.setEnabled(false);
            exitButton.setEnabled(false);
            displayTimer.start();
        }
    }

    private void resetButtonActionPerformed() {
        runningTimeTextField.setText("00:00:00");
        totalTimeTextField.setText("00:00:00");
        startStopButton.setText("Start");
        resetButton.setEnabled(false);
    }

    private void exitButtonActionPerformed() {
        System.exit(0);
    }

    private void displayTimerActionPerformed() {
        long currentTime = System.currentTimeMillis();
        runningTimeTextField.setText(formatTime(currentTime - startTime - stoppedTime));
        totalTimeTextField.setText(formatTime(currentTime - startTime));
    }

    private String formatTime(long timeInMillis) {
        long seconds = timeInMillis / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
	        tareaa05 stopwatch = new tareaa05();
	        stopwatch.setVisible(true);
	    });
	}

}