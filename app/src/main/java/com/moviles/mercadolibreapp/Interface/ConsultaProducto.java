package com.moviles.mercadolibreapp.Interface;
import com.moviles.mercadolibreapp.Model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConsultaProducto {
    @GET("consultaImagen/imagen.php")
    Call<List<Producto>> getProduc();
}
