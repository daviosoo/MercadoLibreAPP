package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        readPreferences();
        Toast.makeText(this, Integer.toString(identificacion), Toast.LENGTH_SHORT).show();
        getCart();
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
                .baseUrl("http://192.168.1.24/MercadoLibreAPI/features/")
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

                    String message = Integer.toString(productosCarrito.get(0).getId_producto()) + "\n" + Integer.toString(productosCarrito.get(0).getPrecio_producto()) + "\n" + Integer.toString(productosCarrito.get(0).getCantidad_producto());
                    mensaje.setMessage(message);
                    mensaje.setTitle(productosCarrito.get(0).getNombre_producto());
                    mensaje.show();

                }

            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

            }
        });

    }

    public void readPreferences(){
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.SharedPreference) ,Context.MODE_PRIVATE);
        status = sharedPref.getString("Status", "");
        identificacion = sharedPref.getInt(getString(R.string.Identificacion), 0);
    }

}