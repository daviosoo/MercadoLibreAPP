package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moviles.mercadolibreapp.databinding.ActivityCartBinding;


public class CartActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityCartBinding activityCartBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = activityCartBinding.getRoot();
        setContentView(view);

        activityCartBinding.btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                Intent intentSkip = new Intent(this, com.moviles.mercadolibreapp.HomeActivity.class);
                startActivity(intentSkip);
                break;
        }
    }
}