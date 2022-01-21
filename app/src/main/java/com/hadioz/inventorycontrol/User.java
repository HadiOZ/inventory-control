package com.hadioz.inventorycontrol;

public class User {
    private String id;
    private String name;
    private String username;
    private String password;
    private String position;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getPosition() {
        return position;
    }
}
