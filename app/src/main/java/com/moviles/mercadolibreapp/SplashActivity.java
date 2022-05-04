package com.moviles.mercadolibreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashActivity extends AppCompatActivity {

    String status;

    public void readPreferences() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.SharedPreference), Context.MODE_PRIVATE);
        status = sharedPref.getString("Status", "");
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        readPreferences();
        startTimer();
    }

    public final void startTimer() {
        (new CountDownTimer(3000L, 1000L) {
            public void onTick(long millisUntilFinished) { }
            public void onFinish() {
                if (!status.isEmpty())
                {
                    Intent intentHome = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intentHome);
                }
                else
                {
                    Intent intentMain = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intentMain);
                }
            }
        }).start();
    }
}