import java.io.*;
import java.util.*;
import java.nio.file.*;


/**
 * Thsi class is the logic. Here we save users into files get user from files and also can send money
 * We check if user is unique and also check his login if its correct
 */
public class Register implements Serializable {
    private static final long serialVersionUID = 418272607240658501L;


    public Register() {
    }

    /**
     * Checks if the email and phoneNumebers are unique to create a new user
     */
    public static boolean isUnique(String email, String phoneNumber) throws IOException, ClassNotFoundException {
        File usersDir = new File("src/Users");
        if (!usersDir.exists() || !usersDir.isDirectory()) {
            throw new IOException("Users directory does not exist or is not a directory");
        }

        File[] files = usersDir.listFiles((dir, name) -> name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                    User user = (User) stream.readObject();
                    if (user.getEmail().equals(email) || user.getPhone().equals(phoneNumber)) {
                        return false;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new IOException("Error reading user data from file: " + file.getName(), e);
                }
            }
        }
        return true;
    }

    /**
     * enables people to login into their account
     */
    public static boolean login(String email, String password) throws IOException, ClassNotFoundException {
        File usersDir = new File("src/Users");
        if (!usersDir.exists() || !usersDir.isDirectory()) {
            throw new IOException("Users directory does not exist or is not a directory");
        }

        File[] files = usersDir.listFiles((dir, name) -> name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                    User user = (User) stream.readObject();
                    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        return true;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new IOException("Error reading user data from file: " + file.getName(), e);
                }
            }
        }
        return false;
    }


    /**
     * input is email and password by that it runs through the system if the user exist and if it does it gets it
     */
    public static User getUserByEmailAndPassword(String email, String password) throws IOException, ClassNotFoundException {
        File usersDir = new File("src/Users");
        if (!usersDir.exists() || !usersDir.isDirectory()) {
            throw new IOException("Users directory does not exist or is not a directory");
        }

        File[] files = usersDir.listFiles((dir, name) -> name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                    User user = (User) stream.readObject();
                    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        return user;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new IOException("Error reading user data from file: " + file.getName(), e);
                }
            }
        }
        return null;
    }

    /**
     * logic for sending money from one user to another
     */
    public static boolean sendMoney(String senderEmail, String receiverAccountNumber, double amount) throws IOException, ClassNotFoundException {
        User sender = getUserByEmail(senderEmail);
        User receiver = getUserByAccountNumber(receiverAccountNumber);

        if (sender == null || receiver == null) {
            return false;
        }

        if (sender.getAccountBalance() < amount) {
            return false;
        }

        if (sender.getTransactionHistory() == null) {
            sender.setTransactionHistory(new ArrayList<>());
        }
        if (receiver.getTransactionHistory() == null) {
            receiver.setTransactionHistory(new ArrayList<>());
        }

        sender.setAccountBalance(sender.getAccountBalance() - amount);
        Date currentDate = new Date();
        sender.addTransaction(new Transaction(sender.getAccountNumber(), receiver.getAccountNumber(), amount, currentDate)); // Passing current date

        receiver.setAccountBalance(receiver.getAccountBalance() + amount);
        receiver.addTransaction(new Transaction(sender.getAccountNumber(), receiver.getAccountNumber(), amount, currentDate)); // Passing current date

        saveUser(sender);
        saveUser(receiver);
        return true;
    }


    /**
     * Gets user by their Email
     */
    private static User getUserByEmail(String email) throws IOException, ClassNotFoundException {
        return getUser(user -> user.getEmail().equals(email));
    }

    /**
     *  Gets user by their accountNumber
     */
    private static User getUserByAccountNumber(String accountNumber) throws IOException, ClassNotFoundException {
        return getUser(user -> user.getAccountNumber().equals(accountNumber));
    }

    /**
     * iterates through files to get user
     */
    private static User getUser(UserPredicate predicate) throws IOException, ClassNotFoundException {
        File usersDir = new File("src/Users");
        if (!usersDir.exists() || !usersDir.isDirectory()) {
            throw new IOException("Users directory does not exist or is not a directory");
        }

        File[] files = usersDir.listFiles((dir, name) -> name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                    User user = (User) stream.readObject();
                    if (predicate.test(user)) {
                        return user;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new IOException("Error reading user data from file: " + file.getName(), e);
                }
            }
        }
        return null;
    }

    @FunctionalInterface
    private interface UserPredicate {
        boolean test(User user);
    }

    /**
     *Iterates trough system to get user by their BankAccount number
     */
    public static User getUserByBankAccount(String bankAccount) throws IOException, ClassNotFoundException {
        File usersDir = new File("src/Users");
        if (!usersDir.exists() || !usersDir.isDirectory()) {
            throw new IOException("Users directory does not exist or is not a directory");
        }

        File[] files = usersDir.listFiles((dir, name) -> name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                    User user = (User) stream.readObject();
                    if (user.getAccountNumber().equals(bankAccount)) {
                        return user;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new IOException("Error reading user data from file: " + file.getName(), e);
                }
            }
        }
        return null;
    }
    /**
     *  Saves user as sar object - serializable
     */
    public static void saveUser(User user) throws IOException {
        File usersDir = new File("src/Users");
        if (!usersDir.exists()) {
            usersDir.mkdirs();
        }

        File[] files = usersDir.listFiles((dir, name) -> name.endsWith(".ser"));
        File userFile = null;
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                    User existingUser = (User) stream.readObject();
                    if (existingUser.getEmail().equals(user.getEmail())) {
                        userFile = file;
                        break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new IOException("Error reading user data from file: " + file.getName(), e);
                }
            }
        }

        if (userFile == null) {
            String uniqueNumber = UUID.randomUUID().toString();
            userFile = new File(usersDir, "user_" + user.getSurename() + "_" + uniqueNumber + ".ser");
        }

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(userFile))) {
            stream.writeObject(user);
            System.out.println("User saved successfully.");
        }
    }
}
