package com.moviles.mercadolibreapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.moviles.mercadolibreapp.Interface.CartService;
import com.moviles.mercadolibreapp.Model.Car;
import com.moviles.mercadolibreapp.Model.Producto;
import com.moviles.mercadolibreapp.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CartActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityCartBinding activityCartBinding;
    String status;
    int identificacion;
    ArrayList<Car> listaProductos;
    CarAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = activityCartBinding.getRoot();
        setContentView(view);

        readPreferences();
        activityCartBinding.btnBack.setOnClickListener(this);

        Toast.makeText(this, Integer.toString(identificacion), Toast.LENGTH_SHORT).show();
        listaProductos = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.24.85/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartService cartService = retrofit.create(CartService.class);
        Call<ArrayList<Car>> call = cartService.getToCart(identificacion);
        call.enqueue(new Callback<ArrayList<Car>>() {
            @Override
            public void onResponse(Call<ArrayList<Car>> call, Response<ArrayList<Car>> response) {
                listaProductos = response.body();
                adapter = new CarAdapter(CartActivity.this,listaProductos);
                activityCartBinding.recycleCar.setHasFixedSize(true);
                activityCartBinding.recycleCar.setLayoutManager(new LinearLayoutManager(CartActivity.this,LinearLayoutManager.VERTICAL,false));
                activityCartBinding.recycleCar.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Car>> call, Throwable t) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(CartActivity.this);
                mensaje.setMessage(""+t);
                mensaje.setTitle("ERROR");
                mensaje.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                Intent intentSkip = new Intent(this, HomeActivity.class);
                startActivity(intentSkip);
                break;
        }
    }

    public void readPreferences() {
        SharedPreferences sharedPref = getSharedPreferences("MercadoLibre" ,Context.MODE_PRIVATE);
        identificacion =sharedPref.getInt("identificacion",0);
    }



}