import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Register implements Serializable {
    private static final long serialVersionUID = 418272607240658501L;


    public Register() {
    }

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


    public static void saveUser(User user) throws IOException, ClassNotFoundException {
        if (isUnique(user.getEmail(), user.getPhone())) {
            String uniqueNumber = UUID.randomUUID().toString();
            File usersDir = new File("src/Users");
            if (!usersDir.exists()) {
                usersDir.mkdirs();
            }
            File file = new File(usersDir, "user_" + user.getSurename() + "_" + uniqueNumber + ".ser");
            try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
                stream.writeObject(user);
                System.out.println("User saved successfully.");
            }
        } else {
            System.out.println("Email or phone number is already in use. User not saved.");
        }
    }

    public static void displayAllUsers() throws IOException, ClassNotFoundException {
        File usersDir = new File("src/Users");
        if (!usersDir.exists() || !usersDir.isDirectory()) {
            throw new IOException("Users directory does not exist or is not a directory");
        }

        File[] files = usersDir.listFiles((dir, name) -> name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                    User user = (User) stream.readObject();
                    // Print user details
                    System.out.println("User details from file: " + file.getName());
                    System.out.println("Name: " + user.getName());
                    System.out.println("Surname: " + user.getSurename());
                    System.out.println("Bank number: " + user.getAccountNumber());
                    System.out.println("Email: " + user.getEmail());
                    System.out.println("Phone: " + user.getPhone());
                    System.out.println("Address: " + user.getAddress());
                    System.out.println("Password: " + user.getPassword());
                    System.out.println("-----");
                } catch (IOException | ClassNotFoundException e) {
                    throw new IOException();
                }
            }
        } else {
            System.out.println("No user files found.");
        }
    }
}
