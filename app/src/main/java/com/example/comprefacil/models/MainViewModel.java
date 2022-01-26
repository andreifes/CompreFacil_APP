package com.example.comprefacil.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    MutableLiveData<List<MercadoData>> itens;

    public LiveData<List<MercadoData>> getItens(){
        if (itens==null){

            itens = new MutableLiveData<List<MercadoData>>();
            loadItens();
        }
        return itens;

    }

    void loadItens(){

    }
}
