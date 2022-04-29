package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View;
import android.widget.GridLayout;

import android.os.Bundle;
import android.widget.GridLayout;
import com.moviles.mercadolibreapp.Interface.ConsultaProducto;
import com.moviles.mercadolibreapp.Model.Producto;
import com.moviles.mercadolibreapp.Model.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class OffertsActivity extends AppCompatActivity {
    RecyclerView dataList;
    ArrayList<ListProducts> listProducts;
    ListProductsAdapter adapter;
    RecyclerView categoryList;
    ArrayList<CategoryList> categoryModel;
    LinearLayoutManager layoutManager;
    CategoryListAdapter categoryListAdapter;
    ImageButton backOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerts);
        dataList = findViewById(R.id.products);
        instancia();


        adapter= new ListProductsAdapter(this,listProducts);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.60.31/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConsultaProducto consultaProducto = retrofit.create(ConsultaProducto.class);
        Call<List<Producto>> call = consultaProducto.getProduc();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                List<Producto> listaProductos = response.body();
                for(int i = 0;i<listaProductos.size();i++){
                    listProducts.add(new ListProducts(listaProductos.get(i).getUrl_producto(),listaProductos.get(i).getPrecio_producto(),listaProductos.get(i).getNombre_producto()));
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

        categoryList = findViewById(R.id.categoryRecycle);
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
    }


    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnBackHome:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;


        }
    }

    private void instancia(){
        listProducts = new ArrayList<>();
        dataList = findViewById(R.id.products);
        adapter = new ListProductsAdapter(this,listProducts);
    }

}