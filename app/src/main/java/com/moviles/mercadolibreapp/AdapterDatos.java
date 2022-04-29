package com.moviles.mercadolibreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterDatos  extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {

    ArrayList<DatesCar> listDatos;
    Context context;
    //ArrayList<DatesCar> listDatos;

    public AdapterDatos(ArrayList<DatesCar> listDatos, Context ctx) {
        this.listDatos = listDatos;
        this.context = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatesCar products = listDatos.get(position);
        holder.des.setText(products.txt_description);
        holder.valor.setText(products.txt_value);
        Glide.with(context).load(products.img_car).into(holder.imgItem);

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView des,valor;
        ImageView imgItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            valor = itemView.findViewById(R.id.valueCar);
            imgItem = itemView.findViewById(R.id.imgCar);
            des = itemView.findViewById(R.id.desCar);
        }


    }
}
