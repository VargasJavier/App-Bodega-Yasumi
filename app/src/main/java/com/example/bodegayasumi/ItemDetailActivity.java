package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bodegayasumi.dto.CartItem;
import com.example.bodegayasumi.dto.CartList;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {
    private int quantity = 1, stock, idProducto;
    private TextView txtCategoria, txtNombreProducto, txtDescripcion, txtQuantity, txtStock, txtPrecio;
    private ImageView ivImagenDetalle;
    private String nombre = null, descripcion = null, marca = null, nombreImage = null;
    private double precio = 0.00;
//    private CartList cartList = new CartList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        enlazarControles();
        obtenerDatos();
    }

    private void enlazarControles() {
        txtCategoria = (TextView) findViewById(R.id.txtCategoria);
        txtNombreProducto = (TextView) findViewById(R.id.txtNombreProducto);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
        txtQuantity = (TextView) findViewById(R.id.txtQuantity);
        txtStock = (TextView) findViewById(R.id.txtStock);
        txtPrecio = (TextView) findViewById(R.id.txtPrecio);
        ivImagenDetalle = (ImageView) findViewById(R.id.imageView2);
    }

    public void obtenerDatos(){
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            idProducto = bundle.getInt("idProducto");
            nombre = bundle.getString("nombre");
            descripcion = bundle.getString("descripcion");
            precio = bundle.getDouble("precio");
            marca = bundle.getString("marca");
            nombreImage = bundle.getString("imagen");
            stock = bundle.getInt("stock");

            this.txtNombreProducto.setText(nombre);
            this.txtDescripcion.setText(descripcion);

            Picasso.get().load("http://192.168.1.2:3000/" + nombreImage).into(ivImagenDetalle);

            if(stock <= 10){
                this.txtStock.setText("¡Quedan solo " + stock + " unidades!");
            }else{
                this.txtStock.setText("¡" + stock + " unidades diponibles!");
            }

            this.txtPrecio.setText("S/. " + String.format("%.2f",precio));
        }else{
            Toast.makeText(this, "Oops parece que hubo un error", Toast.LENGTH_SHORT).show();
        }
    }

    public void incrementar(View v){
        quantity = Integer.parseInt(txtQuantity.getText().toString());
        Log.i("Sumar: ", String.valueOf(quantity));
        if(quantity < stock) {
            quantity++;
            txtQuantity.setText(String.valueOf(quantity));
        }
    }

    public void decrementar(View v){
        quantity = Integer.parseInt(txtQuantity.getText().toString());
        Log.i("Restar: ", String.valueOf(quantity));
        if(quantity > 1) {
            quantity--;
            txtQuantity.setText(String.valueOf(quantity));
        }
    }

    public void irACart(View v){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    public void addCart(View v){
        try {

            CartItem cartItem = new CartItem(nombre, nombreImage, precio, quantity, idProducto);
            CartList.agregarAlCarrito(cartItem);

            savePreferences(CartList.obtenerTodos());
            Snackbar mySnackbar = Snackbar.make(v, "Se agregó " + quantity + " productos", Snackbar.LENGTH_LONG);
            mySnackbar.setAction(R.string.cart, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    irACart(v);
                }
            });
            mySnackbar.show();
        }catch (Exception e){
            Log.i("Error", "Error: " + e.getMessage());
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void savePreferences(ArrayList<CartItem> cartList){
        SharedPreferences preferences = getSharedPreferences("carrito", Context.MODE_PRIVATE);
//        addProduct(cartItem);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = String.valueOf(gson.toJson(cartList));
        editor.putString("list", json);

        editor.apply();
    }

//    public int isExist(){
//        if(MainActivity.cartList.size() > 0){
//            for (int i = 0; i < MainActivity.cartList.size(); i++){
//                if(MainActivity.cartList.get(i).getProductId() == idProducto)
//                    return i;
//            }
//        }
//        return -1;
//    }

//    public void addProduct(CartItem cartItem){
//        int index = isExist();
//        if(index == -1){
//            MainActivity.cartList.add(cartItem);
//            return;
//        }
//        int oldQuantity = MainActivity.cartList.get(index).getQuantity();
//        MainActivity.cartList.get(index).setQuantity(oldQuantity + cartItem.getQuantity());
//    }
}