package TAREA_09;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}

class MainFrame extends JFrame {
    private StringBuilder messageData;
    private JTextArea textArea;

    public MainFrame() {
        messageData = new StringBuilder();
        textArea = new JTextArea(10, 20);
        textArea.setEditable(false);

        setTitle("Hello and Goodbye Buttons");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JButton helloButton = new JButton("Hello");
        JButton goodbyeButton = new JButton("Goodbye");

        helloButton.addActionListener(e -> {
            messageData.append("Hello\n");
            textArea.setText(messageData.toString());
        });

        goodbyeButton.addActionListener(e -> {
            messageData.append("Goodbye\n");
            textArea.setText(messageData.toString());
        });

        panel.add(helloButton);
        panel.add(goodbyeButton);
        panel.add(textArea);

        add(panel);
        setVisible(true);
    }
}