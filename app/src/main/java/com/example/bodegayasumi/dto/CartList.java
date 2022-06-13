package com.example.bodegayasumi.dto;

import java.util.ArrayList;

public class CartList {

    private static ArrayList<CartItem> cartItems = new ArrayList<>();

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
}
