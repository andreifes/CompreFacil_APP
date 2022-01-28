package com.example.comprefacil.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comprefacil.R;
import com.example.comprefacil.models.CompraData;
import com.example.comprefacil.models.ProdutoData;

import java.util.List;

public class AdapterItensReceipt extends RecyclerView.Adapter {

    Context context;

    List<ProdutoData> itens;

    public AdapterItensReceipt(Context context, List<ProdutoData> itens) {
        this.context = context;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_tv_receipt, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProdutoData produtoData = this.itens.get(position);

        View v = holder.itemView;

        TextView nomeProduto = v.findViewById(R.id.item_tv_produto_receipt);
        nomeProduto.setText(produtoData.getNome());

        TextView preco = v.findViewById(R.id.item_tv_valor_receipt);
        preco.setText(produtoData.getPreco());
    }

    @Override
    public int getItemCount() {
        return this.itens.size();
    }
}
