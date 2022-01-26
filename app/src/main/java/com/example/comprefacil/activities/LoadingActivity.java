package com.example.comprefacil.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.comprefacil.R;

public class LoadingActivity extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent_loading = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent_loading);
                finish();
            }
        }, 1500);
    }

}