package com.example.bodegayasumi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bodegayasumi.dto.CartList;
import com.example.bodegayasumi.services.PurchaseService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class PaymentActivity extends AppCompatActivity {
    private double subtotal, total;
    private static final int SHIPPING = 8;
    TextView txtSubtotal, txtTotal, txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.MAPS_API_KEY));
        }
        txtSubtotal = findViewById(R.id.txtPriceProducts);
        txtTotal = findViewById(R.id.txtPriceTotal);
        txtAddress = findViewById(R.id.txtAddress);
    }

    public void back(View view){
        finish();
    }

    public void newOrder(View view) throws JSONException {
        Intent intent = new Intent(this, CompletedActivity.class);
        PurchaseService purchaseService = new PurchaseService();

        JsonArrayRequest request = purchaseService.registrarVenta(CartList.obtenerTodos(), 24, this, intent);
        CartList.vaciarCarrito();

        Volley.newRequestQueue(this).add(request);
//        Snackbar.make(view, "Se compró con éxito, causa", Snackbar.LENGTH_LONG).show();
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

    public void changeMap(View view){
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                Place place = Autocomplete.getPlaceFromIntent(data);
                txtAddress.setText(place.getName());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                assert data != null;
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("ERROR", status.getStatusMessage());
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}