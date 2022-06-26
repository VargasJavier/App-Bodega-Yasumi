package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bodegayasumi.services.AuthService;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    AuthService authService = new AuthService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarControles();
    }

    private void inicializarControles(){
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    public void accionar(View view) throws JSONException {
        switch (view.getId()){
            case R.id.textViewRegistrarme:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLogin:
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                login(email, password);
                break;
        }
    }

    public void login(String correo, String contrasena) throws JSONException {

        Intent intent = new Intent(this, MainActivity.class);

        JsonObjectRequest request = authService.login(correo, contrasena, this, intent);

        Volley.newRequestQueue(this).add(request);
    }
}