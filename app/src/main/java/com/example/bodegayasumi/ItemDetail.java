package com.example.bodegayasumi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetail extends AppCompatActivity {
    private int quantity, stock = 10;
    private TextView txtNombreProducto, txtDescripcion, txtQuantity, txtStock, txtPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        enlazarControles();
        obtenerDatos();
    }

    private void enlazarControles() {
        txtNombreProducto = (TextView) findViewById(R.id.txtNombreProducto);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
        txtQuantity = (TextView) findViewById(R.id.txtQuantity);
        txtStock = (TextView) findViewById(R.id.txtStock);
        txtPrecio = (TextView) findViewById(R.id.txtPrecio);
    }

    public void obtenerDatos(){
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            String nombre = bundle.getString("nombre");
            String descripcion = bundle.getString("descripcion");
            double precio = bundle.getDouble("precio");
            String marca = bundle.getString("marca");
            int stock = bundle.getInt("stock");

            this.txtNombreProducto.setText(nombre);
            this.txtDescripcion.setText(descripcion);

            if(stock <= 10){
                this.txtStock.setText("¡Quedan solo " + String.valueOf(stock) + " unidades!");
            }else{
                this.txtStock.setText("¡" + String.valueOf(stock) + " unidades diponibles!");
            }

            this.txtPrecio.setText("S/. " + String.format("%.2f",precio));
        }else{
            Toast.makeText(this, "Oops parece que hubo un error", Toast.LENGTH_SHORT);
        }
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