package com.moviles.mercadolibreapp;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moviles.mercadolibreapp.Model.Producto;
import com.moviles.mercadolibreapp.databinding.ProductscardsBinding;

import java.util.ArrayList;


public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private Context context;
    ProductscardsBinding productscardsBinding;
    private ArrayList<Producto> lista;
    //private LayoutInflater inflater;



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

        return new ProductoViewHolder(productscardsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ProductoViewHolder holder, int position) {
        Producto producto = lista.get(position);
        holder.productscardsBinding.desProduct.setText(producto.getNombre());
        holder.productscardsBinding.valueProduct.setText(String.valueOf(producto.getPrecio()));

        Glide.with(context).load(producto.getUrl()).into(holder.productscardsBinding.imageProduct);
    }

    @Override
    public int getItemCount() {
        return lista.size();
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

