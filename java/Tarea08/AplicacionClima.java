package Tarea08;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AplicacionClima extends JFrame {
    // componentes para los datos del clima
    private JLabel etiquetaCondicion = new JLabel("Condición");
    private JLabel etiquetaTemperatura = new JLabel("57°F");
    private JLabel etiquetaHumedad = new JLabel("91%");
    private JLabel etiquetaPrecipitacion = new JLabel("59%");
    private JLabel etiquetaViento = new JLabel("29 mph");

    public AplicacionClima() {
        // configurar la ventana
        setTitle("Aplicación del Clima");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setLocationRelativeTo(null);

        // panel principal con BoxLayout vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // título principal
        JLabel etiquetaTitulo = new JLabel("¿Cómo está el clima?");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // texto de bienvenida
        JLabel etiquetaBienvenida = new JLabel("¡Bienvenido a la aplicación del clima! Presiona el botón 'Actualizar' para obtener el pronóstico más reciente.");
        etiquetaBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        // panel para el código postal
        JPanel panelCodigoPostal = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel etiquetaCodigoPostal = new JLabel("Código Postal:");
        JTextField campoCodigoPostal = new JTextField(10);
        campoCodigoPostal.setText("12345");
        panelCodigoPostal.add(etiquetaCodigoPostal);
        panelCodigoPostal.add(campoCodigoPostal);

        // botón de actualizar
        JButton botonActualizar = new JButton("Actualizar");
        botonActualizar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // panel para los datos del clima
        JPanel panelClima = new JPanel(new GridLayout(5, 1, 10, 5));
        panelClima.setBorder(BorderFactory.createTitledBorder("Condiciones Actuales"));
        
        // configurar estilo de los datos
        Font fuenteDatos = new Font("Arial", Font.PLAIN, 14);
        agregarDatoClima(panelClima, "Condición:", etiquetaCondicion);
        agregarDatoClima(panelClima, "Temperatura:", etiquetaTemperatura);
        agregarDatoClima(panelClima, "Humedad:", etiquetaHumedad);
        agregarDatoClima(panelClima, "Precipitación:", etiquetaPrecipitacion);
        agregarDatoClima(panelClima, "Viento:", etiquetaViento);

        // ensamblar componentes
        panelPrincipal.add(etiquetaTitulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(etiquetaBienvenida);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelCodigoPostal);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(botonActualizar);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelClima);

        // configurar acción del botón (simulación)
        botonActualizar.addActionListener((ActionEvent e) -> {
            // logica para actualizar los datos
            JOptionPane.showMessageDialog(this, "Actualizando clima para: " + campoCodigoPostal.getText());
        });

        add(panelPrincipal);
        setVisible(true);
    }

    private void agregarDatoClima(JPanel panel, String etiqueta, JLabel valor) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.add(new JLabel(etiqueta), BorderLayout.WEST);
        fila.add(valor, BorderLayout.EAST);
        panel.add(fila);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplicacionClima());
    }
}