package com.moviles.mercadolibreapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moviles.mercadolibreapp.Model.Car;
import com.moviles.mercadolibreapp.databinding.ItemListBinding;
import com.moviles.mercadolibreapp.databinding.ProductscardsBinding;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private Context context;
    ItemListBinding itemListBinding;
    private ArrayList<Car> lista;

    public CarAdapter(Context context, ArrayList<Car> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemListBinding = ItemListBinding.inflate(LayoutInflater.from(context));
        return new CarAdapter.CarViewHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.CarViewHolder holder, int position) {
        Car car = lista.get(position);
        holder.itemListBinding.nombreCar.setText(car.getNombre_producto());
        holder.itemListBinding.desCar.setText(String.valueOf(car.getCantidad()));
        holder.itemListBinding.valueCar.setText(String.valueOf(car.getPrecio_producto()));
        Glide.with(context).load(car.getUrl_producto()).into(holder.itemListBinding.imgCar);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        ItemListBinding itemListBinding;

        public CarViewHolder(@NonNull  ItemListBinding itemListBinding) {
            super(itemListBinding.getRoot());
            this.itemListBinding = itemListBinding;


        }
    }
}
