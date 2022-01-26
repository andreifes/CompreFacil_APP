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
import com.example.comprefacil.models.ProdutoData;

import java.util.List;

public class AdapterSupermarket extends RecyclerView.Adapter {

    Context context;

    List<ProdutoData> itens;

    public AdapterSupermarket(Context context, List<ProdutoData> itens) {
        this.context = context;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_rv_products, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProdutoData produto = this.itens.get(position);

        View v = holder.itemView;

        ImageView produto_img = v.findViewById(R.id.item_iv_produto_products);
        produto_img.setImageBitmap(produto.getImg());

        TextView nome = v.findViewById(R.id.item_tv_produto_products);
        nome.setText(produto.getNome());

        TextView preco = v.findViewById(R.id.item_tv_preço_products);
        preco.setText(produto.getPreco());

        ImageView add_prod = v.findViewById(R.id.item_iv_addcarrinho_products);
        add_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return this.itens.size();
    }
}
