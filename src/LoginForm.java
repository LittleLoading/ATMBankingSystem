import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JPanel loginPanel;
    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCKOUT_DURATION = 10000; // 10s
    private int failedAttempts = 0;
    private long lastAttemptTime = 0;


    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Create New Account");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(400, 450));
        setModal(true);
        setLocationRelativeTo(parent);

        String email = tfEmail.getText();
        String password = pfPassword.getText();


        //Login button
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter email","try again", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(password.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter password", "Try again",JOptionPane.ERROR_MESSAGE);
                return;
            }
            long currentTime = System.currentTimeMillis();
            if(failedAttempts >= MAX_ATTEMPTS && currentTime - lastAttemptTime < LOCKOUT_DURATION){
                JOptionPane.showMessageDialog(LoginForm.this, "Too many failed attempts. Please wait for 10 seconds.");
            }
            if(currentTime-lastAttemptTime > LOCKOUT_DURATION){
                failedAttempts = 0;
            }

            Register register = new Register();
            boolean loginSuccess = false;
                try {
                    loginSuccess = register.login(email, password);
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(LoginForm.this, "An error occurred during login: " + ex.getMessage());
                    return;
                }
                if (loginSuccess) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login successful");
                    failedAttempts = 0;
                    dispose();
                    Account account = new Account();
                    account.setVisible(true);
                } else {
                    failedAttempts++;
                    lastAttemptTime = currentTime;
                    JOptionPane.showMessageDialog(LoginForm.this, "Login failed. Attempts remaining: " + (MAX_ATTEMPTS - failedAttempts));
                }
            }
        });

        //Cancel Button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel button clicked");
                dispose();
                System.exit(0);
            }
        });
        setVisible(true);
    }


}
