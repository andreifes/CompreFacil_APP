package com.example.comprefacil.models;

import android.graphics.Bitmap;

public class ProdutoData {
    String id_produto;
    String id_item;
    String id_mercado;
    public String nome;
    public String preco;
    public Bitmap img;
    public int categoria;

    public ProdutoData(String id_mercado, String id_produto, String id_item, String nome, String preco, Bitmap img, int categoria) {
        this.id_produto = id_produto;
        this.id_item = id_item;
        this.id_mercado = id_mercado;
        this.nome = nome;
        this.preco = preco;
        this.img = img;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getPreco() {
        return preco;
    }

    public Bitmap getImg() {
        return img;
    }

    public int getCategoria() {
        return categoria;
    }

    public String getId_mercado() {
        return id_mercado;
    }

    public String getId_item() {
        return id_item;
    }
}
