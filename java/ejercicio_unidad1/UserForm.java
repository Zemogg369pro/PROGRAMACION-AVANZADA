package ejercicio_unidad1;
import javax.swing.*;
import java.awt.*;

public class UserForm {

    private static DefaultListModel<String> listModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("User Information Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = createMainPanel();
        frame.add(mainPanel, BorderLayout.WEST);

        JList<String> employeeList = createEmployeeList();
        frame.add(new JScrollPane(employeeList), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(15);
        panel.add(nameField, gbc);

        // Occupation
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Occupation:"), gbc);
        gbc.gridx = 1;
        JTextField occupationField = new JTextField(15);
        panel.add(occupationField, gbc);

        // Age (Radio Buttons)
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        JPanel agePanel = new JPanel();
        ButtonGroup ageGroup = new ButtonGroup();
        JRadioButton ageUnder18 = new JRadioButton("Under 18");
        JRadioButton age18To65 = new JRadioButton("18 to 65");
        JRadioButton age65OrOver = new JRadioButton("65 or over");
        ageGroup.add(ageUnder18);
        ageGroup.add(age18To65);
        ageGroup.add(age65OrOver);
        agePanel.add(ageUnder18);
        agePanel.add(age18To65);
        agePanel.add(age65OrOver);
        panel.add(agePanel, gbc);

        // Employment (ComboBox)
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Employment:"), gbc);
        gbc.gridx = 1;
        String[] employmentOptions = {"Employed", "Unemployed"};
        JComboBox<String> employmentBox = new JComboBox<>(employmentOptions);
        panel.add(employmentBox, gbc);

        // US Citizen (Checkbox)
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("US Citizen:"), gbc);
        gbc.gridx = 1;
        JCheckBox citizenCheck = new JCheckBox();
        panel.add(citizenCheck, gbc);

        // Tax ID
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Tax ID:"), gbc);
        gbc.gridx = 1;
        JTextField taxField = new JTextField(15);
        panel.add(taxField, gbc);

        // Gender (Radio Buttons)
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel();
        ButtonGroup genderGroup = new ButtonGroup();
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        genderGroup.add(male);
        genderGroup.add(female);
        genderPanel.add(male);
        genderPanel.add(female);
        panel.add(genderPanel, gbc);

        // OK Button
        gbc.gridx = 1;
        gbc.gridy = 7;
        JButton okButton = new JButton("OK");
        panel.add(okButton, gbc);

        okButton.addActionListener(e -> {
            String name = nameField.getText();
            String occupation = occupationField.getText();
            String age = ageUnder18.isSelected() ? "Under 18" : age18To65.isSelected() ? "18 to 65" : "65 or over";
            String employment = (String) employmentBox.getSelectedItem();
            boolean isCitizen = citizenCheck.isSelected();
            String taxId = taxField.getText();
            String gender = male.isSelected() ? "Male" : "Female";

            String userInfo = String.format("Name: %s, Occupation: %s, Age: %s, Employment: %s, US Citizen: %s, Tax ID: %s, Gender: %s",
                    name, occupation, age, employment, isCitizen, taxId, gender);
            listModel.addElement(userInfo);
        });

        return panel;
    }

    private static JList<String> createEmployeeList() {
        listModel = new DefaultListModel<>();
        return new JList<>(listModel);
    }
}