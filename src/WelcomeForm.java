import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class is for just greeting the user if they got account or if they want to create one
 */
public class WelcomeForm extends JDialog{
    private JButton loginButton;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel WelcomePanel;


    public WelcomeForm(JFrame parent) {
        super(parent);
        setTitle("Welcome");
        setContentPane(WelcomePanel);
        setMinimumSize(new Dimension(400, 450));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setLocationRelativeTo(parent);



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm loginForm = new LoginForm(null);
                loginForm.setVisible(true);
                setVisible(false);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegisterForm registerForm = new RegisterForm(null);
                registerForm.setVisible(true);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JPanel getWelcomePanel() {
        return WelcomePanel;
    }

    public void setWelcomePanel(JPanel welcomePanel) {
        WelcomePanel = welcomePanel;
    }
}
