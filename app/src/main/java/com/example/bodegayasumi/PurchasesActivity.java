package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PurchasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);
    }

    public void isMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}