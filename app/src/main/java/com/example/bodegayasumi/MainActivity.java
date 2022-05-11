package com.example.bodegayasumi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    ProductsAdapter adapter;
    List<Products> productsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductsAdapter(productsList);
        recyclerView.setAdapter(adapter);

        fetchProducts();
    }
    private void fetchProducts() {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient.getRetrofitClient().getProducts().enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(@NonNull Call<List<Products>> call, @NonNull Response<List<Products>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("Error", ""+response.body());
                    productsList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Products>> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.i("Error",t.getMessage());
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void visitar(View v){
        Intent intent = new Intent(this, ItemDetail.class);
        startActivity(intent);
    }
}