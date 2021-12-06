package com.example.projetfestivalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetfestivalv2.classes.Festival;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifFestActivity extends AppCompatActivity {
    EditText deb;
    EditText fin;
    Button but;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_fest);
        getSupportActionBar().hide();

        deb = findViewById(R.id.editDateDeb);
        fin = findViewById(R.id.editDateFin);
        but = findViewById(R.id.button2);
        intent = this.getIntent();
        int idAdm = intent.getIntExtra("idAdm",0);
        String token = intent.getStringExtra("token");

        String url2 = "http://10.0.2.2:3000/festival/";
        Ion.with(ModifFestActivity.this.getApplicationContext())
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
                        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
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
                        deb.setText(fest.getDate_deb());
                        fin.setText(fest.getDate_fin());
                    }
                });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2:3000/festival/1";
                Ion.with(ModifFestActivity.this.getApplicationContext())
                        .load("POST",url)
                        .addHeader("Authorization", "Bearer "+ token)
                        .setBodyParameter("idAdmin", String.valueOf(idAdm))
                        .setBodyParameter("date_deb", String.valueOf(deb.getText()))
                        .setBodyParameter("date_fin", String.valueOf(fin.getText()))
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result == null){
                                    Toast.makeText(ModifFestActivity.this, "Modification effectuée", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ModifFestActivity.this, "Erreur inconnue", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}