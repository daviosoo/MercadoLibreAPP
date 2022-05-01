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
        readPreferences();

        //recicler = findViewById(R.id.recycle_car);
        //recicler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //listDatos= new ArrayList<DatesCar>();
        //for (int i = 0; i <= listDatos.size() ; i++) {

        //}
        //AdapterDatos adapterDatos = new AdapterDatos(listDatos,this);
        //recicler.setAdapter(adapterDatos);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.7/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartService cartService = retrofit.create(CartService.class);
        Call <ArrayList<Car>> call = cartService.getToCart(identificacion);
        call.enqueue(new Callback<ArrayList<Car>>() {
            @Override
            public void onResponse(Call<ArrayList<Car>> call, Response<ArrayList<Car>> response) {

                    ArrayList<Car> productosCarrito = response.body();

                    AlertDialog.Builder mensaje = new AlertDialog.Builder(CartActivity.this);
                    mensaje.setMessage(productosCarrito.get(0).getId_producto());
                    mensaje.setMessage(productosCarrito.get(0).getPrecio_producto());
                    mensaje.setMessage(productosCarrito.get(0).getCantidad());
                    mensaje.setTitle(productosCarrito.get(0).getNombre_producto());
                    mensaje.show();
            }

            @Override
            public void onFailure(Call<ArrayList<Car>> call, Throwable t) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(CartActivity.this);
                mensaje.setMessage("error mi pay "+t.getMessage());
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



    public void readPreferences(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        status = sharedPref.getString("Status", "");
        identificacion = sharedPref.getInt("Identificación", 0);
    }

}