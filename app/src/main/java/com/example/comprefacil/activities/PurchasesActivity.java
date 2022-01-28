package com.example.comprefacil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.example.comprefacil.R;
import com.example.comprefacil.adapters.AdapterPurchases;
import com.example.comprefacil.models.CompraData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PurchasesActivity extends AppCompatActivity {

    MutableLiveData<List<CompraData>> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        RecyclerView rvCompras = findViewById(R.id.rv_compras_purchases);
        rvCompras.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCompras.setLayoutManager(layoutManager);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<CompraData> compraDataList = new ArrayList<>();

                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_compras.php", "GET", "UTF-8");

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);
                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("compras");
                        for(int i = 0; i < jsonArray.length(); i++){
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

        LiveData<List<CompraData>> itensLv = itens;
        itens.observe(this, new Observer<List<CompraData>>() {
                    @Override
                    public void onChanged(List<CompraData> compraData) {

                        AdapterPurchases adapterPurchases = new AdapterPurchases(PurchasesActivity.this, compraData);
                        rvCompras.setAdapter(adapterPurchases);

                    }
                });

    }
}