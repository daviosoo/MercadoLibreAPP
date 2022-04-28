package com.moviles.mercadolibreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.moviles.mercadolibreapp.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<RecycleModel> recycleModels;
    LinearLayoutManager layoutManager;
    BottomSheetDialog dialog;
    ImageButton buttonOpenDialog;
    ImageButton cart;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navigationView.setItemIconTintList(null);
        navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));

        recyclerView= findViewById(R.id.recycle_view);
        Integer[] imgBanner = {R.drawable.imagen1,R.drawable.imagen2,R.drawable.imagen3,R.drawable.imagen4,R.drawable.imagen5};
        recycleModels = new ArrayList<>();
        for(int i=0;i<imgBanner.length;i++){
            RecycleModel model = new RecycleModel(imgBanner[i]);
            recycleModels.add(model);
        }
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        cart = findViewById(R.id.btnShopping);
        RecycleAdapter recycleAdapter = new RecycleAdapter(this,recycleModels);
        recyclerView.setAdapter(recycleAdapter);

        ImageButton btnshowmenu = findViewById(R.id.btnPlus);
        btnshowmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"TAG");

            }
        });

        ImageButton btnOffer = findViewById(R.id.btnOffer);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnOffer:
                Intent intent = new Intent(this, OffertsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnShopping:
                Intent intentShopping = new Intent(this, HomeActivity.class);
                startActivity(intentShopping);
                break;
        }

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*if (!status.isEmpty()){
                    Intent intentCart = new Intent(HomeActivity.this, com.moviles.mercadolibreapp.CartActivity.class);
                    startActivity(intentCart);
                }
                else
                {
                    Intent intentLogin = new Intent(HomeActivity.this, com.moviles.mercadolibreapp.LoginEmailActivity.class);
                    startActivity(intentLogin);
                }*/
                Intent intentCart = new Intent(HomeActivity.this, com.moviles.mercadolibreapp.CartActivity.class);
                startActivity(intentCart);


            }
        });
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}