package com.example.projetfestivalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.projetfestivalv2.classes.Artiste;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class ModifArtisteListeActivity extends AppCompatActivity {
    ListView liste;
    Intent intent;
    Button actualiser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_artiste_liste);
        getSupportActionBar().hide();

        intent = this.getIntent();
        int idAdm = intent.getIntExtra("idAdm", 0);
        String token = intent.getStringExtra("token");
        actualiser = findViewById(R.id.actualiser);

        liste = findViewById(R.id.listeModifArtiste);
        String url = "http://10.0.2.2:3000/artiste/";
        Ion.with(ModifArtisteListeActivity.this.getApplicationContext())
                .load("GET", url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        ArrayList<Artiste> listeArtiste = new ArrayList<>();
                        for (Object o : result) {
                            JsonObject element = (JsonObject) o;
                            int id = element.getAsJsonObject().get("id").getAsInt();
                            String nom = element.getAsJsonObject().get("nom").getAsString();
                            String photo = element.getAsJsonObject().get("photo").getAsString();
                            String date_passage = element.getAsJsonObject().get("date_passage").getAsString();
                            String heure_passage = element.getAsJsonObject().get("heure_passage").getAsString();
                            String style = element.getAsJsonObject().get("style").getAsString();

                            Artiste artiste = new Artiste(id, nom, photo, heure_passage, date_passage, style);
                            listeArtiste.add(artiste);
                        }
                        liste.setAdapter(new ArtisteAdapteur(ModifArtisteListeActivity.this,
                                R.layout.list_row,
                                listeArtiste));
                        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { // On envois à MainActifity2 la bière sur laquel l'utilisateur a cliqué
                                Intent intent = new Intent(ModifArtisteListeActivity.this, ModifArtisteActivity.class);
                                intent.putExtra("artiste", listeArtiste.get(i));
                                intent.putExtra("idAdm", idAdm);
                                intent.putExtra("token", token);
                                startActivity(intent);
                            }
                        });
                    }
                });

        actualiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ion.with(ModifArtisteListeActivity.this.getApplicationContext())
                        .load("GET", url)
                        .asJsonArray()
                        .setCallback(new FutureCallback<JsonArray>() {
                            @Override
                            public void onCompleted(Exception e, JsonArray result) {
                                ArrayList<Artiste> listeArtiste = new ArrayList<>();
                                for (Object o : result) {
                                    JsonObject element = (JsonObject) o;
                                    int id = element.getAsJsonObject().get("id").getAsInt();
                                    String nom = element.getAsJsonObject().get("nom").getAsString();
                                    String photo = element.getAsJsonObject().get("photo").getAsString();
                                    String date_passage = element.getAsJsonObject().get("date_passage").getAsString();
                                    String heure_passage = element.getAsJsonObject().get("heure_passage").getAsString();
                                    String style = element.getAsJsonObject().get("style").getAsString();

                                    Artiste artiste = new Artiste(id, nom, photo, heure_passage, date_passage, style);
                                    listeArtiste.add(artiste);
                                }
                                liste.setAdapter(new ArtisteAdapteur(ModifArtisteListeActivity.this,
                                        R.layout.list_row,
                                        listeArtiste));
                            }
                        });
            }
        });

    }
}