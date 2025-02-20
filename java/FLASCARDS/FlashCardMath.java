package FLASCARDS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FlashCardMath extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel lblIntentos, lblCorrectos, lblPregunta, lblTemporizador;
    private JTextField txtRespuesta, txtNumeros;
    private JButton btnIniciar, btnSalir;
    private JCheckBox chkSuma, chkResta, chkMultiplicacion, chkDivision;
    private JRadioButton[] botonesFactor;
    private JRadioButton temporizadorApagado, temporizadorArriba, temporizadorAbajo;
    private ButtonGroup grupoFactor, grupoTemporizador;
    private Random random;
    private int num1, num2, respuesta, intentos = 0, correctos = 0;
    private Timer temporizador;
    private int contadorTiempo = 0;

    public FlashCardMath() {
        setTitle("Tarjetas de Matematicas");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        random = new Random();

        JPanel panelSuperior = new JPanel();
        lblIntentos = new JLabel("Intentos: 0");
        lblCorrectos = new JLabel("Correctos: 0");
        lblTemporizador = new JLabel("Tiempo: 0s");
        lblIntentos.setOpaque(true);
        lblCorrectos.setOpaque(true);
        lblTemporizador.setOpaque(true);
        lblIntentos.setBackground(Color.RED);
        lblCorrectos.setBackground(Color.RED);
        lblTemporizador.setBackground(Color.RED);
        lblIntentos.setForeground(Color.WHITE);
        lblCorrectos.setForeground(Color.WHITE);
        lblTemporizador.setForeground(Color.WHITE);
        panelSuperior.add(lblIntentos);
        panelSuperior.add(lblCorrectos);
        panelSuperior.add(lblTemporizador);
        add(panelSuperior, BorderLayout.NORTH);

        lblPregunta = new JLabel("", SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 24));
        lblPregunta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(lblPregunta, BorderLayout.CENTER);

        txtNumeros = new JTextField(10);
        txtNumeros.setEditable(false);
        txtNumeros.setHorizontalAlignment(JTextField.CENTER);
        add(txtNumeros, BorderLayout.WEST);

        JPanel panelConfiguracion = new JPanel(new GridLayout(1, 3));
        JPanel panelTipo = new JPanel(new GridLayout(4, 1));
        panelTipo.setBorder(BorderFactory.createTitledBorder("Tipo:"));
        chkSuma = new JCheckBox("Suma", true);
        chkResta = new JCheckBox("Resta");
        chkMultiplicacion = new JCheckBox("Multiplicacion");
        chkDivision = new JCheckBox("Division");
        panelTipo.add(chkSuma);
        panelTipo.add(chkResta);
        panelTipo.add(chkMultiplicacion);
        panelTipo.add(chkDivision);
        panelConfiguracion.add(panelTipo);

        JPanel panelFactor = new JPanel(new GridLayout(5, 2));
        panelFactor.setBorder(BorderFactory.createTitledBorder("Factor:"));
        botonesFactor = new JRadioButton[6];
        grupoFactor = new ButtonGroup();
        String[] factores = {"Aleatorio", "1", "2", "3", "7", "9"};
        for (int i = 0; i < factores.length; i++) {
            botonesFactor[i] = new JRadioButton(factores[i]);
            grupoFactor.add(botonesFactor[i]);
            panelFactor.add(botonesFactor[i]);
        }
        botonesFactor[0].setSelected(true);
        panelConfiguracion.add(panelFactor);

        JPanel panelTemporizador = new JPanel(new GridLayout(3, 1));
        panelTemporizador.setBorder(BorderFactory.createTitledBorder("Temporizador:"));
        temporizadorApagado = new JRadioButton("Apagado", true);
        temporizadorArriba = new JRadioButton("Encendido-Contar Arriba");
        temporizadorAbajo = new JRadioButton("Encendido-Contar Abajo");
        grupoTemporizador = new ButtonGroup();
        grupoTemporizador.add(temporizadorApagado);
        grupoTemporizador.add(temporizadorArriba);
        grupoTemporizador.add(temporizadorAbajo);
        panelTemporizador.add(temporizadorApagado);
        panelTemporizador.add(temporizadorArriba);
        panelTemporizador.add(temporizadorAbajo);
        panelConfiguracion.add(panelTemporizador);

        add(panelConfiguracion, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        btnIniciar = new JButton("Iniciar Práctica");
        btnSalir = new JButton("Salir");
        panelInferior.add(btnIniciar);
        panelInferior.add(btnSalir);
        add(panelInferior, BorderLayout.SOUTH);

        btnSalir.addActionListener(e -> System.exit(0));
        btnIniciar.addActionListener(e -> iniciarPractica());

        txtRespuesta = new JTextField(10);
        panelInferior.add(txtRespuesta);
        txtRespuesta.addActionListener(e -> verificarRespuesta());

        temporizador = new Timer(1000, e -> {
            contadorTiempo++;
            lblTemporizador.setText("Tiempo: " + contadorTiempo + "s");
        });
    }

    private void iniciarPractica() {
        num1 = random.nextInt(10) + 1;
        num2 = random.nextInt(10) + 1;

        if (chkSuma.isSelected()) {
            txtNumeros.setText(num1 + " + " + num2);
            lblPregunta.setText(num1 + " + " + num2 + " = ?");
            respuesta = num1 + num2;
        } else if (chkResta.isSelected()) {
            txtNumeros.setText(num1 + " - " + num2);
            lblPregunta.setText(num1 + " - " + num2 + " = ?");
            respuesta = num1 - num2;
        } else if (chkMultiplicacion.isSelected()) {
            txtNumeros.setText(num1 + " * " + num2);
            lblPregunta.setText(num1 + " * " + num2 + " = ?");
            respuesta = num1 * num2;
        } else if (chkDivision.isSelected()) {
            txtNumeros.setText(num1 + " / " + num2);
            lblPregunta.setText(num1 + " / " + num2 + " = ?");
            respuesta = num1 / num2;
        }

        contadorTiempo = 0;
        temporizador.start();
    }

    private void verificarRespuesta() {
        intentos++;
        int respuestaUsuario = Integer.parseInt(txtRespuesta.getText());
        if (respuestaUsuario == respuesta) {
            correctos++;
        }
        lblIntentos.setText("Intentos: " + intentos);
        lblCorrectos.setText("Correctos : " + correctos);
        txtRespuesta.setText("");
        iniciarPractica();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlashCardMath().setVisible(true));
    }
}