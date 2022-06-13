package com.example.bodegayasumi.sin_uso;

import com.example.bodegayasumi.dto.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/api/productos")
    Call<List<Product>> getProducts();

}
