package com.example.comprefacil.activities;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comprefacil.R;
import com.example.comprefacil.adapters.AdapterHome;
import com.example.comprefacil.adapters.AdapterItensReceipt;
import com.example.comprefacil.models.HomeViewModel;
import com.example.comprefacil.models.MercadoData;
import com.example.comprefacil.models.ProdutoData;
import com.example.comprefacil.models.ReceiptViewModel;

import java.util.List;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        Intent i = getIntent();
        //Bitmap img_mercado = i.getParcelableExtra("img_mercado");
        String nome_mercado = i.getStringExtra("nome_mercado");
        String bairro_mercado = i.getStringExtra("bairro_mercado");
        String cidade_mercado = i.getStringExtra("cidade_mercado");
        String preco_total = i.getStringExtra("preco_total");
        String horario = i.getStringExtra("horario");
        String id_compra = i.getStringExtra("id_compra");

        String location = cidade_mercado + " - " + bairro_mercado;

        /*ImageView iv_mercado_receipt = findViewById(R.id.iv_mercado_receipt);
        iv_mercado_receipt.setImageBitmap(img_mercado); */

        TextView tv_nomemercado_receipt = findViewById(R.id.tv_nomemercado_receipt);
        tv_nomemercado_receipt.setText(nome_mercado);

        TextView Tvpreco = findViewById(R.id.tv_preço_receipt);
        Tvpreco.setText("RS$" + preco_total);

        TextView tv_localização_receipt = findViewById(R.id.tv_localização_receipt);
        tv_localização_receipt.setText(location);

        TextView tv_datahora_receipt = findViewById(R.id.tv_datahora_receipt);
        tv_datahora_receipt.setText(horario);

        TextView tv_idcompra_receipt = findViewById(R.id.tv_idcompra_receipt);
        tv_idcompra_receipt.setText("Pedido N°" + id_compra);

        TextView compra = findViewById(R.id.tv_soma_purchases);
        compra.setText("RS$" + preco_total);

        RecyclerView rv_receipt = findViewById(R.id.rv_notafiscal_receipt);
        rv_receipt.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv_receipt.setLayoutManager(layoutManager);


        ReceiptViewModel receiptViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);
        receiptViewModel.setId_compra(id_compra);

        LiveData<List<ProdutoData>> produtosLv = receiptViewModel.getItens();
        produtosLv.observe(this, new Observer<List<ProdutoData>>() {
            @Override
            public void onChanged(List<ProdutoData> listItens) {
                AdapterItensReceipt adapterItensReceipt =
                        new AdapterItensReceipt(ReceiptActivity.this, listItens);
                rv_receipt.setAdapter(adapterItensReceipt);
            }
        });



    }
}