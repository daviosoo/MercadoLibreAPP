package com.moviles.mercadolibreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder>{

    private Context context;
    private ArrayList<CategoryList> categoryLists;

    public CategoryListAdapter(Context ctx,ArrayList<CategoryList> categoryLists){
        this.context = ctx;
        this.categoryLists = categoryLists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categoryitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgCategory.setImageResource(categoryLists.get(position).getImgCategory());
        holder.textCategory.setText(categoryLists.get(position).getTxtCategory());
    }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCategory;
        TextView textCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            textCategory = itemView.findViewById(R.id.txtCategory);
        }
    }
}
