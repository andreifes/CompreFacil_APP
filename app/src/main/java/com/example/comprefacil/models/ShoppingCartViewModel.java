package com.example.comprefacil.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ShoppingCartViewModel extends ViewModel {
    int id;

    MutableLiveData<List<ShoppingData>> itens;

    public ShoppingCartViewModel() {
    }

    public LiveData<List<ShoppingData>> getItens(){
        if(itens==null){
            itens = new MutableLiveData<List<ShoppingData>>();
            loadItens();
        }
        return itens;
    }

    void loadItens() {
    }
}

