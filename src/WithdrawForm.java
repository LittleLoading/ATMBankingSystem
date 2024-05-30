import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class enables User to withdraw money from their account, nothing much to say about it
 * It also uses logic from Register
 */
public class WithdrawForm extends JDialog {
    private JPanel withdrawForm;
    private JTextField tfAmountToWithdraw;
    private JButton withdrawButton;
    private JButton cancelButton;
    private JLabel acBalanc;

    public WithdrawForm(JFrame parent,User currentUser) {
        super(parent);
        setTitle("Withdraw");
        setContentPane(withdrawForm);
        setMinimumSize(new Dimension(400, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setUsersBalance(currentUser.getAccountBalance());

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = tfAmountToWithdraw.getText().trim();

                double amount;
                try {
                    amount = Double.parseDouble(amountStr);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(WithdrawForm.this, "Amount must be greater than zero.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(WithdrawForm.this, "Invalid amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (currentUser.getAccountBalance() < amount) {
                    JOptionPane.showMessageDialog(WithdrawForm.this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
                try {
                    Register register = new Register();
                    register.saveUser(currentUser);
                    JOptionPane.showMessageDialog(WithdrawForm.this, "Withdrawal successful.");
                    dispose();

                    Account account = new Account(null,currentUser);
                    account.setVisible(true);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace(); // Handle or log exception
                    JOptionPane.showMessageDialog(WithdrawForm.this, "An error occurred. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
    public void setUsersBalance(Double balance)
    {
        acBalanc.setText("Balance: " + balance.toString());
    }
}
