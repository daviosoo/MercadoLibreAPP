package com.moviles.mercadolibreapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.moviles.mercadolibreapp.Interface.CartService;
import com.moviles.mercadolibreapp.Model.Car;
import com.moviles.mercadolibreapp.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CartActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityCartBinding activityCartBinding;
    String status;
    int identificacion;
    ArrayList<DatesCar> listDatos;
    RecyclerView recicler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = activityCartBinding.getRoot();
        setContentView(view);

        readPreferences();
        activityCartBinding.btnBack.setOnClickListener(this);

        getCart();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                Intent intentSkip = new Intent(this, HomeActivity.class);
                startActivity(intentSkip);
                break;
        }
    }

    public void readPreferences() {
        SharedPreferences sharedPref = getSharedPreferences("MercadoLibre",Context.MODE_PRIVATE);
        status = sharedPref.getString("Status", "");
        identificacion = sharedPref.getInt(getString(R.string.Identificacion), 0);
        Toast.makeText(this, Integer.toString(identificacion), Toast.LENGTH_SHORT).show();
    }

    public void getCart(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartService cartService = retrofit.create(CartService.class);
        Call <ArrayList<Car>> call = cartService.getToCart(identificacion);

        call.enqueue(new Callback<ArrayList<Car>>() {
            @Override
            public void onResponse(Call<ArrayList<Car>> call, Response<ArrayList<Car>> response) {

                if (response.isSuccessful()){

                    List<Car> productosCarrito = response.body();

                    AlertDialog.Builder mensaje = new AlertDialog.Builder(CartActivity.this);
                    String message;
                    TextView valorCarrito = findViewById(R.id.txtTotalCarrito);
                    TextView cantidaCarrito = findViewById(R.id.txtCantidadCarrito);


                    if (productosCarrito.size() > 0)
                    {
                        cantidaCarrito.setText("Carrito ("+ productosCarrito.size() +")");
                        message = Integer.toString(productosCarrito.get(0).getId_producto()) + "\n" + Integer.toString(productosCarrito.get(0).getPrecio_producto()) + "\n" + Integer.toString(productosCarrito.get(0).getCantidad());
                        mensaje.setTitle(productosCarrito.get(0).getNombre_producto());

                        int valorTotalCarrito = 0;

                        for (Car producto:
                                productosCarrito) {

                            int totalProducto = producto.getCantidad() * producto.getPrecio_producto();
                            valorTotalCarrito += totalProducto;

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
                    else
                    {
                        message = "No hay productos en el carrito";
                        mensaje.setTitle("Aviso");
                    }

                    mensaje.setMessage(message);
                    mensaje.show();

                }

            }

            @Override
            public void onFailure(Call<ArrayList<Car>> call, Throwable t) {

                AlertDialog.Builder mensaje = new AlertDialog.Builder(CartActivity.this);
                mensaje.setMessage("error mi pay "+t.getMessage());
                mensaje.show();

            }
        });

    }

}