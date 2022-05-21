package com.example.bodegayasumi;

public class Product {

    String nombre, descripcion, marca;
    double precio;
    int stock;

    public Product(String nombre, String descripcion, double precio, String marca, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.marca = marca;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getMarca() {
        return marca;
    }

    public int getStock() {
        return stock;
    }
}
