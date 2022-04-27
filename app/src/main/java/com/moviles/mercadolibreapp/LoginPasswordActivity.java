package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.moviles.mercadolibreapp.Interface.RegisterParse;
import com.moviles.mercadolibreapp.Model.Register;
import com.moviles.mercadolibreapp.databinding.ActivityLoginPasswordBinding;

import java.util.List;
import java.util.function.ToIntFunction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityLoginPasswordBinding activityLoginPasswordBinding;
    public String Email;
    public String contra;
    private int identificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginPasswordBinding = ActivityLoginPasswordBinding.inflate(getLayoutInflater());
        View view = activityLoginPasswordBinding.getRoot();
        setContentView(view);

        activityLoginPasswordBinding.btnBack.setOnClickListener(this);
        activityLoginPasswordBinding.btnLogin.setOnClickListener(this);
        activityLoginPasswordBinding.btnImNot.setOnClickListener(this);
        recibirEmail();
        activityLoginPasswordBinding.btnImNot.setText("no soy "+Email);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                Intent intentBack = new Intent(this, MainActivity.class);
                startActivity(intentBack);
                break;
            case R.id.btnLogin:
                //Intent intentLogin = new Intent(this, com.moviles.mercadolibreapp.HomeActivity.class);
                //startActivity(intentLogin);
                getLogin();
                break;
            case R.id.btnImNot:
                Intent intentEmail = new Intent(this, com.moviles.mercadolibreapp.LoginEmailActivity.class);
                startActivity(intentEmail);
                break;
        }
    }

    public void recibirEmail(){
        Bundle extras = getIntent().getExtras();
        Email= extras.getString("email");
    }
    public void  savePreferences(){
        Toast.makeText(this, Email, Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.SharedPreference) ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString( getString(R.string.Email) , Email);
        editor.putInt( getString(R.string.Identificacion), identificacion);
        editor.putString( getString(R.string.Status), "Logged");
        editor.apply();
    }
    private void getLogin(){
        contra = activityLoginPasswordBinding.etUserPassword.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterParse registerParse = retrofit.create(RegisterParse.class);
        Call<List<Register>> call = registerParse.getLogin(new Register(Email,contra));
        call.enqueue(new Callback<List<Register>>() {
            @Override
            public void onResponse(Call<List<Register>> call, Response<List<Register>> response) {
                if(!response.isSuccessful()){
                    //activityLoginPasswordBinding.txtPrueba.setText("codigo"+response.code());
                    return;
                }

                List<Register> listUSer = response.body();
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(LoginPasswordActivity.this);
                    mensaje.setMessage("exito en el login");
                    mensaje.setTitle("ingresando");
                    mensaje.show();

                for(Register register: listUSer){
                    identificacion = Integer.parseInt(register.getIdentificacion());
                }

                Intent intent = new Intent(LoginPasswordActivity.this, HomeActivity.class);
                savePreferences();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Register>> call, Throwable t) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(LoginPasswordActivity.this);
                mensaje.setMessage("usuario o contrasena incorrectos");
                mensaje.setTitle("ERROR");
                mensaje.show();
            }
        });
    }
}