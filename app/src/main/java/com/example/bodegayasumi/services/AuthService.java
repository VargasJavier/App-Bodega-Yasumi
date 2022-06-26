package com.example.bodegayasumi.services;

import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bodegayasumi.LoginActivity;
import com.example.bodegayasumi.MainActivity;
import com.example.bodegayasumi.RegisterActivity;
import com.example.bodegayasumi.dto.User;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthService {

    String url = MainActivity.URL_API + "api/usuarios";

    public JsonObjectRequest register(User user, RegisterActivity registerActivity, Intent intent) throws JSONException {

        JSONObject body = new JSONObject();
        body.put("nombre", user.getNombre());
        body.put("correo", user.getCorreo());
        body.put("contrasena", user.getContrasena());
        body.put("apellidoPaterno", user.getApellidoPat());
        body.put("apellidoMaterno", user.getApellidoPat());
        body.put("telefono", user.getTelefono());
        body.put("sexo", user.getSexo());
        body.put("isAdmin", user.getEsAdmin());
        body.put("estado", user.getEstado());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");

                            boolean estaRegistrado = jsonObject.getBoolean("estaRegistrado");

                            if(estaRegistrado){
                                registerActivity.startActivity(intent);
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

    public JsonObjectRequest login(String correo, String contrasena, LoginActivity loginActivity, Intent intent) throws JSONException {

        JSONObject body = new JSONObject();
        body.put("correo", correo);
        body.put("contrasena", contrasena);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url + "/sign-in", body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");

                            boolean estaAutenticado = jsonObject.getBoolean("estaAutenticado");

                            if(estaAutenticado){
                                loginActivity.startActivity(intent);
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
