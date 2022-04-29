package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moviles.mercadolibreapp.databinding.ActivityLoginIconBinding;

public class LoginIconActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginIconBinding iconBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iconBinding = ActivityLoginIconBinding.inflate(getLayoutInflater());
        View view = iconBinding.getRoot();
        setContentView(view);
        iconBinding.btnIconLogin.setOnClickListener(this);
        iconBinding.btnBackIconL.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_backIconL:
                Intent intentBack = new Intent(this,com.moviles.mercadolibreapp.MainActivity.class);
                startActivity(intentBack);
                break;
            case R.id.btn_iconLogin:
                Intent intentNext = new Intent(this, RegisterActivity.class);
                startActivity(intentNext);

        }
    }
}