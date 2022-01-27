package com.example.comprefacil.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comprefacil.R;
import com.example.comprefacil.activities.LoginActivity;
import com.example.comprefacil.activities.SupermarketActivity;
import com.example.comprefacil.models.ProdutoData;
import com.example.comprefacil.models.ShoppingCart;
import com.example.comprefacil.models.ShoppingCartViewModel;

import java.util.List;

public class AdapterSupermarket extends RecyclerView.Adapter {

    SupermarketActivity context;


    List<ProdutoData> itens;

    public AdapterSupermarket(SupermarketActivity context, List<ProdutoData> itens) {
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
        preco.setText("R" + produto.getPreco());

        ImageView add_prod = v.findViewById(R.id.item_iv_addcarrinho_products);
        add_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShoppingCart.addProduto(produto);
                Toast.makeText(context, "Produto adicionado no carrinho.", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return this.itens.size();
    }
}
