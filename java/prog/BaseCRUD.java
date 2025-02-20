package prog;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JToggleButton;
import java.awt.Color;

public class BaseCRUD extends JFrame {

 private static final long serialVersionUID = 1L;
 private JPanel contentPane;
 private JTextField textField;

 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     BaseCRUD frame = new BaseCRUD();
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 /**
  * Create the frame.
  */
 public BaseCRUD() {
  setForeground(Color.RED);
  setBackground(Color.WHITE);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 450, 300);
  contentPane = new JPanel();
  contentPane.setForeground(Color.RED);
  contentPane.setBackground(Color.WHITE);
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

  setContentPane(contentPane);
  contentPane.setLayout(null);

  JButton btnNewButton = new JButton("ALTA");
  btnNewButton.setForeground(Color.RED);
  btnNewButton.setBackground(Color.WHITE);
  btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
  btnNewButton.setBounds(33, 184, 85, 21);
  contentPane.add(btnNewButton);

  JTextArea textArea = new JTextArea();
  textArea.setForeground(Color.RED);
  textArea.setBackground(Color.WHITE);
  textArea.setBounds(33, 67, 352, 92);
  contentPane.add(textArea);

  JButton btnNewButton_1 = new JButton("ELIMINAR");
  btnNewButton_1.setForeground(Color.RED);
  btnNewButton_1.setBackground(Color.WHITE);
  btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 9));
  btnNewButton_1.setBounds(130, 184, 85, 21);
  contentPane.add(btnNewButton_1);

  JToggleButton btnNewButton_2 = new JToggleButton("New button");
  btnNewButton_2.setForeground(Color.RED);
  btnNewButton_2.setBackground(Color.WHITE);
  btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 10));
  btnNewButton_2.setBounds(320, 184, 85, 21);
  contentPane.add(btnNewButton_2);

  JButton btnNewButton_1_1 = new JButton("MOVER");
  btnNewButton_1_1.setForeground(Color.RED);
  btnNewButton_1_1.setBackground(Color.WHITE);
  btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
  btnNewButton_1_1.setBounds(225, 184, 85, 21);
  contentPane.add(btnNewButton_1_1);

  JButton btnNewButton_1_2 = new JButton("New button");
  btnNewButton_1_2.setForeground(Color.RED);
  btnNewButton_1_2.setBackground(Color.WHITE);
  btnNewButton_1_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
  btnNewButton_1_2.setBounds(163, 215, 85, 21);
  contentPane.add(btnNewButton_1_2);

  textField = new JTextField();
  textField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
  textField.setForeground(Color.RED);
  textField.setBackground(Color.WHITE);
  textField.setBounds(33, 38, 108, 33);
  contentPane.add(textField);
  textField.setColumns(10);
 }
}
