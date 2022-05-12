package com.moviles.mercadolibreapp.Interface;

import com.moviles.mercadolibreapp.Model.Car;
import com.moviles.mercadolibreapp.Model.CarPost;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CartService {

    @POST("cart/cart.php")
    Call<String> insertToCart(@Body CarPost producto);

    @GET("cart/cart.php")
    Call<ArrayList<Car>> getToCart(@Query("identification") int identificacion);

    @DELETE("cart/cart.php")
    Call<String> deleteFromCart(@Query("identification") int identificacion, @Query("idProducto") int idProducto);

    @DELETE("cart/cart.php")
    Call<String> deleteFromCart(@Query("identification") int identificacion);

}
