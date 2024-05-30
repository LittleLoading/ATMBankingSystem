import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Account, place where we show user his name and balance, he can choose what he wants to do next, withdraw or send money
 */
public class Account extends JDialog {
    private JPanel accountForm;
    private JButton cancelButton;
    private JButton withdrawButton;
    private JButton sendButton;
    private JLabel usersName;
    private JLabel usersBalance;
    private User user;

    public Account(JFrame parent, User user) throws IOException, ClassNotFoundException {
        super(parent);
        setTitle("Your Account");
        setContentPane(accountForm);
        setMinimumSize(new Dimension(700, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setWelcomeName(user.getName() + " " + user.getSurename());
        setUsersBalance(user.getAccountBalance());


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });



        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                setVisible(false);
                SendForm sendForm = new SendForm(null, user);
                sendForm.setVisible(true);


            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();
            setVisible(false);
            WithdrawForm withdrawForm = new WithdrawForm(null, user);
            withdrawForm.setVisible(true);

            }
        });
        setVisible(true);
    }


    public void setWelcomeName(String username)
    {
        usersName.setText(username);
    }

    public void setUsersBalance(Double balance)
    {
        usersBalance.setText(balance.toString());
    }

    public JPanel getAccountForm() {
        return accountForm;
    }

    public void setAccountForm(JPanel accountForm) {
        this.accountForm = accountForm;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JButton getWithdrawButton() {
        return withdrawButton;
    }

    public void setWithdrawButton(JButton withdrawButton) {
        this.withdrawButton = withdrawButton;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public void setSendButton(JButton sendButton) {
        this.sendButton = sendButton;
    }


}
