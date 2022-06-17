package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.ArrayList;
import java.util.List;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.bodegayasumi.adapter.ProductAdapter;
import com.example.bodegayasumi.dto.CartItem;
import com.example.bodegayasumi.dto.Product;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private RequestQueue queue;
    private int idProducto = 0;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    GridLayoutManager layoutManager;
    ProductAdapter adapter;
    List<Product> productList = new ArrayList<>();
    public static List<CartItem> cartList = new ArrayList<>();
    public static final String URL_API = "http://192.168.1.2:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == -1 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductAdapter(productList, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                mostrarDetalle(product);
            }
        });
        recyclerView.setAdapter(adapter);
//        savePreferences();
        traerProductos();
        Log.i("info", "Estamos en el main");
    }

    private void mostrarDetalle(Product product){
        Intent intent = new Intent(this, ItemDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("idProducto", product.getProductId());
        bundle.putString("nombre", product.getName());
        bundle.putString("descripcion", product.getDescription());
        bundle.putDouble("precio", product.getPrice());
        bundle.putString("marca", product.getBrand());
        bundle.putString("imagen", product.getImageName());
        bundle.putInt("stock", product.getStock());

        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void irACart(View v){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    private void traerProductos(){
        String url = URL_API + "/api/productos";

        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for(int i = 0; i < jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idProducto = jsonObject.getInt("idProducto");
                        String nombre = jsonObject.getString("nombre");
                        String descripcion = jsonObject.getString("descripcion");
                        double precio = jsonObject.getDouble("precio");
                        JSONObject marca = jsonObject.getJSONObject("Marca");
                        JSONObject imagen = jsonObject.getJSONObject("Imagen");
                        String marcaNombre = marca.getString("nombre");
                        String imagenNombre = imagen.getString("nombre");
                        int stock = jsonObject.getInt("stock");

                        Product objeto = new Product(idProducto, stock, nombre, descripcion, marcaNombre, imagenNombre, precio);

                        productList.add(objeto);

                        adapter.notifyDataSetChanged();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void savePreferences(){
        SharedPreferences preferences = getSharedPreferences("carrito", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartList);
        editor.putString("list", json);

        editor.apply();
    }

}