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
import com.example.comprefacil.models.ProdutoData;
import com.example.comprefacil.models.ShoppingCart;
import com.example.comprefacil.models.ShoppingData;

import java.util.List;

public class AdapterShoppingCart extends RecyclerView.Adapter {

    Context context;

    List<ProdutoData> itens;

    public AdapterShoppingCart(Context context, List<ProdutoData> itens) {
        this.context = context;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_rv_shopping_cart, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProdutoData item = this.itens.get(position);

        View v = holder.itemView;

        ImageView produto_img = v.findViewById(R.id.item_iv_produto_cart);
        produto_img.setImageBitmap(item.getImg());

        TextView nome = v.findViewById(R.id.item_tv_nome_cart);
        nome.setText(item.getNome());

        TextView preco = v.findViewById(R.id.item_tv_preco_cart);
        preco.setText(item.getPreco());

        ImageView delete_img = v.findViewById(R.id.item_iv_delete_cart);
        delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //código para deletar item do carrinho
                itens.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                ShoppingCart.deleteItem(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itens.size();
    }
}
