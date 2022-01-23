package com.hadioz.inventorycontrol;

public class Log {
    private String id;
    private String  admin;
    private char action;
    private String date;
    private int amount;

    public Log(String admin, char action, int amount) {
        this.admin = admin;
        this.action = action;
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getAdmin() {
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
