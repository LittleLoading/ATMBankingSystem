import java.io.Serializable;
import java.util.*;

/**
 * user has a lot of variables that he has to contain
 * Is the element that we use everywere and its the main reason this has been created
 * its all about USERS!
 */
public class User implements Serializable {

    private static final long serialVersionUID = -2584696864553332093L;


    private String name;
    private String surename;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String accountNumber;
    private double accountBalance;
    private List<Transaction> transactionHistory = new ArrayList<>();



    public User(String name, String surename, String email, String phone, String address, String password, double accountBalance) {
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.accountBalance = accountBalance;
        setAccountNumber();
        this.transactionHistory = new ArrayList<>();

    }

    @Override
    public String toString() {
        return "User: " + '\n' +
                " name: " + name + '\n' +
                " surename: " + surename + '\n' +
                " email: " + email + '\n' +
                " phone: " + phone + '\n' +
                " address: " + address + '\n' +
                " password: " + password + '\n' +
                " accountNumber: " + accountNumber + '\n';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches("^[A-Z]{1}[a-z]+$")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name must contain only alphanumeric characters");
        }
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        if (surename.matches("^[A-Z]{1}[a-z]+$")) {
            this.surename = surename;
        } else {
            throw new IllegalArgumentException("Surename must contain only alphanumeric characters");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.toLowerCase().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$")) {
            this.email = email.toLowerCase();
        } else {
            throw new IllegalArgumentException("Email must contain only alphanumeric characters");
        }
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.matches("^(\\d{3}\\d{3}\\d{3})$")) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Phone number must contain only alphanumeric characters");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.toLowerCase().matches("^[A-Z]{1}[a-z]+$")) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("Address must contain only alphanumeric characters");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.matches("^(?=.*\\d)(?=.*[A-Z]).{8,}$")) {
            this.password = password;
        } else {

        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber() {
        Random rn = new Random();
        int bankFirst = rn.nextInt( 90000000) + 10000000;
        int bankLast = rn.nextInt(9000) + 1000;

        String bankLastString = String.valueOf(bankLast);

        String accountNumber = bankFirst + "/" + bankLastString;
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
