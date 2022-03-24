package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.moviles.mercadolibreapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding activityRegisterBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = activityRegisterBinding.getRoot();
        setContentView(view);

        activityRegisterBinding.btnRegister.setOnClickListener(this);
        activityRegisterBinding.btnBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;

            //case R.id.btnRegister:
                //Toast toast = Toast.makeText(this, "Registro completo", Toast.LENGTH_SHORT);
        }
    }

    public void Registrar(View view){
        String email = activityRegisterBinding.etEmail.toString();
        String identity = activityRegisterBinding.etIdentity.toString();
        String phone = activityRegisterBinding.etNumber.toString();
        String password = activityRegisterBinding.etPassword.toString();

        if(email.length()==0){
            Toast.makeText(this,"Debes ingresar un email",Toast.LENGTH_LONG).show();
        }
        if(PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Debes ingresar un correo electronico valido",Toast.LENGTH_LONG).show();
        }

        if(identity.length()==0){
            Toast.makeText(this,"Debes ingresar un numero de documento",Toast.LENGTH_LONG).show();
        }

        if(phone.length()==0){
            Toast.makeText(this,"Debes ingresar un teléfono",Toast.LENGTH_LONG).show();
        }

        if(password.length()==0){
            Toast.makeText(this,"Debes ingresar una contraseña",Toast.LENGTH_LONG).show();
        }

        if(email.length()!=0 &&
                identity.length()!=0 &&
                phone.length()!=0 &&
                password.length()!=0 &&
                password.length()>=10){
            Toast.makeText(this,"Registro en proceso...",Toast.LENGTH_LONG).show();
        }
    }
}