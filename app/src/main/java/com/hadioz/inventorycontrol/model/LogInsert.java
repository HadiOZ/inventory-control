package com.hadioz.inventorycontrol.model;

import com.hadioz.inventorycontrol.Product;
import com.hadioz.inventorycontrol.User;

public class LogInsert {
    private Product product;
    private User admin;
    private String action;
    private int amount;

    public void setProduct(String id) {
        this.product = new Product();
        this.product.setId(id);
    }

    public void setAdmin(String id) {
        this.admin = new User();
        this.admin.setId(id);
    }

    public void setAction(String action) {this.action = action;}

    public void setAmount(int amount) {this.amount = amount;}

    public int getAmount() {
        return amount;
    }

    public User getAdmin() {
        return admin;
    }

    public Product getProduct() {
        return product;
    }

    public String getAction() {
        return action;
    }
}
