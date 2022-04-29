package com.moviles.mercadolibreapp.Interface;

import com.moviles.mercadolibreapp.Model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CartService {

    @POST("cart/cart.php")
    Call<Producto> insertToCart(@Body Producto producto);

    @GET("cart/cart.php")
    Call<List<Producto>> getToCart(@Query("identification") int identificacion);

}