package com.example.bodegayasumi.dto;

import java.util.ArrayList;

public class CartList {

    private static ArrayList<CartItem> cartItems = new ArrayList<>();
    private static ArrayList<CartItem> purchaseItems = new ArrayList<>();

    public static CartItem obtenerProducto(int productId){
        CartItem cartItem = null;

        for(int i = 0; i < cartItems.size(); i++){
            if(cartItems.get(i).getProductId() == productId){
                cartItem = cartItems.get(i);
            }
        }

        return cartItem;
    }

    public static void agregarAlCarrito(CartItem cartItem){
        if(cartItems.size() > 0){
            if (verificarExistencia(cartItem.getProductId())){
                CartItem cartItemToUpdate = obtenerProducto(cartItem.getProductId());
                cartItemToUpdate.setQuantity(cartItem.getQuantity());
            }else{
                cartItems.add(cartItem);
            }
        }else{
            cartItems.add(cartItem);
        }
    }

    public static boolean verificarExistencia(int productId){

       return obtenerProducto(productId) != null ? true : false;
    }

    public static ArrayList<CartItem> obtenerTodos(){
        return cartItems;
    }

    public static ArrayList<CartItem> obtenerCompras(){
        return purchaseItems;
    }

    public static int obtenerCantidadElementos(){
        int cantidad = 0;

        for(int i = 0; i < cartItems.size(); i++){
            cantidad += cartItems.get(i).getQuantity();
        }

        return cantidad;
    }

    public static double obtenerSubTotal(){
        double subTotal = 0.0;

        for(int i = 0; i < cartItems.size(); i++){
            subTotal += cartItems.get(i).getPrice() * cartItems.get(i).getQuantity();
        }

        return subTotal;
    }

    public static void vaciarCarrito(){
        purchaseItems.addAll(cartItems);
        cartItems = new ArrayList<>();
    }
}
