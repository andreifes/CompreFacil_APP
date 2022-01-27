package com.example.comprefacil.models;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.comprefacil.activities.Config;
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

public class SupermarketViewModel extends ViewModel {

    String id;
    //id = Integer.toS

    MutableLiveData<List<ProdutoData>> produtosLv;

    public SupermarketViewModel(String id) {
        this.id = id;
    }

    public LiveData<List<ProdutoData>> getProdutosLv() {
        if(this.produtosLv==null){
            produtosLv = new MutableLiveData<List<ProdutoData>>();
            loadProdutos();
        }
        return produtosLv;
    }

    void loadProdutos(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_all_produtos.php", "GET", "UTF-8");
                httpRequest.addParam("id_mercado", id);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("produtos");
                        List<ProdutoData> produtoDataList = new ArrayList<>();
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jProduto = jsonArray.getJSONObject(i);

                            int id_categoria = jProduto.getInt("id_categoria");
                            String preco = jProduto.getString("preco");
                            String nome = jProduto.getString("nome");
                            String categoria = jProduto.getString("nome_categoria");
                            String imageBase64 = jProduto.getString("img");
                            String id_produto = jProduto.getString("id");
                            imageBase64 = imageBase64.substring(imageBase64.indexOf(",") + 1);
                            Bitmap img_produto = Util.base642Bitmap(imageBase64);
                            //int id, String nome, String preco, Bitmap img, int categoria
                            //int id_mercado, int id_produto, String nome, String preco, Bitmap img, int categoria
                            ProdutoData produtoData = new ProdutoData(id, id_produto, nome, preco, img_produto, id_categoria);
                            produtoDataList.add(produtoData);

                        }
                        produtosLv.postValue(produtoDataList);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
            }


    static public class SupermarketViewModelFactory implements ViewModelProvider.Factory{
        String id;

        public SupermarketViewModelFactory(String id) {
            this.id = id;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new SupermarketViewModel(id);
        }
    }
}
