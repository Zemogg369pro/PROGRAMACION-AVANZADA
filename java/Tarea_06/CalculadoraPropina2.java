package Tarea_06;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraPropina2 extends JFrame {
    private JTextField campoMonto;
    private JLabel etiquetaPropina;
    private JLabel etiquetaTotal;
    private JButton boton15;
    private JButton boton20;
    private JButton botonRedondearAbajo;
    private JButton botonRedondearArriba;
    private double porcentaje = 0.15;
    private boolean redondearArriba = false;

    public CalculadoraPropina2() {
        setTitle("Calculadora de Propinas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 450);
        setLocationRelativeTo(null);

        // panel principal con boxLayout vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // seccion cuenta
        JPanel panelCuenta = crearSeccionVertical("Cuenta");
        campoMonto = new JTextField("Ingrese el monto");
        panelCuenta.add(campoMonto);
        
        // seccion propina y Total
        JPanel panelMontos = crearSeccionVertical("Montos");
        panelMontos.add(crearFilaHorizontal("Propina", "0.00"));
        panelMontos.add(crearFilaHorizontal("Total", "0.00"));
        
        // extraer referencias de las etiquetas
        etiquetaPropina = (JLabel) ((JPanel) panelMontos.getComponent(1)).getComponent(1);
        etiquetaTotal = (JLabel) ((JPanel) panelMontos.getComponent(2)).getComponent(1);

        // seccion porcentaje
        JPanel panelPorcentaje = crearSeccionVertical("Porcentaje de Propina");
        JPanel botonesPorcentaje = new JPanel(new GridLayout(1, 2, 10, 0));
        boton15 = crearBotonEstilado("15%");
        boton20 = crearBotonEstilado("20%");
        botonesPorcentaje.add(boton15);
        botonesPorcentaje.add(boton20);

        // seccion redondeo
        JPanel panelRedondeo = crearSeccionVertical("Redondeo");
        JPanel botonesRedondeo = new JPanel(new GridLayout(1, 2, 10, 0));
        botonRedondearAbajo = crearBotonEstilado("Redondear Abajo");
        botonRedondearArriba = crearBotonEstilado("Redondear Arriba");
        botonesRedondeo.add(botonRedondearAbajo);
        botonesRedondeo.add(botonRedondearArriba);

        // ensamblar componentes
        panelPrincipal.add(panelCuenta);
        panelPrincipal.add(panelMontos);
        panelPrincipal.add(panelPorcentaje);
        panelPorcentaje.add(botonesPorcentaje);
        panelPrincipal.add(panelRedondeo);
        panelRedondeo.add(botonesRedondeo);

        // configurar eventos
        campoMonto.addActionListener(e -> calcularTodo());
        boton15.addActionListener(e -> cambiarPorcentaje(0.15));
        boton20.addActionListener(e -> cambiarPorcentaje(0.20));
        botonRedondearAbajo.addActionListener(e -> cambiarRedondeo(false));
        botonRedondearArriba.addActionListener(e -> cambiarRedondeo(true));

        add(panelPrincipal);
        setVisible(true);
    }

    private JPanel crearSeccionVertical(String titulo) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setFont(etiqueta.getFont().deriveFont(Font.BOLD, 14));
        panel.add(etiqueta);
        return panel;
    }

    private JPanel crearFilaHorizontal(String texto, String valor) {
        JPanel fila = new JPanel(new BorderLayout());
        JLabel etiqueta = new JLabel(texto);
        JLabel valorLabel = new JLabel(valor);
        fila.add(etiqueta, BorderLayout.WEST);
        fila.add(valorLabel, BorderLayout.EAST);
        fila.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return fila;
    }

    private JButton crearBotonEstilado(String texto) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(new Color(240, 240, 240));
        boton.setPreferredSize(new Dimension(120, 35));
        return boton;
    }

    private void calcularTodo() {
        try {
            double monto = Double.parseDouble(campoMonto.getText());
            double propina = monto * porcentaje;
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

    private void cambiarPorcentaje(double nuevoPorcentaje) {
        porcentaje = nuevoPorcentaje;
        boton15.setBackground(nuevoPorcentaje == 0.15 ? Color.LIGHT_GRAY : new Color(240, 240, 240));
        boton20.setBackground(nuevoPorcentaje == 0.20 ? Color.LIGHT_GRAY : new Color(240, 240, 240));
        calcularTodo();
    }

    private void cambiarRedondeo(boolean arriba) {
        redondearArriba = arriba;
        botonRedondearArriba.setBackground(arriba ? Color.LIGHT_GRAY : new Color(240, 240, 240));
        botonRedondearAbajo.setBackground(!arriba ? Color.LIGHT_GRAY : new Color(240, 240, 240));
        calcularTodo();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraPropina());
    }
}