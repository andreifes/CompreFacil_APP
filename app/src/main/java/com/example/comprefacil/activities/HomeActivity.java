package com.example.comprefacil.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.comprefacil.R;
import com.example.comprefacil.adapters.AdapterHome;
import com.example.comprefacil.models.MercadoData;
import com.example.comprefacil.models.HomeViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imgCarrinho = findViewById(R.id.iv_carrinho_toolbar);
        imgCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intado = new Intent(HomeActivity.this, ShoppingCartActivity.class);
                startActivity(intado);
            }
        });

        ImageView imgPerfil = findViewById(R.id.iv_perfil_toolbar);
        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intado1 = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intado1);
            }
        });

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        RecyclerView rv_home = findViewById(R.id.rv_supermercados_home);
        rv_home.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv_home.setLayoutManager(layoutManager);

        LiveData<List<MercadoData>> mercadosLv = homeViewModel.getItens();
        mercadosLv.observe(this, new Observer<List<MercadoData>>() {

            @Override
            public void onChanged(List<MercadoData> mercadoDataList) {

                AdapterHome myAdapterHome = new AdapterHome(HomeActivity.this, mercadoDataList);
                rv_home.setAdapter(myAdapterHome);
            }
        });
    }
}