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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
//import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
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

        //fetchProducts();
        traerProductos();
    }

    private void traerProductos(){
        String url = "http://192.168.1.7:3000/api/productos";

        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String nombre = jsonObject.getString("nombre");
                        Toast.makeText(MainActivity.this, String.valueOf(jsonArray.getJSONObject(i)), Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(request);
    }

    private void fetchProducts() {
//        progressBar.setVisibility(View.VISIBLE);
//        RetrofitClient.getRetrofitClient().getProducts().enqueue(new Callback<List<Products>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<Products>> call, @NonNull Response<List<Products>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    Log.i("Error", ""+response.body());
//                    productsList.addAll(response.body());
//                    adapter.notifyDataSetChanged();
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//            @Override
//            public void onFailure(@NonNull Call<List<Products>> call, @NonNull Throwable t) {
//                progressBar.setVisibility(View.GONE);
//                Log.i("Error",t.getMessage());
//                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    public void visitar(View v){
        Intent intent = new Intent(this, ItemDetail.class);
        startActivity(intent);
    }
}