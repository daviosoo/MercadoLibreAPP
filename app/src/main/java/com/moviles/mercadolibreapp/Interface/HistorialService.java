package com.moviles.mercadolibreapp.Interface;

import com.moviles.mercadolibreapp.Model.Car;
import com.moviles.mercadolibreapp.Model.CarPost;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HistorialService {

    @POST("historial/historial.php")
    Call<String> insertHistorial(@Body ArrayList<CarPost> productosComprados);

    @GET("historial/historial.php")
    Call<ArrayList<Car>> getHistorial(@Query("identification") int identification);

}
