package com.example.comprefacil.models;

import android.graphics.Bitmap;

public class MercadoData {
    public int id;
    public String nome;
    public String cidade;
    public Bitmap imagem;
    public String bairro;

    public MercadoData(int id, String nome, String cidade, String bairro, Bitmap imagem) {
        this.id = id;
        this.nome = nome;
        this.bairro = bairro;
        this.imagem = imagem;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public String getBairro() {
        return bairro;
    }
}
