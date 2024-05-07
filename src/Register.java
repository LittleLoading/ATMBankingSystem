import java.util.HashSet;

public class Register {


    private User user;
    private HashSet users = new HashSet<User>();
    public Register(User user) {
        this.user = user;
        this.users = users;
    }

    public void addUser(){
        users.add(user);
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashSet getUsers() {
        return users;
    }

    public void setUsers(HashSet users) {
        this.users = users;
    }
}
