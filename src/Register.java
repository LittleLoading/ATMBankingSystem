import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class Register implements Serializable {

    private HashMap<Character, HashSet<User>> userMap;
    private static final String USERS_FOLDER = "Users";
    private static final long serialVersionUID = 1113799434508676095L;


    public Register() {
        userMap = new HashMap<>();
    }

    public void addUser(User user) {
        if (!isPhoneUnique(user.getPhone())) {
            System.out.println("Phone number already exists.");
            return;
        }

        char firstLetter = Character.toUpperCase(user.getSurename().charAt(0));
        if (!userMap.containsKey(firstLetter)) {
            userMap.put(firstLetter, new HashSet<>());
        }
        userMap.get(firstLetter).add(user);
        saveToFile(user);
    }

    public boolean isPhoneUnique(String phone) {
        for (HashSet<User> users : userMap.values()) {
            for (User user : users) {
                if (user.getPhone().equals(phone)) {
                    return false;
                }
            }
        }

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(USERS_FOLDER))) {
            for (Path path : directoryStream) {
                User user = readUserFromFile(path);
                if (user != null && user.getPhone().equals(phone)) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private User readUserFromFile(Path path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveToFile(User user) {

        String folderName = "Users";
        Path folderPath = Paths.get(folderName);
        if(!Files.exists(folderPath)){
            try {
                Files.createDirectories(folderPath);
            }catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }


        String fileName = folderName + "/user_" + user.getSurename() + "_" + UUID.randomUUID() + ".ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashSet<User> getUsers() {
        HashSet<User> allUsers = new HashSet<>();
        for (HashSet<User> users : userMap.values()) {
            allUsers.addAll(users);
        }
        return allUsers;
    }
}
