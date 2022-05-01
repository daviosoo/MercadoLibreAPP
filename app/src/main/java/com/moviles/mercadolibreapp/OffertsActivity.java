package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.moviles.mercadolibreapp.Interface.ConsultaProducto;
import com.moviles.mercadolibreapp.Model.Producto;
import com.moviles.mercadolibreapp.Model.Register;
import com.moviles.mercadolibreapp.databinding.ActivityOffertsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OffertsActivity extends AppCompatActivity {

    //ArrayList<ListProducts> listProducts;
    private ActivityOffertsBinding activityOffertsBinding;
    ArrayList<Producto> listaProductos;
    ProductoAdapter adapter;
    RecyclerView dataList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOffertsBinding = ActivityOffertsBinding.inflate(getLayoutInflater());
        View view = activityOffertsBinding.getRoot();
        setContentView(view);

        listaProductos = new ArrayList<>();


        //dataList = activityOffertsBinding.products;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.7/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsultaProducto consultaProducto = retrofit.create(ConsultaProducto.class);
        Call<ArrayList<Producto>> call = consultaProducto.getProduc();
        call.enqueue(new Callback<ArrayList<Producto>>() {
            @Override
            public void onResponse(Call<ArrayList<Producto>> call, Response<ArrayList<Producto>> response) {

                listaProductos = response.body();
                adapter= new ProductoAdapter(listaProductos,OffertsActivity.this);
                activityOffertsBinding.products.setHasFixedSize(true);
                activityOffertsBinding.products.setLayoutManager(new LinearLayoutManager(OffertsActivity.this,LinearLayoutManager.HORIZONTAL,false));
                activityOffertsBinding.products.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //Toast.makeText(OffertsActivity.this, ""+listaProductos.size(), Toast.LENGTH_SHORT).show();


                //AlertDialog.Builder mensaje = new AlertDialog.Builder(OffertsActivity.this);
               //mensaje.setMessage(listaProductos.get(0).getUrl());
                //mensaje.setTitle("url");
                //mensaje.show();
            }


            @Override
            public void onFailure(Call<ArrayList<Producto>> call, Throwable t) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(OffertsActivity.this);
                mensaje.setMessage(""+t);
                mensaje.setTitle("ERROR");
                mensaje.show();
            }
        });


    }

    private void instancia(){
        listaProductos = new ArrayList<Producto>();
        //dataList = findViewById(R.id.products);
        //adapter = new ListProductsAdapter(this,listProducts);
    }

    public void getProducts(){

    }


}
