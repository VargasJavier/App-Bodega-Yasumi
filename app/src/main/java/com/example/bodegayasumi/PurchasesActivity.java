package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
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

public class PurchasesActivity extends AppCompatActivity {

    private RecyclerView myList;
    private RecyclerView.LayoutManager layoutManager;
    CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        myList = findViewById(R.id.myPurchases);
        myList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        cartAdapter = new CartAdapter(CartList.obtenerCompras());
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(cartAdapter);
    }

    public void isMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}