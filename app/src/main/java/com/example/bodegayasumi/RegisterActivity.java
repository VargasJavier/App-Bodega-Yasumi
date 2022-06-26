package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bodegayasumi.dto.User;
import com.example.bodegayasumi.services.AuthService;

import org.json.JSONException;
public class RegisterActivity extends AppCompatActivity {
    EditText editTextFullName, editTextEmail, editTextPassword, editTextPhone;
    RadioGroup rgrpGenre;
    AuthService authService = new AuthService();

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

                User user = new User(names[0],email,password,names[1],names[2],phone,genre, 1,1);

                register(user);
        }
    }

    public void register(User user) throws JSONException {

        Intent intent = new Intent(this, LoginActivity.class);

        JsonObjectRequest request = authService.register(user, this, intent);

        Volley.newRequestQueue(this).add(request);
    }
}