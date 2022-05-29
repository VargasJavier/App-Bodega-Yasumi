package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private RequestQueue queue;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    GridLayoutManager layoutManager;
    ProductsAdapter adapter;
    List<Product> productList = new ArrayList<>();

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
        adapter = new ProductsAdapter(productList, new ProductsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                mostrarDetalle(product);
            }
        });
        recyclerView.setAdapter(adapter);

        traerProductos();
    }

    private void mostrarDetalle(Product product){
        Intent intent = new Intent(this, ItemDetail.class);
        Bundle bundle = new Bundle();

        bundle.putString("nombre", product.getNombre());
        bundle.putString("descripcion", product.getDescripcion());
        bundle.putDouble("precio", product.getPrecio());
        bundle.putString("marca", product.getMarca());
        bundle.putInt("stock", product.getStock());

        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void traerProductos(){
        String url = "http://192.168.100.14:3000/api/productos";

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
                        String descripcion = jsonObject.getString("descripcion");
                        double precio = jsonObject.getDouble("precio");
                        JSONObject marca = jsonObject.getJSONObject("Marca");
                        String marcaNombre = marca.getString("nombre");
                        int stock = jsonObject.getInt("stock");

                        Product objeto = new Product(nombre, descripcion, precio, marcaNombre, stock);

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
}