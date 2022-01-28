package com.example.comprefacil.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.comprefacil.activities.Config;
import com.example.comprefacil.activities.HttpRequest;
import com.example.comprefacil.activities.PurchasesActivity;
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

public class PurchasesViewModel extends ViewModel {

    String id;
    MutableLiveData<List<CompraData>> itens;
    PurchasesActivity context;

    public PurchasesViewModel(String id) {
        this.id = id;
    }


    public LiveData<List<CompraData>> getItens() {
        if (itens == null) {
            itens = new MutableLiveData<List<CompraData>>();
            loadItens();
        }
        return itens;
    }

    void loadItens() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<CompraData> compraDataList = new ArrayList<>();

                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_compras.php", "GET", "UTF-8");
                httpRequest.addParam("email_usuario", Config.getLogin(context));
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);
                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("compras");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jCompra = jsonArray.getJSONObject(i);

                            String id_compra = String.valueOf(jCompra.getInt("id_compra"));
                            String data_hora = jCompra.getString("data_hora");
                            String cidade = jCompra.getString("cidade_mercado");
                            String nome_mercado = jCompra.getString("nome_mercado");
                            String preco = jCompra.getString("preco");
                            String imageBase64 = jCompra.getString("img_produto");
                            imageBase64 = imageBase64.substring(imageBase64.indexOf(",") + 1);
                            Bitmap img_produto = Util.base642Bitmap(imageBase64);

                            //String data_hora, String cidade_mercado, String id_compra, String nome_mercado, String preco, Bitmap img_produto)
                            CompraData compraData = new CompraData(data_hora, cidade, id_compra, nome_mercado, preco, img_produto);
                            compraDataList.add(compraData);
                        }
                        itens.postValue(compraDataList);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}


