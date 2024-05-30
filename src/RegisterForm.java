import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegisterForm extends JDialog {
    private JTextField tfName;
    private JTextField tfSurename;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAdress;
    private JPasswordField pfConfirmPassword;
    private JPasswordField pfPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;
    private boolean registered =false;

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
                if(registered) {
                    LoginForm loginForm = new LoginForm(null);
                    loginForm.setVisible(true);
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



    public void registerUser() {
        String name = tfName.getText();
        String surname = tfSurename.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String address = tfAdress.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        String nameRegex = "^[A-Z]{1}[a-z]+$";
        String surnameRegex = "^[A-Z]{1}[a-zA-Z]+$";
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$";
        String phoneRegex = "^(\\d{9})$";
        String addressRegex = "^[A-Z]{1}[a-z]+$";
        String passwordRegex = "^(?=.*\\d)(?=.*[A-Z]).{8,}$";

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the required fields!", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!name.matches(nameRegex)) {
            JOptionPane.showMessageDialog(null, "Name has to be in format [Name] example [Liam]", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!surname.matches(surnameRegex)) {
            JOptionPane.showMessageDialog(null, "Surname has to be in format [Surname] example [Smith]", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Email is in wrong format, example of correct email: [liam_smith123@gmail.com]", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!phone.matches(phoneRegex)) {
            JOptionPane.showMessageDialog(null, "Phone number has to be a 9 digit number example [123456789]", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!address.matches(addressRegex)) {
            JOptionPane.showMessageDialog(null, "Address has to be in format [Address] example [Jecna]", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.matches(passwordRegex)) {
            JOptionPane.showMessageDialog(null, "Password has to be: at least 8 characters, contain at least one digit and one uppercase letter. example: [LiamSmith123]", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!confirmPassword(password, confirmPassword)) {
            return;
        }

        Register register = new Register();
        try {
            if (!register.isUnique(email, phone)) {
                JOptionPane.showMessageDialog(null, "Email or phone number already exists.", "Try again", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create new user and save it
            User user = new User(name,surname,email,phone,address,password);
            Register.saveUser(user);
            JOptionPane.showMessageDialog(null, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            register.displayAllUsers();
            registered = true;


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while saving the user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean confirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Try again", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }



    public JTextField getTfName() {
        return tfName;
    }

    public void setTfName(JTextField tfName) {
        this.tfName = tfName;
    }

    public JTextField getTfSurename() {
        return tfSurename;
    }

    public void setTfSurename(JTextField tfSurename) {
        this.tfSurename = tfSurename;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public void setTfEmail(JTextField tfEmail) {
        this.tfEmail = tfEmail;
    }

    public JTextField getTfPhone() {
        return tfPhone;
    }

    public void setTfPhone(JTextField tfPhone) {
        this.tfPhone = tfPhone;
    }

    public JTextField getTfAdress() {
        return tfAdress;
    }

    public void setTfAdress(JTextField tfAdress) {
        this.tfAdress = tfAdress;
    }

    public JPasswordField getPfConfirmPassword() {
        return pfConfirmPassword;
    }

    public void setPfConfirmPassword(JPasswordField pfConfirmPassword) {
        this.pfConfirmPassword = pfConfirmPassword;
    }

    public JPasswordField getPfPassword() {
        return pfPassword;
    }

    public void setPfPassword(JPasswordField pfPassword) {
        this.pfPassword = pfPassword;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public void setBtnRegister(JButton btnRegister) {
        this.btnRegister = btnRegister;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(JButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    public JPanel getRegisterPanel() {
        return registerPanel;
    }

    public void setRegisterPanel(JPanel registerPanel) {
        this.registerPanel = registerPanel;
    }
}
