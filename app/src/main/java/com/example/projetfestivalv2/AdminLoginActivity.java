package com.example.projetfestivalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class AdminLoginActivity extends AppCompatActivity {
    Button butCon;
    EditText login;
    EditText password;
    ImageView charge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().hide();

        butCon = findViewById(R.id.button);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        charge = findViewById(R.id.chargementImage);

        Glide.with(this).load(R.drawable.chargement).into(charge);

        charge.setVisibility(View.GONE);
        String url = "http://10.0.2.2:3000/admin/login";

        butCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                charge.setVisibility(View.VISIBLE);
                butCon.setVisibility(View.GONE);
                login.setVisibility(View.GONE);
                password.setVisibility(View.GONE);

                Toast.makeText(AdminLoginActivity.this, "Connexion en cours, veuillez patienter.", Toast.LENGTH_SHORT).show();
                Ion.with(AdminLoginActivity.this.getApplicationContext())
                        .load(url)
                        .setBodyParameter("login", String.valueOf(login.getText()))
                        .setBodyParameter("password", String.valueOf(password.getText()))
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result == null){
                                    Toast.makeText(AdminLoginActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                                    charge.setVisibility(View.GONE);
                                    butCon.setVisibility(View.VISIBLE);
                                    login.setVisibility(View.VISIBLE);
                                    password.setVisibility(View.VISIBLE);
                                } else {
                                    int idAdm = result.get("idAdmin").getAsInt();
                                    String token = result.get("token").getAsString();
                                    Toast.makeText(AdminLoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AdminLoginActivity.this, AdminControlPanelActivity.class);
                                    intent.putExtra("idAdm", idAdm);
                                    intent.putExtra("token", token);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}