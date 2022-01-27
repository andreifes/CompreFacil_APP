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
import com.example.comprefacil.activities.SupermarketActivity;
import com.example.comprefacil.models.MercadoData;
import com.example.comprefacil.models.MercadoInfo;

import java.util.List;

public class AdapterHome extends RecyclerView.Adapter {

    Context context;

    List<MercadoData> itens;

    public AdapterHome(Context context, List<MercadoData> itens) {
        this.context = context;
        this.itens = itens;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_rv_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MercadoData homeItem = this.itens.get(position);

        View v = holder.itemView;

        ImageView iv_supermercado = v.findViewById(R.id.item_iv_supermercado_home);
        iv_supermercado.setImageBitmap(homeItem.getImagem());

        TextView localizacao = v.findViewById(R.id.item_tv_localizacao_home);
        String location = homeItem.getCidade() + " - " + homeItem.getBairro();
        localizacao.setText(location);

        TextView nome = v.findViewById(R.id.item_tv_supermercado_home);
        nome.setText(homeItem.getNome());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SupermarketActivity.class);
                i.putExtra("id", homeItem.getId());
                MercadoInfo.setImg(homeItem.getImagem());
                MercadoInfo.setId(homeItem.getId());
                MercadoInfo.setNome(homeItem.getNome());
                MercadoInfo.setCidade(homeItem.getCidade());
                MercadoInfo.setBairro(homeItem.getBairro());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.itens.size();
    }
}
