package com.example.bodegayasumi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/api/productos")
    Call<List<Products>> getProducts();

}
