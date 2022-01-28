package com.example.comprefacil.models;

import android.graphics.Bitmap;

public class CompraData {
    public String data_hora;
    public String cidade_mercado;
    public String bairro_mercado;
    public String id_compra;
    public String nome_mercado;
    public String preco;
    public Bitmap img_mercado;


    public CompraData(String data_hora, String cidade_mercado,String bairro_mercado, String id_compra, String nome_mercado, String preco, Bitmap img_produto) {
        this.data_hora = data_hora;
        this.cidade_mercado = cidade_mercado;
        this.bairro_mercado = bairro_mercado;
        this.id_compra = id_compra;
        this.nome_mercado = nome_mercado;
        this.preco = preco;
        this.img_mercado = img_produto;
    }

    public String getData_hora() {
        return data_hora;
    }

    public String getCidade_mercado() {
        return cidade_mercado;
    }

    public String getId_compra() {
        return id_compra;
    }

    public String getNome_mercado() {
        return nome_mercado;
    }

    public String getPreco() {
        return preco;
    }

    public Bitmap getImg_mercado() {
        return img_mercado;
    }

    public String getBairro_mercado() {
        return bairro_mercado;
    }
}
