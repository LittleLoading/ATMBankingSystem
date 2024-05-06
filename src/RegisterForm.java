import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JDialog {
    private JTextField tfName;
    private JTextField tfSurename;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAdress;
    private JPasswordField pfConfirmPassword;
    private JPasswordField pfPassword;
    private JCheckBox maleCheckBox;
    private JCheckBox femaleCheckBox;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;

    public RegisterForm(JFrame parent) {
        super(parent);
        setTitle("Create New Account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(350, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);

        //Register Button
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        //Cancel Button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public void registerUser() {
        String name = tfName.getText();
        String surename = tfSurename.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String adress = tfAdress.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (name.isEmpty() || surename.isEmpty() || email.isEmpty() || phone.isEmpty() || adress.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the required fields!", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
  /*      if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Try again", JOptionPane.ERROR_MESSAGE);
        }

   */

        if (name != null && surename != null && email != null && phone != null && adress != null && password != null && confirmPassword != null && confirmPassword(password, confirmPassword)) {
            User user = new User(name, surename,email,phone,adress,password);
        }
    }

    public boolean confirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Try again", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
}
