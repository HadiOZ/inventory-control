package com.hadioz.inventorycontrol;

public class Log {
    private String id;
    private User  admin;
    private char action;
    private String date;
    private int amount;

    public Log(String admin, char action, int amount) {
        this.admin.setId(admin);
        this.action = action;
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAdmin(String admin) {
        this.admin.setName(admin);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public User getAdmin() {
        return admin;
    }

    public char getAction() {
        return action;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
}
