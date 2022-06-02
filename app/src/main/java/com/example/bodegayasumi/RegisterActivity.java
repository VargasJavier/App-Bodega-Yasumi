package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextFullName, editTextEmail, editTextPassword, editTextPhone;
    RadioGroup rgrpGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inicializarControles();
    }

    public void inicializarControles(){
        editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        rgrpGenre = (RadioGroup) findViewById(R.id.rbtnGenre);
    }

    public void accionar(View view) throws JSONException {
        switch (view.getId()){
            case R.id.textViewIniciarSesion:
                this.onBackPressed();
            case R.id.btnRegistrarse:
                String fullName = editTextFullName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String phone = editTextPhone.getText().toString();
                RadioButton rbtnGenre = (RadioButton) findViewById(rgrpGenre.getCheckedRadioButtonId());
                String genre = rbtnGenre.getText().toString();

                String[] names = fullName.split(" ");

                register(names[0], email, password, names[1], names[2], phone, genre, 1, 1);
                // metodo para registrar
        }
    }

    public void register(String nombre, String correo, String contrasena, String apellidoPat, String apellidoMat, String telefono, String sexo, int esAdmin, int estado) throws JSONException {

        Intent intent = new Intent(this, LoginActivity.class);

        String url = "http://192.168.1.6:3000/api/usuarios/";

        JSONObject body = new JSONObject();
        body.put("nombre", nombre);
        body.put("correo", correo);
        body.put("contrasena", contrasena);
        body.put("apellidoPaterno", apellidoPat);
        body.put("apellidoMaterno", apellidoMat);
        body.put("telefono", telefono);
        body.put("sexo", sexo);
        body.put("isAdmin", esAdmin);
        body.put("estado", estado);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");

                            boolean estaRegistrado = jsonObject.getBoolean("estaRegistrado");

                            if(estaRegistrado){
                                startActivity(intent);
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

        Volley.newRequestQueue(this).add(request);
    }
}