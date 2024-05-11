import java.io.*;
import java.util.HashSet;

public class Register implements Serializable {


    private HashSet users = new HashSet<User>();


    public void addUser(User user) {
        users.add(user);
        saveToFile("karel");
    }

    public HashSet<User> getAllUsers() {
        loadFromFile("karel");
        return users;
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream ouputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ouputStream.writeObject(users);
            System.out.println("Users saved to file: " + fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            users = (HashSet<User>) inputStream.readObject();
            System.out.println("Users loaded from file: " + fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Register: " + '\n' +
                " users: " + users + '\n';
    }
}
