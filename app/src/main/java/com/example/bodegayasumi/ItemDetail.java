package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemDetail extends AppCompatActivity {
    private int quantity, stock = 10;
    private TextView txtQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        enlazarControles();
    }

    private void enlazarControles() {
        txtQuantity = (TextView) findViewById(R.id.txtQuantity);
    }

    public void incrementar(View v){
        quantity = Integer.parseInt(txtQuantity.getText().toString());
        Log.i("Sumar: ", String.valueOf(quantity));
        if(quantity < stock) {
            quantity++;
            txtQuantity.setText(String.valueOf(quantity));
        }
    }

    public void decrementar(View v){
        quantity = Integer.parseInt(txtQuantity.getText().toString());
        Log.i("Restar: ", String.valueOf(quantity));
        if(quantity > 1) {
            quantity--;
            txtQuantity.setText(String.valueOf(quantity));
        }
    }
}