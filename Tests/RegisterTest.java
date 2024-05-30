import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void isUnique() {
        try {
            assertTrue(Register.isUnique("newuser@example.com", "1234567890"));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void login() {
        try {
            assertFalse(Register.login("john@example.com", "password"));
            assertFalse(Register.login("john@example.com", "wrongpassword"));
            assertFalse(Register.login("nonexistent@example.com", "password"));
        } catch (ClassNotFoundException | IOException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

}