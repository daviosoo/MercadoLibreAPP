package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moviles.mercadolibreapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBindingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBindingBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBindingBinding.getRoot();
        setContentView(view);

        activityMainBindingBinding.btnSkip.setOnClickListener(this);
        activityMainBindingBinding.btnContinueRegister.setOnClickListener(this);
        activityMainBindingBinding.btnContinueLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSkip:
                Intent intentSkip = new Intent(this, com.moviles.mercadolibreapp.HomeActivity.class);
                startActivity(intentSkip);
                break;
            case R.id.btnContinueRegister:
                Intent intentRegister = new Intent(this, com.moviles.mercadolibreapp.LoginIconActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.btnContinueLogin:
                Intent intentLogin = new Intent(this, com.moviles.mercadolibreapp.LoginEmailActivity.class);
                startActivity(intentLogin);
        }
    }
}