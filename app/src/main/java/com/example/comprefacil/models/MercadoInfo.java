package com.example.comprefacil.models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class MercadoInfo {


    static String nome;
    static String bairro;
    static String cidade;

    public static String getNome() {
        return nome;
    }

    public static String getBairro() {
        return bairro;
    }

    public static String getCidade() {
        return cidade;
    }

    public static Bitmap getImg() {
        return img;
    }

    public static int getId() {
        return id;
    }

    static Bitmap img;
    static int id;

    public static void setNome(String nome) {
        MercadoInfo.nome = nome;
    }

    public static void setBairro(String bairro) {
        MercadoInfo.bairro = bairro;
    }

    public static void setCidade(String cidade) {
        MercadoInfo.cidade = cidade;
    }

    public static void setImg(Bitmap img) {
        MercadoInfo.img = img;
    }

    public static void setId(int id) {
        MercadoInfo.id = id;
    }
}
