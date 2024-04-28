public class User {

    private String name;
    private String surename;
    private String email;
    private String phone;
    private String address;
    private String password;


    public User(String name, String surename, String email, String phone, String address, String password) {
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }
    public void addUser(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
