package Tarea_06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraPropina extends JFrame {
    private JTextField campoMonto;
    private JLabel etiquetaPropina;
    private JLabel etiquetaTotal;
    private JButton boton15Porciento;
    private JButton boton20Porciento;
    private JButton botonRedondearAbajo;
    private JButton botonRedondearArriba;
    private double porcentajePropina = 0.15;
    private boolean redondearArriba = false;

    public CalculadoraPropina() {
        setTitle("Calculadora de Propinas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        // panel principal con boxLayout vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // seccion cuenta
        JPanel panelCuenta = new JPanel(new BorderLayout());
        JLabel etiquetaCuenta = new JLabel("Cuenta");
        campoMonto = new JTextField("Ingrese Monto");
        panelCuenta.add(etiquetaCuenta, BorderLayout.NORTH);
        panelCuenta.add(campoMonto, BorderLayout.CENTER);
        panelCuenta.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // seccion propina
        JPanel panelPropina = crearPanelMonto("Propina", "0.00");
        etiquetaPropina = (JLabel) panelPropina.getComponent(1);

        // seccion total
        JPanel panelTotal = crearPanelMonto("Total", "0.00");
        etiquetaTotal = (JLabel) panelTotal.getComponent(1);

        // seccion porcentaje
        JPanel panelPorcentaje = new JPanel();
        panelPorcentaje.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel etiquetaPorcentaje = new JLabel("Porcentaje de Propina");
        boton15Porciento = new JButton("15%");
        boton20Porciento = new JButton("20%");
        
        // botones de porcentaje
        boton15Porciento.addActionListener(new ManejadorPorcentaje(0.15));
        boton20Porciento.addActionListener(new ManejadorPorcentaje(0.20));
        
        panelPorcentaje.add(etiquetaPorcentaje);
        panelPorcentaje.add(boton15Porciento);
        panelPorcentaje.add(boton20Porciento);
        panelPorcentaje.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // seccion redondeo
        JPanel panelRedondeo = new JPanel();
        panelRedondeo.setLayout(new FlowLayout(FlowLayout.LEFT));
        botonRedondearAbajo = new JButton("Redondear Abajo");
        botonRedondearArriba = new JButton("Redondear Arriba");
        
        // botones de redondeo
        botonRedondearAbajo.addActionListener(new ManejadorRedondeo(false));
        botonRedondearArriba.addActionListener(new ManejadorRedondeo(true));
        
        panelRedondeo.add(botonRedondearAbajo);
        panelRedondeo.add(botonRedondearArriba);
        panelRedondeo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // añadir todos los paneles a la principal
        panelPrincipal.add(panelCuenta);
        panelPrincipal.add(panelPropina);
        panelPrincipal.add(panelTotal);
        panelPrincipal.add(panelPorcentaje);
        panelPrincipal.add(panelRedondeo);

        // configurar el campo de monto
        campoMonto.addActionListener(e -> calcularValores());

        add(panelPrincipal);
        setVisible(true);
    }

    private JPanel crearPanelMonto(String titulo, String valorInicial) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel etiquetaTitulo = new JLabel(titulo);
        JLabel etiquetaValor = new JLabel(valorInicial);
        panel.add(etiquetaTitulo, BorderLayout.WEST);
        panel.add(etiquetaValor, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return panel;
    }

    private void calcularValores() {
        try {
            double monto = Double.parseDouble(campoMonto.getText());
            double propina = monto * porcentajePropina;
            double total = monto + propina;

            if (redondearArriba) {
                total = Math.ceil(total);
                propina = total - monto;
            } else {
                total = Math.floor(total);
                propina = total - monto;
            }

            etiquetaPropina.setText(String.format("%.2f", propina));
            etiquetaTotal.setText(String.format("%.2f", total));
        } catch (NumberFormatException ex) {
            etiquetaPropina.setText("0.00");
            etiquetaTotal.setText("0.00");
        }
    }

    private class ManejadorPorcentaje implements ActionListener {
        private double porcentaje;

        public ManejadorPorcentaje(double porcentaje) {
            this.porcentaje = porcentaje;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            porcentajePropina = porcentaje;
            boton15Porciento.setEnabled(porcentaje != 0.15);
            boton20Porciento.setEnabled(porcentaje != 0.20);
            calcularValores();
        }
    }

    private class ManejadorRedondeo implements ActionListener {
        private boolean redondearArribaValor;

        public ManejadorRedondeo(boolean redondearArriba) {
            this.redondearArribaValor = redondearArriba;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            redondearArriba = redondearArribaValor;
            botonRedondearAbajo.setEnabled(redondearArribaValor);
            botonRedondearArriba.setEnabled(!redondearArribaValor);
            calcularValores();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraPropina());
    }
}