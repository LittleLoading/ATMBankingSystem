import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * SendForm, this class is used to send money from one account to another, uses logic in Register
 */
public class SendForm extends JDialog {
    private JPanel sendForm;
    private JTextField tfAccountNum1;
    private JTextField tfAccountNum2;
    private JButton sendButton;
    private JButton cancelButton;
    private JTextField tfAmount;
    private JLabel usersName;
    private JLabel usersBalance;
    private User currentUser;

    public SendForm(JFrame parent, User user) {
        super(parent);
        this.currentUser = user;
        setTitle("Your Account");
        setContentPane(sendForm);
        setMinimumSize(new Dimension(500, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setUsersName(user.getName() + " " + user.getSurename());
        setUsersBalance(String.valueOf(user.getAccountBalance()));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNum1 = tfAccountNum1.getText().trim();
                String accountNum2 = tfAccountNum2.getText().trim();
                String amountStr = tfAmount.getText().trim();

                if (accountNum1.length() != 8 || !accountNum1.matches("\\d{8}")) {
                    JOptionPane.showMessageDialog(SendForm.this, "Account Number 1 must be 8 digits.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (accountNum2.length() != 4 || !accountNum2.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(SendForm.this, "Account Number 2 must be 4 digits.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double amount;
                try {
                    amount = Double.parseDouble(amountStr);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(SendForm.this, "Amount must be greater than zero.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SendForm.this, "Invalid amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String bankAccount = accountNum1 + "/" + accountNum2;

                try {
                    User receiver = Register.getUserByBankAccount(bankAccount);
                    if (receiver != null) {
                        boolean success = Register.sendMoney(currentUser.getEmail(), bankAccount, amount);

                        if (success) {
                            JOptionPane.showMessageDialog(SendForm.this, "Money sent successfully.");
                            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
                            receiver.setAccountBalance(receiver.getAccountBalance() + amount);
                            setUsersBalance(String.valueOf(currentUser.getAccountBalance()));
                            dispose(); // Close the dialog
                            Account account = new Account(null,user);
                            account.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(SendForm.this, "Failed to send money. Please check account details and balance.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(SendForm.this, "Recipient's bank account does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SendForm.this, "An error occurred. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void setUsersName(String name) {
        usersName.setText(name);
    }

    public void setUsersBalance(String balance) {
        usersBalance.setText("Balance: " + balance);
    }
}
