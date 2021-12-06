package com.example.projetfestivalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.example.projetfestivalv2.classes.*;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConsultFestActivity extends AppCompatActivity {
    ListView liste;
    TextView date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_fest);
        getSupportActionBar().hide();

        liste = findViewById(R.id.ListeArtiste1);
        String url = "http://10.0.2.2:3000/artiste/";
        Ion.with(ConsultFestActivity.this.getApplicationContext())
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
                        liste.setAdapter(new ArtisteAdapteur(ConsultFestActivity.this,
                                R.layout.list_row,
                                listeArtiste));
                        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { // On envois à MainActifity2 la bière sur laquel l'utilisateur a cliqué
                                Intent intent = new Intent(ConsultFestActivity.this, ConsultArtisteActivity.class);
                                intent.putExtra("artiste", listeArtiste.get(i));
                                startActivity(intent);
                            }
                        });
                    }
                });

        String url2 = "http://10.0.2.2:3000/festival/";
        Ion.with(ConsultFestActivity.this.getApplicationContext())
                .load("GET", url2)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        JsonObject element = (JsonObject) result.get(0);
                        int id = element.getAsJsonObject().get("id").getAsInt();
                        String date_deb = element.getAsJsonObject().get("date_deb").getAsString();
                        String date_fin = element.getAsJsonObject().get("date_fin").getAsString();

                        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date parsedDateDeb = null;
                        Date parsedDateFin = null;

                        try {
                            parsedDateDeb = inputFormat.parse(date_deb);
                            parsedDateFin = inputFormat.parse(date_fin);
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                        String formattedDateDeb = outputFormat.format(parsedDateDeb);
                        String formattedDateFin = outputFormat.format(parsedDateFin);

                        Festival fest = new Festival(id, formattedDateDeb, formattedDateFin);
                        date = findViewById(R.id.textViewDate);
                        date.setText("Du " + fest.getDate_deb() + " 10h au " + fest.getDate_fin() + " 4h !");
                    }
                });



    }
}