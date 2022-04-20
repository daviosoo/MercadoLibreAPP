package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.moviles.mercadolibreapp.Interface.RegisterParse;
import com.moviles.mercadolibreapp.Interface.RegisterUser;
import com.moviles.mercadolibreapp.Model.Register;
import com.moviles.mercadolibreapp.databinding.ActivityRegisterBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding activityRegisterBinding;
    public Register user;

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
                Intent intent = new Intent(this, LoginIconActivity.class);
                startActivity(intent);
                break;

            case R.id.btnRegister:
                setUser();
                //Toast toast = Toast.makeText(this, "Registro completo", Toast.LENGTH_SHORT);
        }
    }

    /*public void Registrar(View view){
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
    }*/

    public void setUser(){
        user=new Register();
        user.setIdentificacion(activityRegisterBinding.etIdentity.getText().toString());
        user.setEmail(activityRegisterBinding.etEmail.getText().toString());
        user.setCelular(activityRegisterBinding.etNumber.getText().toString());
        user.setContra(activityRegisterBinding.etPassword.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.18.75.135:80/MercadoLibreAPI/features/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterUser registerUser = retrofit.create(RegisterUser.class);
        Call<String> call = registerUser.setUser(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    //activityLoginPasswordBinding.txtPrueba.setText("codigo"+response.code());
                    return;
                }

                String respuesta = response.body();
                if (respuesta.equals("Exito en el registro")){
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(RegisterActivity.this);
                    mensaje.setMessage(respuesta);
                    mensaje.setTitle("REGISTRADO");
                    mensaje.show();
                    Intent intent = new Intent(RegisterActivity.this, LoginEmailActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(RegisterActivity.this);
                    mensaje.setMessage("fallo en el registro ok");
                    mensaje.setTitle("ERROR");
                    mensaje.show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(RegisterActivity.this);
                mensaje.setMessage(t.getMessage());
                mensaje.setTitle("ERROR");
                mensaje.show();
            }
        });
    }
}