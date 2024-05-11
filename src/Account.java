import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Account extends JDialog {
    private JPanel accountForm;
    private JButton cancelButton;
    private JButton withdrawButton;
    private JButton sendButton;


    DefaultListModel<String> listModel;

    public Account(JFrame parent) {
        super(parent);
        setTitle("Create New Account");
        setContentPane(accountForm);
        setMinimumSize(new Dimension(400, 450));
        setModal(true);
        setLocationRelativeTo(parent);


        //Cancel Button
        cancelButton.addActionListener(new ActionListener() {
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
