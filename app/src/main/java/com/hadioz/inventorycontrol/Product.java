package com.hadioz.inventorycontrol;

public class Product {
    private String id;
    private String name;
    private int price;
    private String code;
    private String image;
    private int stock;

    public void setName(String name) {this.name = name;}

    public void setCode(String code) {this.code = code;}

    public void setPrice(int price) {this.price = price;}

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
