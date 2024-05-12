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
        setMinimumSize(new Dimension(400, 450));
        setModal(true);
        setLocationRelativeTo(parent);


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
                System.out.println("Cancel button clicked");
                dispose();
                System.exit(0);
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

        String nameRegex = "^[A-Z]{1}[a-z]+$";
        String surenameRegex = "^[A-Z]{1}[a-zA-Z]+$";
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$";
        String phoneRegex = "^(\\d{3}\\d{3}\\d{3})$";
        String adressRegex = "^[A-Z]{1}[a-z]+$";
        String passwordRegex = "^(?=.*\\d)(?=.*[A-Z]).{8,}$";

        if (name.isEmpty() || surename.isEmpty() || email.isEmpty() || phone.isEmpty() || adress.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the required fields!", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!name.matches(nameRegex)) {
            JOptionPane.showMessageDialog(null, "Name has to be in format [Name] example [Liam]", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!surename.matches(surenameRegex)) {
            JOptionPane.showMessageDialog(null, "Surename has to be in format [Surename] example [Smith]", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Email is in wrong fromat, example of correct email: [liam_smith123@gmail.com]", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!phone.matches(phoneRegex)) {
            JOptionPane.showMessageDialog(null, "Phone number has to be 9 digit number  example [123456789]");
            return;
        }

        if (!adress.matches(adressRegex)) {
            JOptionPane.showMessageDialog(null, "Adress has to be in format [Adress] example [Jecna]");
            return;
        }
        if (!password.matches(passwordRegex)) {
            JOptionPane.showMessageDialog(null, "Password has to be: at least 8 characters, contain at least one digit and one uppercase letter. example: [LiamSmith123]");
            return;
        }

        if (!confirmPassword(password, confirmPassword)) {
            return;
        }

        Register register = new Register();
        if(!register.isPhoneUnique(phone)){
            JOptionPane.showMessageDialog(null,"Phone number already exists.", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(name, surename, email, phone, adress, password);
        user.addUser(user);
        System.out.println("User was added");
        user.showusers();
        System.out.println("Users were showed");
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
