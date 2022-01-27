package com.example.comprefacil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.comprefacil.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView imgCarrinho = findViewById(R.id.iv_carrinho_toolbar);
        imgCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intado = new Intent(ProfileActivity.this, ShoppingCartActivity.class);
                startActivity(intado);
            }
        });

        ImageView imgPerfil = findViewById(R.id.iv_perfil_toolbar);
        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intado1 = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intado1);
            }
        });


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