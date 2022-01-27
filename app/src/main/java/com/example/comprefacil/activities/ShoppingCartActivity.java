package com.example.comprefacil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.comprefacil.R;
import com.example.comprefacil.adapters.AdapterShoppingCart;
import com.example.comprefacil.models.ProdutoData;
import com.example.comprefacil.models.ShoppingCart;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        RecyclerView rvItem = findViewById(R.id.rv_itens_cart);
        rvItem.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItem.setLayoutManager(layoutManager);

        AdapterShoppingCart adapterShoppingCart = new AdapterShoppingCart(ShoppingCartActivity.this, ShoppingCart.getItens());
        rvItem.setAdapter(adapterShoppingCart);

        Button acb_finaliza_cart = findViewById(R.id.acb_finalizarcompra_shoppingcart);
        acb_finaliza_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ProdutoData> itens;

                itens = ShoppingCart.getItens();
                if(!itens.isEmpty()){
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "cria_compra.php", "POST", "UTF-8");
                        httpRequest.addParam("email_usuario", Config.getLogin(ShoppingCartActivity.this));

                        int id1 = 0;
                        try {
                            InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            if (success == 1) {
                                id1 = jsonObject.getInt("id");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast.makeText(ShoppingCartActivity.this, "Compra concluída com sucesso", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            } else {
                                final String error = jsonObject.getString("error");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ShoppingCartActivity.this, error, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }

                        // Inserir produtos na compra
                        for (ProdutoData produto : itens) {
                            try {
                                HttpRequest httpRequest2 = new HttpRequest(Config.SERVER_URL_BASE + "compra_item.php", "POST", "UTF-8");
                                httpRequest2.addParam("id_compra", String.valueOf(id1));
                                httpRequest2.addParam("id_item", produto.getId_item());

                                InputStream is = httpRequest2.execute();
                                String result = Util.inputStream2String(is, "UTF-8");
                                httpRequest2.finish();

                                JSONObject jsonObject = new JSONObject(result);
                                final int success = jsonObject.getInt("success");
                                if (success == 1) {
                                    ShoppingCart.deleteItem(produto);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ShoppingCartActivity.this, "Compra concluída com sucesso", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });
                                } else {
                                    final String error = jsonObject.getString("error");
                                    Log.d("COMPRA_ITEM_ERROR", "ERRO: " + error);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ShoppingCartActivity.this, error, Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
            }else{
                    Toast.makeText(ShoppingCartActivity.this, "Não há nenhum produto no carrinho.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}