package com.moviles.mercadolibreapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moviles.mercadolibreapp.Interface.CartService;
import com.moviles.mercadolibreapp.Model.Car;
import com.moviles.mercadolibreapp.Model.CarPost;
import com.moviles.mercadolibreapp.Model.Producto;
import com.moviles.mercadolibreapp.databinding.ProductscardsBinding;

import java.util.ArrayList;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private Context context;
    ProductscardsBinding productscardsBinding;
    private ArrayList<Producto> lista;
    //private LayoutInflater inflater;
    ImageButton cart;
    String status;
    int identificacion;



    public void readPreferences(){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        status = sharedPref.getString("Status", "");
        identificacion = sharedPref.getInt(context.getString(R.string.Identificacion), 0);
    }

    public ProductoAdapter(ArrayList<Producto> itemList, Context context){
        this.lista = itemList;
        //this.inflater = LayoutInflater.from(context);
        this.context=context;
    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productscardsBinding = ProductscardsBinding.inflate(LayoutInflater.from(context));
        //inflater=LayoutInflater.from(context);
        //View view =inflater.inflate(R.layout.productscards,null);
        cart = productscardsBinding.btnCarrito;
        return new ProductoViewHolder(productscardsBinding);



    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ProductoViewHolder holder, int position) {
        Producto producto = lista.get(position);
        holder.productscardsBinding.desProduct.setText(producto.getNombre());
        holder.productscardsBinding.valueProduct.setText(String.valueOf(producto.getPrecio()));

        readPreferences();

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CarPost productoCarrito = new CarPost( producto.getId(),producto.getNombre(),producto.getPrecio(),producto.getUrl(), identificacion, producto.getCantidad());

                //Toast.makeText(context, productoCarrito.getUrl_producto(), Toast.LENGTH_SHORT).show();
                insertInCart(productoCarrito);

            }
        });

        Glide.with(context).load(producto.getUrl()).into(holder.productscardsBinding.imageProduct);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void insertInCart(CarPost producto){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartService cartService = retrofit.create(CartService.class);
        Call<String> call = cartService.insertToCart(producto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String respuesta = response.body();

                if(respuesta == "Exito agregando al carrito")
                {
                    Toast.makeText(context, respuesta , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.e("Error:", respuesta);
                    Toast.makeText(context, respuesta, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Error:", t.toString());
            }
        });

    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder{
        ProductscardsBinding productscardsBinding;
        //TextView value,name;
        //ImageView gridItem;
        //View view;
        ProductoViewHolder(@NonNull ProductscardsBinding itemBinding) {
            super(itemBinding.getRoot());
            this.productscardsBinding= itemBinding;
        }
    }
}

