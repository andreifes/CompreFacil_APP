package com.example.comprefacil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comprefacil.R;
import com.example.comprefacil.models.CategoriasData;
import com.example.comprefacil.models.MercadoData;

import java.util.List;

public class AdapterCategorias extends RecyclerView.Adapter {

    public AdapterCategorias(List<CategoriasData> itens) {
        this.itens = itens;
    }

    List<CategoriasData> itens;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.item_rv_categories, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoriasData categoriasData = itens.get(position);

        View v = holder.itemView;

        ImageView imgCategoria = v.findViewById(R.id.item_iv_categories_supermarket);
        imgCategoria.setImageURI(categoriasData.imgCaregoria);

        TextView nome = v.findViewById(R.id.item_tv_categories_supermarket);
        nome.setText(categoriasData.nome);
    }

    @Override
    public int getItemCount() {
        return this.itens.size();
    }
}
