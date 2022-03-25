package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moviles.mercadolibreapp.databinding.ActivityLoginEmailBinding;

    public class LoginEmailActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityLoginEmailBinding activityLoginEmailBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginEmailBinding = ActivityLoginEmailBinding.inflate(getLayoutInflater());
        View view = activityLoginEmailBinding.getRoot();
        setContentView(view);

        activityLoginEmailBinding.btnContinue.setOnClickListener(this);
        activityLoginEmailBinding.btnCreateAccount.setOnClickListener(this);
        activityLoginEmailBinding.btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                Intent intentBack = new Intent(this, MainActivity.class);
                startActivity(intentBack);
                break;
            case R.id.btnCreateAccount:
                Intent intentRegister = new Intent(this, com.moviles.mercadolibreapp.RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.btnContinue:
                Intent intentContinue = new Intent(this, com.moviles.mercadolibreapp.LoginPasswordActivity.class);
                intentContinue.putExtra("email",activityLoginEmailBinding.etUserPassword.getText().toString());
                startActivity(intentContinue);
                break;
        }
    }
}