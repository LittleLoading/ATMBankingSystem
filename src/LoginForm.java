import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JDialog{
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JPanel loginPanel;

    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Create New Account");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(400  , 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);


        //Login button
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Cancel button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


}
