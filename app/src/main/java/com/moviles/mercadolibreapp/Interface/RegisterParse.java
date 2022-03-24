package com.moviles.mercadolibreapp.Interface;

import com.moviles.mercadolibreapp.Model.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterParse {
    @POST("login/login.php")
    Call<List<Register>> getLogin(@Body Register usuario);
}

