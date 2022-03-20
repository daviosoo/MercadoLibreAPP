package com.moviles.mercadolibreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<RecycleModel> recycleModels;
    RecycleAdapter recycleAdapter;
    Timer timer;
    TimerTask timerTask;
    int position;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        RecycleAdapter recycleAdapter = new RecycleAdapter(this,recycleModels);
        recyclerView.setAdapter(recycleAdapter);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


    }




    public void runAutoScroll(){
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() < (recycleAdapter.getItemCount() - 5)) {
                        layoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), layoutManager.findLastCompletelyVisibleItemPosition() + 1);
                    } else if (layoutManager.findLastCompletelyVisibleItemPosition() < (recycleAdapter.getItemCount() - 5)) {
                        layoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
                    }
                }
            };
            timer.schedule(timerTask,3000);
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