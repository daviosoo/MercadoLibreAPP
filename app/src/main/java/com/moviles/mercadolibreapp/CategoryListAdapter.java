package com.moviles.mercadolibreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moviles.mercadolibreapp.databinding.CategroryitemBinding;
import com.moviles.mercadolibreapp.databinding.ProductscardsBinding;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder>{

    private Context context;
    private ArrayList<CategoryList> categoryLists;
    CategroryitemBinding categroryitemBinding;

    public CategoryListAdapter(Context ctx,ArrayList<CategoryList> categoryLists){
        this.context = ctx;
        this.categoryLists = categoryLists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.categoryitem,parent,false);
        categroryitemBinding = CategroryitemBinding.inflate(LayoutInflater.from(context));
        return new ViewHolder(categroryitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categroryitemBinding.imgCategory.setImageResource(categoryLists.get(position).getImgCategory());
        holder.categroryitemBinding.txtCategory.setText(categoryLists.get(position).getTxtCategory());
    }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CategroryitemBinding categroryitemBinding;
        ImageView imgCategory;
        TextView textCategory;

        public ViewHolder(@NonNull CategroryitemBinding categroryitemBinding) {
            super(categroryitemBinding.getRoot());
            this.categroryitemBinding = categroryitemBinding;
            imgCategory = itemView.findViewById(R.id.imgCategory);
            textCategory = itemView.findViewById(R.id.txtCategory);
        }
    }
}
