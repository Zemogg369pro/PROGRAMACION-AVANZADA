package TAREA_09;

import javax.swing.*;
import java.awt.*;

public class PersonForm extends JFrame {
    public PersonForm() {
        super("Hello World");
        setLayout(new BorderLayout());

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");
        JMenuItem showItem = new JMenuItem("Show");
        windowMenu.add(showItem);
        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        setJMenuBar(menuBar);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        // Componentes del formulario
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Person"));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(new JTextField(10));
        formPanel.add(new JLabel("Occupation:"));
        formPanel.add(new JTextField(10));

        formPanel.add(new JLabel("Age:"));
        JComboBox<String> ageCombo = new JComboBox<>(new String[]{"Under 18", "18 to 65", "65 or over"});
        formPanel.add(ageCombo);

        formPanel.add(new JLabel("Employment:"));
        JComboBox<String> employmentCombo = new JComboBox<>(new String[]{"employed", "unemployed", "self-employed"});
        formPanel.add(employmentCombo);

        JCheckBox usCitizenCheck = new JCheckBox("US Citizen");
        formPanel.add(usCitizenCheck);
        formPanel.add(new JTextField(10));

        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton maleButton = new JRadioButton("male");
        JRadioButton femaleButton = new JRadioButton("female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        formPanel.add(genderPanel);

        JButton okButton = new JButton("OK");
        formPanel.add(okButton);

        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.add(new JTextArea(), BorderLayout.CENTER);
        add(mainPanel);

        // Configurar ventana
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PersonForm::new);
    }
}
