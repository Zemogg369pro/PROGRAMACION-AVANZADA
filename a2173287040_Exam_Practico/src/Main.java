import view.MainWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Main class of the application.
 */
public class Main {
    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Set custom UI properties for a more modern look
        try {
            // Use Nimbus look and feel for a more modern appearance
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // If Nimbus is not available, fall back to system look and feel
            if (!UIManager.getLookAndFeel().getName().equals("Nimbus")) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }

            // Customize Nimbus colors for a professional look
            UIManager.put("control", new Color(240, 240, 245));
            UIManager.put("info", new Color(242, 242, 247));
            UIManager.put("nimbusBase", new Color(51, 98, 140));
            UIManager.put("nimbusBlueGrey", new Color(100, 120, 140));
            UIManager.put("nimbusLightBackground", new Color(250, 250, 255));
            UIManager.put("text", new Color(30, 30, 30));

            // Improve button appearance
            UIManager.put("Button.arc", 8);
            UIManager.put("Component.arc", 8);
            UIManager.put("ProgressBar.arc", 8);
            UIManager.put("TextComponent.arc", 8);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and show the main window
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
