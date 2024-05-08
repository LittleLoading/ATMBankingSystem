import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Account extends JDialog{
    private JPanel accountForm;
    private JButton cancelButton;
    private JButton withdrawButton;
    private JButton sendButton;


    DefaultListModel <String> listModel;

    public Account(JFrame parent){
        super(parent);
        setTitle("Create New Account");
        setContentPane(accountForm);
        setMinimumSize(new Dimension(400  , 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
    }



}
