import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JLabel jsFirstName;
    private JTextField textFieldFirstName;
    private JButton Value;
    private JPanel MainPanel;
    private JLabel jsLastName;
    private JTextField textFieldLastName;

    public Main(){
        setContentPane(MainPanel);
        setTitle("ATM Banking System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        Value.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstname = textFieldFirstName.getText();
                JOptionPane.showMessageDialog(Main.this, "Enter First Name:");
            }
        });
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");

        new Main();



    }
}