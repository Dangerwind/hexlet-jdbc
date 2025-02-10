package io.hexlet;

public class User {
    private  Long id;
    private static String username;
    private  static String phone;

    public  User (String username1, String phone1) {
        username = username1;
        phone = phone1;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id1) {
        id = id1;
    }

    public static String getName() {
        return username;
    }
    public  String getPhone() {
        return phone;
    }

    public void setName(String newName) {
        username = newName;
    }

    public void setPhone(String newPhone) {
        phone = newPhone;
    }

}
