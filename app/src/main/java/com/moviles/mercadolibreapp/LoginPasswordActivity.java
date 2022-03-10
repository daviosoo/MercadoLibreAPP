package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moviles.mercadolibreapp.databinding.ActivityLoginPasswordBinding;

public class LoginPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityLoginPasswordBinding activityLoginPasswordBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginPasswordBinding = ActivityLoginPasswordBinding.inflate(getLayoutInflater());
        View view = activityLoginPasswordBinding.getRoot();
        setContentView(view);

        activityLoginPasswordBinding.btnBack.setOnClickListener(this);
        activityLoginPasswordBinding.btnLogin.setOnClickListener(this);
        activityLoginPasswordBinding.btnImNot.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                Intent intentBack = new Intent(this, com.moviles.mercadolibreapp.MainActivity.class);
                startActivity(intentBack);
                break;
            case R.id.btnLogin:
                //Intent intentLogin = new Intent(this, com.moviles.mercadolibreapp.HomeActivity.class);
                //startActivity(intentLogin);
                break;
            case R.id.btnImNot:
                Intent intentEmail = new Intent(this, com.moviles.mercadolibreapp.LoginEmailActivity.class);
                startActivity(intentEmail);
                break;
        }
    }
}