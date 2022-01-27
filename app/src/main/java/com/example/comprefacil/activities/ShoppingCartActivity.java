package com.example.comprefacil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.comprefacil.R;
import com.example.comprefacil.adapters.AdapterHome;
import com.example.comprefacil.adapters.AdapterShoppingCart;
import com.example.comprefacil.models.HomeViewModel;
import com.example.comprefacil.models.MercadoData;
import com.example.comprefacil.models.ShoppingCart;
import com.example.comprefacil.models.ShoppingCartViewModel;
import com.example.comprefacil.models.ShoppingData;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        Intent i = getIntent();
        String id = String.valueOf(i.getIntExtra("id", 0));

        RecyclerView rvItem = findViewById(R.id.rv_itens_cart);
        rvItem.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItem.setLayoutManager(layoutManager);

        AdapterShoppingCart adapterShoppingCart = new AdapterShoppingCart(ShoppingCartActivity.this, ShoppingCart.getItens());
        rvItem.setAdapter(adapterShoppingCart);

    }
}