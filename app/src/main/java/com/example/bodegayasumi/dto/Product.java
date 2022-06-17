package com.example.bodegayasumi.dto;

public class Product {

    private int productId, stock;
    private String name, description, brand, imageName;
    private double price;

    public Product(int productId, int stock, String name, String description, String brand, String imageName, double price) {
        this.productId = productId;
        this.stock = stock;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.imageName = imageName;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
