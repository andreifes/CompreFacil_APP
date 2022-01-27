package com.example.comprefacil.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartViewModel extends ViewModel {

    public List<ProdutoData> getItens() {
        return itens;
    }

    List<ProdutoData> itens = new ArrayList<>();

    public ShoppingCartViewModel() {
    }

    public void addProduto(ProdutoData produto){
        itens.add(produto);
    }
}

