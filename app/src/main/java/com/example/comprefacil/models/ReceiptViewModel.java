package com.example.comprefacil.models;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.comprefacil.activities.Config;
import com.example.comprefacil.activities.HttpRequest;
import com.example.comprefacil.activities.PurchasesActivity;
import com.example.comprefacil.activities.ReceiptActivity;
import com.example.comprefacil.activities.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ReceiptViewModel extends ViewModel {

    MutableLiveData<List<ProdutoData>> itens;

    ReceiptActivity context;
    String id_compra;

    public ReceiptViewModel() {

    }

    public void setId_compra(String id_compra) {
        this.id_compra = id_compra;
    }

    public LiveData<List<ProdutoData>> getItens() {
        if (itens == null) {
            itens = new MutableLiveData<List<ProdutoData>>();
            loadItens();
        }
        return itens;
    }

    void loadItens() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<ProdutoData> produtoDataList = new ArrayList<>();

                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_itens_nota.php", "GET", "UTF-8");
                httpRequest.addParam("id", id_compra);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("itensCompra");
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jCompra = jsonArray.getJSONObject(i);

                            String nome = jCompra.getString("nome");
                            String preco = jCompra.getString("preco");

                            ProdutoData produtoData = new ProdutoData(nome, preco);
                            produtoDataList.add(produtoData);
                        }
                        itens.postValue(produtoDataList);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

