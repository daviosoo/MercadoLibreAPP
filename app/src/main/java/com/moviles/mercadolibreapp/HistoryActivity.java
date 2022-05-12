package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.moviles.mercadolibreapp.Interface.CartService;
import com.moviles.mercadolibreapp.Interface.HistorialService;
import com.moviles.mercadolibreapp.Model.Car;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryActivity extends AppCompatActivity {
    int identificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        readPreferences();
        getHistory();
    }

    public void readPreferences() {
        SharedPreferences sharedPref = getSharedPreferences("MercadoLibre", Context.MODE_PRIVATE);
        identificacion = sharedPref.getInt(getString(R.string.Identificacion), 0);
    }

    public void getHistory()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+getString(R.string.IP)+"/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HistorialService historialService = retrofit.create(HistorialService.class);
        Call<ArrayList<Car>> call = historialService.getHistorial(identificacion);
        call.enqueue(new Callback<ArrayList<Car>>() {
            @Override
            public void onResponse(Call<ArrayList<Car>> call, Response<ArrayList<Car>> response) {

                ArrayList<Car> listaHistorial = response.body();

                if (listaHistorial.size() > 0)
                {
                    Toast.makeText(HistoryActivity.this, "Hay productos en el historial", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(HistoryActivity.this, "No hay productos en el historial", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ArrayList<Car>> call, Throwable t) {

            }
        });

    }
}