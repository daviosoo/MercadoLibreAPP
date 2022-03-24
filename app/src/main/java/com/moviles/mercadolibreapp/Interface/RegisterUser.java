package com.moviles.mercadolibreapp.Interface;

import com.moviles.mercadolibreapp.Model.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterUser {
    @POST("register/register.php")
    Call<String> setUser(@Body Register usuario);
}
