package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class activity_cart extends AppCompatActivity {

    private RecyclerView myList;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<ProductCart> cartList = new ArrayList<>();
    CartAdapter cartAdapter;

    TextView tvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        loadData();

        myList = findViewById(R.id.myList);
        myList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        cartAdapter = new CartAdapter(cartList);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(cartAdapter);

        tvItems = findViewById(R.id.txtCantidad);
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("carrito", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        cartList = gson.fromJson(json, cartList.getClass());
    }

}