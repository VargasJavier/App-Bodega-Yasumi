package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.bodegayasumi.adapter.CartAdapter;
import com.example.bodegayasumi.dto.CartItem;
import com.example.bodegayasumi.dto.CartList;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView myList;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<CartItem> cartList = new ArrayList<>();
    CartAdapter cartAdapter;

    TextView tvItems, tvSubTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        loadData();

        myList = findViewById(R.id.myList);
        myList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        cartAdapter = new CartAdapter(CartList.obtenerTodos());
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(cartAdapter);

        tvItems = findViewById(R.id.txtCantidad);
        tvSubTotal = findViewById(R.id.txtSubTotal);

        tvItems.setText(String.valueOf(CartList.obtenerCantidadElementos()) + " productos");
        tvSubTotal.setText("S/. " + String.format("%.2f", CartList.obtenerSubTotal()));
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("carrito", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
//        cartList = gson.fromJson(json, cartList.getClass());
        Log.i("data", "Mostraremos el json");
        Log.i("data", json);
    }

    public void back(View view){
        finish();
    }

}