package com.example.bodegayasumi.services;

import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bodegayasumi.CompletedActivity;
import com.example.bodegayasumi.MainActivity;
import com.example.bodegayasumi.PaymentActivity;
import com.example.bodegayasumi.RegisterActivity;
import com.example.bodegayasumi.dto.CartItem;
import com.example.bodegayasumi.dto.User;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PurchaseService {

    public final String url = MainActivity.URL_API + "api/ventas";

    public JsonArrayRequest registrarVenta(ArrayList<CartItem> productos, int userId, PaymentActivity paymentActivity, Intent intent) throws JSONException {
//        JSONObject body = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        double montoTotal = 0.0;
        for(int i = 0; i < productos.size(); i++){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id_producto", productos.get(i).getProductId());
            jsonObject.put("sub_total", (productos.get(i).getQuantity() * productos.get(i).getPrice()));
            jsonObject.put("cantidad", productos.get(i).getQuantity());

            montoTotal += (productos.get(i).getQuantity() * productos.get(i).getPrice());

            jsonArray.put(jsonObject);
        }

        JSONObject jsonObject1 = new JSONObject();

        jsonObject1.put("id_usuario", userId);
        jsonObject1.put("montoTotal", montoTotal);
        jsonArray.put(jsonObject1);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObjectRes = response.getJSONObject(0);
                            boolean success = jsonObjectRes.getBoolean("success");

                            if(success){
                                paymentActivity.startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        return request;

    }

}
