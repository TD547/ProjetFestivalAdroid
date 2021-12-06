package com.example.projetfestivalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class AjouterArtisteActivity extends AppCompatActivity {
    Intent intent;
    EditText nom;
    EditText photo;
    EditText style;
    EditText h;
    EditText d;
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_artiste);
        getSupportActionBar().hide();

        intent = this.getIntent();
        int idAdm = intent.getIntExtra("idAdm",0);
        String token = intent.getStringExtra("token");

        nom = findViewById(R.id.modifNom);
        photo = findViewById(R.id.modifPhoto);
        style = findViewById(R.id.modifStyle);
        h = findViewById(R.id.modifHP);
        d = findViewById(R.id.modifDP);
        but = findViewById(R.id.button3);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2:3000/artiste/";
                Ion.with(AjouterArtisteActivity.this.getApplicationContext())
                        .load("PUT",url)
                        .addHeader("Authorization", "Bearer "+ token)
                        .setBodyParameter("idAdmin", String.valueOf(idAdm))
                        .setBodyParameter("nom", String.valueOf(nom.getText()))
                        .setBodyParameter("photo", String.valueOf(photo.getText()))
                        .setBodyParameter("heure_passage", String.valueOf(h.getText()))
                        .setBodyParameter("date_passage", String.valueOf(d.getText()))
                        .setBodyParameter("style", String.valueOf(style.getText()))
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result == null){
                                    Toast.makeText(AjouterArtisteActivity.this, "Ajout effectué", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(AjouterArtisteActivity.this, "Erreur inconnue", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }
}