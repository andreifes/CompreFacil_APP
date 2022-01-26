package com.example.comprefacil.models;

import android.graphics.Bitmap;

public class ShoppingData {
    public int id;
    public String nome;
    public String preco;
    public Bitmap imgItem;
    public Bitmap imgDelete;

    public ShoppingData(int id, String nome, String preco, Bitmap imgItem, Bitmap imgDelete) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.imgItem = imgItem;
        this.imgDelete = imgDelete;
    }

    public ShoppingData(String nome, String preco, Bitmap imgItem, Bitmap imgDelete) {
        this.nome = nome;
        this.preco = preco;
        this.imgItem = imgItem;
        this.imgDelete = imgDelete;
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

    public Bitmap getImgItem() {
        return imgItem;
    }

    public Bitmap getImgDelete() {
        return imgDelete;
    }
}
