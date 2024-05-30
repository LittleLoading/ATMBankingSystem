import java.io.Serializable;
import java.util.Date;

/**
 * This class is for tracking transactions on users bank accounts
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender;
    private String receiver;
    private double amount;
    private Date date;

    public Transaction(String sender, String receiver, double amount, Date date) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
