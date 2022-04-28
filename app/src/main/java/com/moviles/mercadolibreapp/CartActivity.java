package com.moviles.mercadolibreapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moviles.mercadolibreapp.Interface.CartService;
import com.moviles.mercadolibreapp.Model.Car;
import com.moviles.mercadolibreapp.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CartActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityCartBinding activityCartBinding;
    String status;
    int identificacion;
    ArrayList<DatesCar> listDatos ;
    RecyclerView recicler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = activityCartBinding.getRoot();
        setContentView(view);

        activityCartBinding.btnBack.setOnClickListener(this);
        recicler = findViewById(R.id.recycle_car);


        recicler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listDatos= new ArrayList<DatesCar>();
        for (int i = 0; i <= listDatos.size() ; i++) {

        }
        AdapterDatos adapterDatos = new AdapterDatos(listDatos,this);
        recicler.setAdapter(adapterDatos);

        listDatos.add(new DatesCar("Portatil hp","$18.500","3","https://th.bing.com/th/id/OIP.OhjypicjGwjWNwhCbLECCwHaHa?pid=ImgDet&rs=1"));
        listDatos.add(new DatesCar("Portatil hp","$18.500","3","https://th.bing.com/th/id/OIP.OhjypicjGwjWNwhCbLECCwHaHa?pid=ImgDet&rs=1"));
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

    public void getCart(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.60.201:8081/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartService cartService = retrofit.create(CartService.class);
        Call <List<Car>> call = cartService.getToCart(identificacion);
        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {

                if (response.isSuccessful()){

                    List<Car> productosCarrito = response.body();

                    AlertDialog.Builder mensaje = new AlertDialog.Builder(CartActivity.this);
                    mensaje.setMessage(productosCarrito.get(0).getId());
                    mensaje.setMessage(productosCarrito.get(0).getPrecio());
                    mensaje.setMessage(productosCarrito.get(0).getCantidad());
                    mensaje.setTitle(productosCarrito.get(0).getNombre());
                    mensaje.show();

                }

            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {

            }
        });

    }

    public void readPreferences(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        status = sharedPref.getString("Status", "");
        identificacion = sharedPref.getInt("Identificación", 0);
    }

}