package com.moviles.mercadolibreapp;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moviles.mercadolibreapp.Interface.CartService;
import com.moviles.mercadolibreapp.Model.Car;
import com.moviles.mercadolibreapp.databinding.ActivityCartBinding;
import com.moviles.mercadolibreapp.databinding.ItemListBinding;
import com.moviles.mercadolibreapp.databinding.ProductscardsBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private Context context;
    ItemListBinding itemListBinding;
    ActivityCartBinding activityCartBinding;
    private ArrayList<Car> lista;
    TextView delete;
    int identificacion;

    public CarAdapter(Context context, ArrayList<Car> lista) {
        this.context = context;
        this.lista = lista;
    }

    public void readPreferences(){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        identificacion = sharedPref.getInt(context.getString(R.string.Identificacion), 0);
    }

    @NonNull
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemListBinding = ItemListBinding.inflate(LayoutInflater.from(context));
        activityCartBinding = ActivityCartBinding.inflate(LayoutInflater.from(context));
        delete = itemListBinding.deleteCar;
        readPreferences();
        return new CarAdapter.CarViewHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.CarViewHolder holder, int position) {
        Car car = lista.get(position);
        holder.itemListBinding.nombreCar.setText(car.getNombre_producto());
        holder.itemListBinding.desCar.setText(String.valueOf(car.getCantidad()));
        holder.itemListBinding.valueCar.setText(String.valueOf(car.getPrecio_producto()));
        Glide.with(context).load(car.getUrl_producto()).into(holder.itemListBinding.imgCar);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, ""+idProducto, Toast.LENGTH_SHORT).show();
                deleteCart(car.getId_producto());

            }
        });

    }

    public void deleteCart(int product){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.18.45.56/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CartService cartService = retrofit.create(CartService.class);
        Call<String> call = cartService.deleteFromCart(identificacion, product);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String respuesta= response.body();

                if(respuesta.equals("Producto retirado del carrito"))
                {
                    Toast.makeText(context, respuesta , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, CartActivity.class);
                    ((CartActivity)context).finish();
                    ((CartActivity) context).overridePendingTransition(0,0);
                    context.startActivity(intent);
                    ((CartActivity) context).overridePendingTransition(0,0);

                }
                else
                {
                    Log.e("Error:", respuesta);
                    Toast.makeText(context, respuesta, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    public void getTotal() {

        TextView valorCarrito = activityCartBinding.txtTotalCarrito;
        TextView cantidaCarrito = activityCartBinding.txtCantidadCarrito;

        cantidaCarrito.setText("Carrito (" + lista.size() + ")");
        //message = Integer.toString(listaProductos.get(0).getId_producto()) + "\n" + Integer.toString(listaProductos.get(0).getPrecio_producto()) + "\n" + Integer.toString(listaProductos.get(0).getCantidad());
        //mensaje.setTitle(listaProductos.get(0).getNombre_producto());

        int valorTotalCarrito = 0;

        for (Car producto :
                lista) {

            valorTotalCarrito += producto.getPrecio_producto();

            //recicler = findViewById(R.id.recycle_car);
            //recicler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            //listDatos= new ArrayList<DatesCar>();
            //for (int i = 0; i <= listDatos.size() ; i++) {

            //}
            //AdapterDatos adapterDatos = new AdapterDatos(listDatos,this);
            //recicler.setAdapter(adapterDatos);

        }

        valorCarrito.setText("$" + valorTotalCarrito);

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
