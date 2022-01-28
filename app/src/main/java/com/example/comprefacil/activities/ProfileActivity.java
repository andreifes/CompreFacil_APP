package com.example.comprefacil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comprefacil.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        View acb_compras_profile = findViewById(R.id.acb_compras_profile);
        acb_compras_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intado = new Intent(ProfileActivity.this, PurchasesActivity.class);
                startActivity(intado);
            }
        });

        ImageView imgCarrinho = findViewById(R.id.iv_carrinho_toolbar);
        imgCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intado2 = new Intent(ProfileActivity.this, ShoppingCartActivity.class);
                startActivity(intado2);
            }
        });

        //tv nome profile, compra, telefone
        TextView etNome = findViewById(R.id.tv_nome_profile);
        TextView etEmail = findViewById(R.id.tv_email_profile);
        TextView etTelefone = findViewById(R.id.tv_compras_profile);

        etEmail.setText(Config.getLogin(ProfileActivity.this));


        //Deslogar do aplicativo
        View acb_sair_profile = findViewById(R.id.acb_sair_profile);
        acb_sair_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Config.setLogin(ProfileActivity.this, "");
                Config.setPassword(ProfileActivity.this, "");
                Intent intent_sair = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent_sair);
            }
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_dados_user.php", "GET", "UTF-8");
                httpRequest.addParam("email", Config.getLogin(ProfileActivity.this));

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        JSONObject usuarioObject = jsonObject.getJSONObject("usuario");
                        String nome = usuarioObject.getString("nome");
                        String telefone = usuarioObject.getString("telefone");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                etNome.setText(nome);
                                etTelefone.setText(telefone);
                            }
                        });
                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ProfileActivity.this, error, Toast.LENGTH_LONG).show();
                                finish();
                            }

                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}