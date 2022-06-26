package com.example.bodegayasumi.services;

import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bodegayasumi.MainActivity;
import com.example.bodegayasumi.adapter.ProductAdapter;
import com.example.bodegayasumi.dto.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProductService {

    public final String url = MainActivity.URL_API + "api/productos";

    public JsonObjectRequest listProducts(ProgressBar progressBar, ProductAdapter adapter, List<Product> productList){

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

        return request;
    }
}
