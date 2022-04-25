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
    RecyclerView dataList;
    ArrayList<ListProducts> listProducts;
    ListProductsAdapter adapter;

    private ActivityOffertsBinding activityOffertsBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOffertsBinding = ActivityOffertsBinding.inflate(getLayoutInflater());
        View view = activityOffertsBinding.getRoot();
        setContentView(view);
        dataList = findViewById(R.id.products);
        instancia();


        adapter= new ListProductsAdapter(this,listProducts);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.9/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsultaProducto consultaProducto = retrofit.create(ConsultaProducto.class);
        Call<List<Producto>> call = consultaProducto.getProduc();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                List<Producto> listaProductos = response.body();
                for(int i = 0;i<=listaProductos.size();i++){
                        listProducts.add(new ListProducts(listaProductos.get(i).getUrl(),listaProductos.get(i).getPrecio(),listaProductos.get(i).getNombre()));
                }
                Toast.makeText(OffertsActivity.this, "producto:"+listProducts.get(0), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(OffertsActivity.this);
                mensaje.setMessage(""+t);
                mensaje.setTitle("ERROR");
                mensaje.show();
            }
        });

    }

    private void instancia(){
        listProducts = new ArrayList<>();
        dataList = findViewById(R.id.products);
        adapter = new ListProductsAdapter(this,listProducts);
    }


}
