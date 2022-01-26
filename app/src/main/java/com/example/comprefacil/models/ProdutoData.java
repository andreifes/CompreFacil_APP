package com.example.comprefacil.models;

import android.graphics.Bitmap;

public class ProdutoData {
    int id_mercado;
    public String nome;
    public String preco;
    public Bitmap img;
    public int categoria;

    public ProdutoData(String nome, String preco, Bitmap img, int categoria) {
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

    public int getId_mercado() {
        return id_mercado;
    }
}
