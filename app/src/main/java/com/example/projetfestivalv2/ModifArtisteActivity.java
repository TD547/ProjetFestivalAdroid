package com.example.projetfestivalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetfestivalv2.classes.Artiste;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ModifArtisteActivity extends AppCompatActivity {
    Intent intent;
    Artiste info;
    EditText nom;
    EditText photo;
    EditText style;
    EditText h;
    EditText d;
    Button but;
    Button sup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_artiste);
        getSupportActionBar().hide();

        intent = this.getIntent();
        info = intent.getParcelableExtra("artiste");
        int idAdm = intent.getIntExtra("idAdm", 0);
        String token = intent.getStringExtra("token");

        nom = findViewById(R.id.modifNom);
        photo = findViewById(R.id.modifPhoto);
        style = findViewById(R.id.modifStyle);
        h = findViewById(R.id.modifHP);
        d = findViewById(R.id.modifDP);
        but = findViewById(R.id.button3);
        sup = findViewById(R.id.sup);

        nom.setText(info.getNom());
        photo.setText(info.getPhoto());
        style.setText(info.getStyle());
        h.setText(info.getHeure_passage());
        d.setText(info.getDate_passage());

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2:3000/artiste/" + info.getId();
                Ion.with(ModifArtisteActivity.this.getApplicationContext())
                        .load("POST", url)
                        .addHeader("Authorization", "Bearer " + token)
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
                                if (result == null) {
                                    Toast.makeText(ModifArtisteActivity.this, "Modification effectuée", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ModifArtisteActivity.this, "Erreur inconnue", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2:3000/artiste/" + info.getId();
                Ion.with(ModifArtisteActivity.this.getApplicationContext())
                        .load("DELETE", url)
                        .addHeader("Authorization", "Bearer " + token)
                        .setBodyParameter("idAdmin", String.valueOf(idAdm))
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result == null) {
                                    Toast.makeText(ModifArtisteActivity.this, "Modification effectuée", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(ModifArtisteActivity.this, "Erreur inconnue", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}