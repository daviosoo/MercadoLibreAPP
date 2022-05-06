package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.moviles.mercadolibreapp.Interface.ConsultaProducto;
import com.moviles.mercadolibreapp.Model.Car;
import com.moviles.mercadolibreapp.Model.Producto;
import com.moviles.mercadolibreapp.Model.Register;
import com.moviles.mercadolibreapp.databinding.ActivityOffertsBinding;
import com.moviles.mercadolibreapp.databinding.ProductscardsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OffertsActivity extends AppCompatActivity{

    //ArrayList<ListProducts> listProducts;
    private ActivityOffertsBinding activityOffertsBinding;
    private ProductscardsBinding productscardsBinding;
    String status;
    //carrito
    ArrayList<Producto> listaProductos;
    ProductoAdapter adapter;
    RecyclerView dataList;
    ArrayList<Car> listaCar;
    //fin carrito

    //categoria
    RecyclerView categoryList;
    ArrayList<CategoryList> categoryModel;
    CategoryListAdapter categoryListAdapter;
    ImageButton backOffer;
    LinearLayoutManager layoutManager;
    ImageButton carButton;

    //fin categoria


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOffertsBinding = ActivityOffertsBinding.inflate(getLayoutInflater());
        View view = activityOffertsBinding.getRoot();
        productscardsBinding = ProductscardsBinding.inflate(getLayoutInflater());
        View view2 = productscardsBinding.getRoot();
        readPreferences();

        setContentView(view);
        listaProductos = new ArrayList<>();
        //dataList = activityOffertsBinding.products;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.18.45.56/MercadoLibreAPI/features/")
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
                GridLayoutManager gridLayoutManager = new GridLayoutManager(OffertsActivity.this, 2, GridLayoutManager.VERTICAL, false);
                activityOffertsBinding.products.setLayoutManager(gridLayoutManager);
                activityOffertsBinding.products.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Producto>> call, Throwable t) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(OffertsActivity.this);
                mensaje.setMessage(""+t);
                mensaje.setTitle("ERROR");
                mensaje.show();
            }
        });
        categoryList = activityOffertsBinding.categoryRecycle;
        String[] textCategory = {"Celulares y Telefonos","Computacion","Belleza y Cuidado Personal","Electronica,Audio y Video","Deportes y Fitness","Carros,Motos y Otros"};
        Integer[] imgCategory = {R.drawable.ic_cellphone,R.drawable.ic_desktop,R.drawable.ic_beauty,R.drawable.ic_microphone,R.drawable.ic_ball,R.drawable.ic_bx_car};
        categoryModel = new ArrayList<>();
        for(int i=0;i<textCategory.length;i++){
            CategoryList model = new CategoryList(imgCategory[i],textCategory[i]);
            categoryModel.add(model);
        }
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        categoryList.setLayoutManager(layoutManager);
        CategoryListAdapter recycleAdapter = new CategoryListAdapter(this,categoryModel);
        categoryList.setAdapter(recycleAdapter);
        backOffer = findViewById(R.id.btnBackHome);

        backOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OffertsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        carButton = activityOffertsBinding.btnBuy;
        carButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!status.isEmpty()){
                    Intent intentCart = new Intent(OffertsActivity.this, com.moviles.mercadolibreapp.CartActivity.class);
                    startActivity(intentCart);
                }
                else
                {
                    Intent intentLogin = new Intent(OffertsActivity.this, com.moviles.mercadolibreapp.LoginEmailActivity.class);
                    startActivity(intentLogin);
                }
            }
        });

    }
    public void readPreferences(){
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        status = sharedPref.getString("Status", "");
    }

}