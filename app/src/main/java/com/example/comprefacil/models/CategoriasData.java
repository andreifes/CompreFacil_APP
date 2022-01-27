package com.example.comprefacil.models;

import android.net.Uri;

public class CategoriasData {
    public String nome;
    public int id;
    public Uri imgCaregoria;

    public CategoriasData(String nome, int id, Uri imgCategoria) {
        this.nome = nome;
        this.id = id;
        this.imgCaregoria = imgCategoria;
    }
}
