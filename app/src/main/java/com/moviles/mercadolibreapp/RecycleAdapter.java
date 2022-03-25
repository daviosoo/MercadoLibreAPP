package com.moviles.mercadolibreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleHolder> {

    private Context context;
    private ArrayList<RecycleModel> recycleModels;
    public RecycleAdapter (Context context, ArrayList<RecycleModel> recycleModels){
        this.context=context;
        this.recycleModels=recycleModels;
    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        return new RecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleHolder holder, int position) {
        holder.imgBanenr.setImageResource(recycleModels.get(position).getImgBanner());

    }

    @Override
    public int getItemCount() {
        return recycleModels.size();
    }

    public class RecycleHolder extends RecyclerView.ViewHolder{
        ImageView imgBanenr;
        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
            imgBanenr=itemView.findViewById(R.id.image_view);
        }
    }
}
