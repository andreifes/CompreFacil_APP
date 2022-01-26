package com.example.comprefacil.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.comprefacil.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //Deslogar do aplicativo
        View acb_sair_profile = findViewById(R.id.acb_sair_profile);
        acb_sair_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Config.setLogin(ProfileActivity.this, "");
                Config.setPassword(ProfileActivity.this, "");
                Intent intent_sair = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent_sair);
            }
        });
    }
}