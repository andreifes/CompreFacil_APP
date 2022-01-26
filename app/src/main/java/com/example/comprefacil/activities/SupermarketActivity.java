package com.example.comprefacil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comprefacil.R;
import com.example.comprefacil.adapters.AdapterSupermarket;
import com.example.comprefacil.models.ProdutoData;
import com.example.comprefacil.models.SupermarketViewModel;



import org.w3c.dom.Text;

import java.util.List;

public class SupermarketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarke);

        Intent i  = getIntent();
        String id = String.valueOf(i.getIntExtra("id", 0));

        RecyclerView rvProduto = findViewById(R.id.rv_produtos_supermarke);
        rvProduto.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvProduto.setLayoutManager(layoutManager);

        SupermarketViewModel supermarketViewModel = new ViewModelProvider(this, new SupermarketViewModel.SupermarketViewModelFactory(id)).get(SupermarketViewModel.class);
        LiveData<ProdutoData> produtos = supermarketViewModel.getProduto();
        produtos.observe(this, new Observer<ProdutoData>() {
            @Override
            public void onChanged(ProdutoData produtos) {

                ImageView imvProduto = findViewById(R.id.item_iv_produto_products);
                imvProduto.setImageBitmap(produtos.getImg());

                TextView tvNome = findViewById(R.id.item_tv_produto_products);
                tvNome.setText(produtos.getNome());

                TextView tvPreco = findViewById(R.id.item_tv_preço_products);
                tvPreco.setText(produtos.getPreco());
            }
        });


    }
}