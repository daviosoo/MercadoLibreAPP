package com.moviles.mercadolibreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ListProductsAdapter extends RecyclerView.Adapter<ListProductsAdapter.ViewHolder>{

    ArrayList<ListProducts> listProducts;
    Context context;
    LayoutInflater inflater;

    public ListProductsAdapter(Context ctx,ArrayList<ListProducts> listProducts){
        this.listProducts = listProducts;
        this.context = ctx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productscards,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ListProducts products = listProducts.get(position);

        Glide.with(context).load(products.image).into(holder.gridItem);
        holder.value.setText(products.getValue());
        holder.name.setText(products.getName());
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView value,name;
        ImageView gridItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.valueProduct);
            gridItem = itemView.findViewById(R.id.imageProduct);
            name = itemView.findViewById(R.id.desProduct);
        }
    }
}
