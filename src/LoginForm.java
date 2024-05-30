import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

/**
 * LoginForm, User after registration has to login him self to verify he knows his login data
 */
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
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(400, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        String email = tfEmail.getText();
        String password = pfPassword.getText();


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve email and password from text fields
                String email = tfEmail.getText().trim();
                String password = new String(pfPassword.getPassword()).trim(); // Assuming passwordField is a JPasswordField

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter email", "Try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter password", "Try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long currentTime = System.currentTimeMillis();
                if (failedAttempts >= MAX_ATTEMPTS && currentTime - lastAttemptTime < LOCKOUT_DURATION) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Too many failed attempts. Please wait for 10 seconds.");
                    return;
                }

                if (currentTime - lastAttemptTime > LOCKOUT_DURATION) {
                    failedAttempts = 0;
                }

                Register register = new Register();
                boolean loginSuccess = false;
                User user = null;
                try {
                    loginSuccess = register.login(email, password);
                    user = register.getUserByEmailAndPassword(email, password);
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(LoginForm.this, "An error occurred during login: " + ex.getMessage());
                    return;
                }

                if (loginSuccess) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login successful");
                    failedAttempts = 0;
                    dispose();
                    Account account = null;
                    try {
                        account = new Account(null,user);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
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
                dispose();
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public void setTfEmail(JTextField tfEmail) {
        this.tfEmail = tfEmail;
    }

    public JPasswordField getPfPassword() {
        return pfPassword;
    }

    public void setPfPassword(JPasswordField pfPassword) {
        this.pfPassword = pfPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(JButton btnLogin) {
        this.btnLogin = btnLogin;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(JButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public long getLastAttemptTime() {
        return lastAttemptTime;
    }

    public void setLastAttemptTime(long lastAttemptTime) {
        this.lastAttemptTime = lastAttemptTime;
    }
}
