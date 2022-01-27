package com.example.comprefacil.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    static public List<ProdutoData> getItens() {
        return itens;
    }

    static List<ProdutoData> itens = new ArrayList<>();

    static public void addProduto(ProdutoData produto){
        itens.add(produto);
    }

    static public void deleteItem(ProdutoData deletedItem){
       itens.remove(deletedItem);
    }
}
