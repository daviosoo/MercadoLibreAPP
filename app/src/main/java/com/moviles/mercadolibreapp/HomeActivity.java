package com.moviles.mercadolibreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<RecycleModel> recycleModels;
    LinearLayoutManager layoutManager;
    BottomSheetDialog dialog;
    ImageButton cart;
    String status;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        readPreferences();
        navigationView = findViewById(R.id.nav_view);

        if (!status.isEmpty()){

            TextView user = findViewById(R.id.ubication);
            user.setText(email);

        }

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        cart = findViewById(R.id.btnShopping);

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

        RecycleAdapter recycleAdapter = new RecycleAdapter(this,recycleModels);
        recyclerView.setAdapter(recycleAdapter);

        ImageButton btnshowmenu = findViewById(R.id.btnPlus);
        btnshowmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        HomeActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(
                        R.layout.show_menu,(LinearLayout)findViewById(R.id.show_menu)
                );
                bottomSheetView.findViewById(R.id.btnqr2);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!status.isEmpty()){
                    Intent intentCart = new Intent(HomeActivity.this, com.moviles.mercadolibreapp.CartActivity.class);
                    startActivity(intentCart);
                }
                else
                {
                    Intent intentLogin = new Intent(HomeActivity.this, com.moviles.mercadolibreapp.LoginEmailActivity.class);
                    startActivity(intentLogin);
                }


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

    public void readPreferences(){
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.SharedPreference),Context.MODE_PRIVATE);
        status = sharedPref.getString("Status", "");
        email = sharedPref.getString("Email", "");
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}