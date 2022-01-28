package com.example.comprefacil.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comprefacil.R;
import com.example.comprefacil.activities.PurchasesActivity;
import com.example.comprefacil.activities.ReceiptActivity;
import com.example.comprefacil.models.CompraData;

import java.io.File;
import java.util.List;

public class AdapterPurchases extends RecyclerView.Adapter {

    Context context;
    List<CompraData> itens;

    public AdapterPurchases(Context context, List<CompraData> itens) {
        this.context = context;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_rv_purchases, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CompraData compra = this.itens.get(position);

        View v = holder.itemView;

        //data hora, cidade mercado, id compra, preco, nome mercado, img produto

        TextView idCompra = v.findViewById(R.id.item_tv_status_purchase);
        idCompra.setText(compra.getId_compra());

        TextView dataHora = v.findViewById(R.id.item_tv_datahora_purchases);
        dataHora.setText(compra.getData_hora());

        String localizacao = compra.getCidade_mercado() + " - " + compra.getBairro_mercado();

        TextView location = v.findViewById(R.id.item_tv_cidade_purchases);
        location.setText(localizacao);

        TextView nomeMercado = v.findViewById(R.id.item_tv_nome_purchases);
        nomeMercado.setText(compra.getNome_mercado());

        TextView preco = v.findViewById(R.id.item_tv_preço_purchase);
        preco.setText(compra.getPreco());

        ImageView imgProduto = v.findViewById(R.id.item_iv_logo_purchases);
        imgProduto.setImageBitmap(compra.getImg_mercado());

        v.setOnClickListener(v1 -> {
            Intent i = new Intent(context, ReceiptActivity.class);

            i.putExtra("id_compra", compra.getId_compra());
            //i.putExtra("img_mercado", compra.getImg_mercado());
            i.putExtra("cidade_mercado", compra.getCidade_mercado());
            i.putExtra("bairro_mercado", compra.getBairro_mercado());
            i.putExtra("preco_total", compra.getPreco());
            i.putExtra("horario", compra.getData_hora());
            i.putExtra("nome_mercado", compra.getNome_mercado());

            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return this.itens.size();
    }
}
