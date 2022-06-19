package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class PaymentActivity extends AppCompatActivity {
    private double subtotal, total;
    private static final int SHIPPING = 8;
    TextView txtSubtotal, txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        txtSubtotal = findViewById(R.id.txtPriceProducts);
        txtTotal = findViewById(R.id.txtPriceTotal);
    }

    public void back(View view){
        finish();
    }

    public void newOrder(View view){
        Snackbar.make(view, "Se compró con éxito, causa", Snackbar.LENGTH_LONG).show();
    }

    public void obtenerDatos(){
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            subtotal = bundle.getDouble("subtotal");
            total = subtotal + PaymentActivity.SHIPPING;
            this.txtSubtotal.setText("S/. " + String.format("%.2f",subtotal));
            this.txtTotal.setText("S/. " + String.format("%.2f",total));
        }
    }
}