package Tarea_06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGrid extends JFrame {
    private JTextField campoMonto;
    private JLabel etiquetaPropina;
    private JLabel etiquetaTotal;
    private JButton boton15;
    private JButton boton20;
    private JButton botonRedondearAbajo;
    private JButton botonRedondearArriba;
    private double porcentaje = 0.15;
    private boolean redondearArriba = false;

    public CalculadoraGrid() {
        setTitle("Calculadora con Grid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(new JLabel("Cuenta:"), gbc);
        
        gbc.gridx = 2;
        campoMonto = new JTextField("Ingrese monto");
        panelPrincipal.add(campoMonto, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panelPrincipal.add(new JLabel("Propina:"), gbc);
        
        gbc.gridx = 2;
        etiquetaPropina = new JLabel("0.00");
        panelPrincipal.add(etiquetaPropina, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panelPrincipal.add(new JLabel("Total:"), gbc);
        
        gbc.gridx = 2;
        etiquetaTotal = new JLabel("0.00");
        panelPrincipal.add(etiquetaTotal, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        JPanel panelPorcentajes = new JPanel(new GridLayout(1, 2, 10, 0));
        boton15 = crearBoton("15%", e -> cambiarPorcentaje(0.15));
        boton20 = crearBoton("20%", e -> cambiarPorcentaje(0.20));
        panelPorcentajes.add(boton15);
        panelPorcentajes.add(boton20);
        panelPrincipal.add(panelPorcentajes, gbc);

        gbc.gridy = 4;
        JPanel panelRedondeo = new JPanel(new GridLayout(1, 2, 10, 0));
        botonRedondearAbajo = crearBoton("Redondear Abajo", e -> cambiarRedondeo(false));
        botonRedondearArriba = crearBoton("Redondear Arriba", e -> cambiarRedondeo(true));
        panelRedondeo.add(botonRedondearAbajo);
        panelRedondeo.add(botonRedondearArriba);
        panelPrincipal.add(panelRedondeo, gbc);

        campoMonto.addActionListener(e -> calcularTodo());
        
        add(panelPrincipal);
        actualizarEstilos();
        setVisible(true);
    }

    private JButton crearBoton(String texto, ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(accion);
        boton.setFocusPainted(false);
        boton.setBackground(new Color(240, 240, 240));
        return boton;
    }

    private void calcularTodo() {
        try {
            double monto = Double.parseDouble(campoMonto.getText());
            double propina = monto * porcentaje;
            double total = monto + propina;

            if (redondearArriba) {
                total = Math.ceil(total);
            } else {
                total = Math.floor(total);
            }
            propina = total - monto;

            etiquetaPropina.setText(String.format("%.2f", propina));
            etiquetaTotal.setText(String.format("%.2f", total));
        } catch (NumberFormatException ex) {
            etiquetaPropina.setText("0.00");
            etiquetaTotal.setText("0.00");
        }
    }

    private void cambiarPorcentaje(double nuevoPorcentaje) {
        porcentaje = nuevoPorcentaje;
        actualizarEstilos();
        calcularTodo();
    }

    private void cambiarRedondeo(boolean arriba) {
        redondearArriba = arriba;
        actualizarEstilos();
        calcularTodo();
    }

    private void actualizarEstilos() {
        boton15.setBackground(porcentaje == 0.15 ? Color.LIGHT_GRAY : new Color(240, 240, 240));
        boton20.setBackground(porcentaje == 0.20 ? Color.LIGHT_GRAY : new Color(240, 240, 240));
        botonRedondearArriba.setBackground(redondearArriba ? Color.LIGHT_GRAY : new Color(240, 240, 240));
        botonRedondearAbajo.setBackground(!redondearArriba ? Color.LIGHT_GRAY : new Color(240, 240, 240));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGrid());
    }
}