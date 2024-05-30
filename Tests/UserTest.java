import java.util.Date;

import static org.junit.Assert.*;

public class UserTest {

    @org.junit.Test
    public void testToString() {
    }

    @org.junit.Test
    public void getName() {
    }

    @org.junit.Test
    public void setName() {
    }

    @org.junit.Test
    public void getSurename() {
    }

    @org.junit.Test
    public void setSurename() {
    }

    @org.junit.Test
    public void getEmail() {
    }

    @org.junit.Test
    public void setEmail() {
    }

    @org.junit.Test
    public void getTransactionHistory() {
    }

    @org.junit.Test
    public void addTransaction() {
        User user = new User("John", "Doe", "john@example.com", "123456789", "123 Main St", "password", 1000.0);

        Date date = new Date();
        String sender = "john@example.com";
        String receiver = "recipient@example.com";
        double amount = 100.0;
        Transaction transaction = new Transaction(sender,receiver,amount,date);

        user.addTransaction(transaction);

        assertEquals(1, user.getTransactionHistory().size());
        assertEquals(transaction, user.getTransactionHistory().get(0));
    }

    @org.junit.Test
    public void setPassword() {
        User user = new User("John", "Doe", "john@example.com", "123456789", "123 Main St", "", 1000.0);

        String validPassword = "Password123";
        user.setPassword(validPassword);

        assertEquals(validPassword, user.getPassword());
    }


    @org.junit.Test
    public void setAccountNumber() {
        User user = new User("John", "Doe", "john@example.com", "123456789", "123 Main St", "password", 1000.0);
        assertNotNull(user.getAccountNumber());

    }

}