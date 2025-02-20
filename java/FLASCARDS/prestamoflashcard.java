package FLASCARDS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class prestamoflashcard {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Asistente de Préstamos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel balanceLabel = new JLabel("Saldo del Préstamo");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(balanceLabel, gbc);
        JTextField balanceField = new JTextField(10);
        gbc.gridx = 1;
        frame.add(balanceField, gbc);

        JLabel rateLabel = new JLabel("Tasa de Interés");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(rateLabel, gbc);
        JTextField rateField = new JTextField(10);
        gbc.gridx = 1;
        frame.add(rateField, gbc);

        JLabel paymentsLabel = new JLabel("Número de Pagos");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(paymentsLabel, gbc);
        JTextField paymentsField = new JTextField(10);
        gbc.gridx = 1;
        frame.add(paymentsField, gbc);

        JLabel monthlyLabel = new JLabel("Pago Mensual");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(monthlyLabel, gbc);
        JTextField monthlyField = new JTextField(10);
        gbc.gridx = 1;
        frame.add(monthlyField, gbc);

        JButton computeButton = new JButton("Calcular Número de Pagos");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(computeButton, gbc);

        JTextArea analysisArea = new JTextArea(6, 20);
        analysisArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(analysisArea);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        frame.add(scrollPane, gbc);

        JButton newLoanButton = new JButton("Nuevo Análisis de Préstamo");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        frame.add(newLoanButton, gbc);

        JButton exitButton = new JButton("Salir");
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        frame.add(exitButton, gbc);

        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double balance = Double.parseDouble(balanceField.getText());
                    double rate = Double.parseDouble(rateField.getText());
                    int numPayments = Integer.parseInt(paymentsField.getText());
                    double monthlyPayment = Double.parseDouble(monthlyField.getText());
                    double interestRate = rate / 1200;

                    double finalPayment = balance * Math.pow(1 + interestRate, numPayments) * interestRate / (Math.pow(1 + interestRate, numPayments) - 1);
                    double totalPayment = finalPayment * numPayments;
                    double interestPaid = totalPayment - balance;

                    analysisArea.setText("Saldo del Préstamo: $" + balance + "\n" +
                                         "Tasa de Interés: " + rate + "%\n" +
                                         numPayments + " Pagos de $" + String.format("%.2f", monthlyPayment) + "\n" +
                                         "Pago Final de $" + String.format("%.2f", finalPayment) + "\n" +
                                         "Pagos Totales: $" + String.format("%.2f", totalPayment) + "\n" +
                                         "Intereses Pagados: $" + String.format("%.2f", interestPaid));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(e -> System.exit(0));
        newLoanButton.addActionListener(e -> {
            balanceField.setText("");
            rateField.setText("");
            paymentsField.setText("");
            monthlyField.setText("");
            analysisArea.setText("");
        });

        frame.setVisible(true);
    }
}