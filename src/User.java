import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

public class User implements Serializable {

    private String name;
    private String surename;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String accountNumber;

    Register register = new Register();

    HashSet<User> users = new HashSet<>();

    public User(String name, String surename, String email, String phone, String address, String password) {
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        setAccountNumber();
    }

    public void addUser(User user) {
        register.addUser(user);
    }

    public void showusers() {
        System.out.println(getUsers());
    }



    public HashSet<User> getUsers(){
        return register.getUsers();
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
            //Throw it has to be only letters
        }
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        if (surename.matches("^[A-Z]{1}[a-z]+$")) {
            this.surename = surename;
        } else {
            //Throw someshit
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.toLowerCase().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$")) {
            this.email = email.toLowerCase();
        } else {
            //Throw someshit
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.matches("^(\\d{3}\\d{3}\\d{3})$")) {
            this.phone = phone;
        } else {
            //throw some exepriton
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.toLowerCase().matches("^[A-Z]{1}[a-z]+$")) {
            this.address = address;
        } else {
            //throw some shit
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.matches("^(?=.*\\d)(?=.*[A-Z]).{8,}$\n")) {
            this.password = password;
        } else {
            //throw some shit
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber() {
        Random rn = new Random();
        int bankFirst = rn.nextInt(90000000) + 10000000;
        int bankLast = rn.nextInt(9000) + 1000;

        String bankLastString = String.valueOf(bankLast);

        String accountNumber = bankFirst + "/" + bankLastString;
        this.accountNumber = accountNumber;
    }
}
