package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.moviles.mercadolibreapp.databinding.ActivityBuyBinding;
import com.moviles.mercadolibreapp.databinding.ActivityOffertsBinding;

public class BuyActivity extends AppCompatActivity {

    ActivityBuyBinding activityBuyBinding;
    TextView salir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityBuyBinding = ActivityBuyBinding.inflate(getLayoutInflater());
        View view = activityBuyBinding.getRoot();
        setContentView(view);
        startTimer();
    }

    public final void startTimer() {
        (new CountDownTimer(3000L, 1000L) {
            public void onTick(long millisUntilFinished) { }
            public void onFinish() {
                    Intent intentHome = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intentHome);
            }
        }).start();
    }
}