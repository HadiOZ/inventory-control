package com.hadioz.inventorycontrol;

public class Product {
    private String id;
    private String name;
    private int price;
    private String code;
    private String image;
    private int stock;

    public Product(String name, int price, String code) {
        this.name = name;
        this.price = price;
        this.code = code;
    }

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
