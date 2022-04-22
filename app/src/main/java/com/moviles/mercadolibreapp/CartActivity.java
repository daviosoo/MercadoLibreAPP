package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.moviles.mercadolibreapp.Interface.CartService;
import com.moviles.mercadolibreapp.Model.Producto;
import com.moviles.mercadolibreapp.databinding.ActivityCartBinding;

import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CartActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityCartBinding activityCartBinding;
    String status;
    int identificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = activityCartBinding.getRoot();
        setContentView(view);

        activityCartBinding.btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                Intent intentSkip = new Intent(this, com.moviles.mercadolibreapp.HomeActivity.class);
                startActivity(intentSkip);
                break;
        }
    }

    public void getCart(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.60.201:8081/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartService cartService = retrofit.create(CartService.class);
        Call <List<Producto>> call = cartService.getToCart(identificacion);
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {

                if (response.isSuccessful()){

                    List<Producto> productosCarrito = response.body();

                    AlertDialog.Builder mensaje = new AlertDialog.Builder(CartActivity.this);
                    mensaje.setMessage(productosCarrito.get(0).getId());
                    mensaje.setMessage(productosCarrito.get(0).getPrecio());
                    mensaje.setMessage(productosCarrito.get(0).getCantidad());
                    mensaje.setTitle(productosCarrito.get(0).getNombre());
                    mensaje.show();

                }

            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });

    }

    public void readPreferences(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        status = sharedPref.getString("Status", "");
        identificacion = sharedPref.getInt("Identificación", 0);
    }

}