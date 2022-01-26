package com.example.comprefacil.models;

import android.graphics.Bitmap;

public class ShoppingData {
    public int id;
    public String nome;
    public String preco;
    public Bitmap img;
    public int categoria;

    public ShoppingData(int id, String nome, String preco, Bitmap img, int categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.img = img;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
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
}
