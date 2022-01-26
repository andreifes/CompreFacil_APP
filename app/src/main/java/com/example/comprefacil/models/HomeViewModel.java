package com.example.comprefacil.models;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.comprefacil.activities.HttpRequest;
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

public class HomeViewModel extends ViewModel {

    int id;

    MutableLiveData<List<MercadoData>> itens;

    public HomeViewModel(){

    }

    public LiveData<List<MercadoData>> getItens(){
        if(itens==null){
            itens = new MutableLiveData<List<MercadoData>>();
            loadItens();
        }
        return itens;
    }

    void loadItens(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<MercadoData> homeItemDataList = new ArrayList<>();

                HttpRequest httpRequest = new HttpRequest("https://compre-facil.herokuapp.com/get_all_mercados.php", "GET", "UTF-8");

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);
                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("mercados");
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jMercado = jsonArray.getJSONObject(i);

                            int id = jMercado.getInt("id");
                            String nome = jMercado.getString("nome");
                            String cidade = jMercado.getString("cidade");
                            String bairro = jMercado.getString("bairro");
                            String imageBase64 = jMercado.getString("img");
                            imageBase64 = imageBase64.substring(imageBase64.indexOf(",") + 1);
                            Bitmap img_mercado = Util.base642Bitmap(imageBase64);

                            MercadoData mercadoData = new MercadoData(id, nome, cidade, bairro, img_mercado);
                            homeItemDataList.add(mercadoData);
                        }
                        itens.postValue(homeItemDataList);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
