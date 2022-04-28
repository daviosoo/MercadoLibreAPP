package com.moviles.mercadolibreapp.Interface;

import com.moviles.mercadolibreapp.Model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CartService {

    @POST("cart/cart.php")
    Call<Car> insertToCart(@Body Car producto);

    @GET("cart/cart.php")
    Call<List<Car>> getToCart(@Query("identification") int identificacion);

}