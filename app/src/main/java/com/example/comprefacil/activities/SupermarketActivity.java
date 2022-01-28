package com.example.comprefacil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comprefacil.R;
import com.example.comprefacil.adapters.AdapterCategorias;
import com.example.comprefacil.adapters.AdapterSupermarket;
import com.example.comprefacil.models.CategoriasData;
import com.example.comprefacil.models.MercadoData;
import com.example.comprefacil.models.MercadoInfo;
import com.example.comprefacil.models.ProdutoData;
import com.example.comprefacil.models.SupermarketViewModel;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SupermarketActivity extends AppCompatActivity {

    List<CategoriasData> categoriasData = new ArrayList<>();
    AdapterCategorias adapterCategorias;
    List<ProdutoData> itens = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarke);


        RecyclerView rvCategorias = findViewById(R.id.rv_categorias_supermarke);
        rvCategorias.setHasFixedSize(true);


        //adicione daqui
        Uri img_categoria = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.bebidas);
        CategoriasData item0 = new CategoriasData("Bebidas", 3, img_categoria);

        categoriasData.add(item0);

        Uri img_categoria1 = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.biscoitos);
        CategoriasData item1 = new CategoriasData("Biscoitos", 9, img_categoria1);

        categoriasData.add(item1);

        Uri img_categoria2 = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.carnes);
        CategoriasData item2 = new CategoriasData("Carnes", 1, img_categoria2);

        categoriasData.add(item2);

        Uri img_categoria3 = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.petshop);
        CategoriasData item3 = new CategoriasData("Pet Shop", 8, img_categoria3);
        categoriasData.add(item3);


        Uri img_categoria4 = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.doces);
        CategoriasData item4 = new CategoriasData("Doces", 10, img_categoria4);
        categoriasData.add(item4);

        Uri img_categoria5 = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.frios);
        CategoriasData item5 = new CategoriasData("Frios", 2, img_categoria5);
        categoriasData.add(item5);

        Uri img_categoria6 = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.higiene);
        CategoriasData item6 = new CategoriasData("Higiene", 4, img_categoria6);
        categoriasData.add(item6);

        Uri img_categoria7 = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.hortifruti);
        CategoriasData item7 = new CategoriasData("Hortifruti", 6, img_categoria7);
        categoriasData.add(item7);

        Uri img_categoria8 = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.limpeza);
        CategoriasData item8 = new CategoriasData("Limpeza", 5, img_categoria8);
        categoriasData.add(item8);

        Uri img_categoria9 = Uri.parse("android.resource://com.example.comprefacil/" + R.drawable.padaria);
        CategoriasData item9 = new CategoriasData("Padaria", 7, img_categoria9);
        categoriasData.add(item9);

        adapterCategorias = new AdapterCategorias(categoriasData);

        RecyclerView.LayoutManager layoutManagerCategoria = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvCategorias.setLayoutManager(layoutManagerCategoria);
        rvCategorias.setAdapter(adapterCategorias);

        ImageView imgCarrinho = findViewById(R.id.iv_carrinho_toolbar);
        imgCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intado = new Intent(SupermarketActivity.this, ShoppingCartActivity.class);
                startActivity(intado);
            }
        });

        ImageView imgPerfil = findViewById(R.id.iv_perfil_toolbar);
        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intado1 = new Intent(SupermarketActivity.this, ProfileActivity.class);
                startActivity(intado1);
            }
        });

        String bairro = MercadoInfo.getBairro();
        String cidade = MercadoInfo.getCidade();

        Intent i  = getIntent();
        String id = String.valueOf(i.getIntExtra("id", 0));
        ImageView imgMercado = findViewById(R.id.iv_supermercado_supermarke);
        imgMercado.setImageBitmap(MercadoInfo.getImg());

        TextView nomeMercado = findViewById(R.id.tv_nome_supermarke);
        nomeMercado.setText(MercadoInfo.getNome());

        TextView localizacao = findViewById(R.id.tv_localizacao_supermarke);
        localizacao.setText(cidade + " - " + bairro);

        RecyclerView rvProduto = findViewById(R.id.rv_produtos_supermarke);
        rvProduto.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvProduto.setLayoutManager(layoutManager);

        SupermarketViewModel supermarketViewModel = new ViewModelProvider(this, new SupermarketViewModel.SupermarketViewModelFactory(id)).get(SupermarketViewModel.class);
        LiveData<List<ProdutoData>> produtos = supermarketViewModel.getProdutosLv();
        produtos.observe(this, new Observer<List<ProdutoData>>() {
            @Override
            public void onChanged(List<ProdutoData> produtos) {


                AdapterSupermarket adapterSupermarket = new AdapterSupermarket(SupermarketActivity.this, produtos);
                rvProduto.setAdapter(adapterSupermarket);
            }
        });




    }
}