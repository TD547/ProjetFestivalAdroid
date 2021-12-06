package com.example.projetfestivalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projetfestivalv2.classes.Artiste;

import java.net.URISyntaxException;

public class ConsultArtisteActivity extends AppCompatActivity {
    ImageView imageView;
    Intent intent;
    Artiste info;
    TextView titre;
    TextView genre;
    TextView jour;
    TextView heure;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_artiste);
        getSupportActionBar().hide();

        intent = this.getIntent();
        info = intent.getParcelableExtra("artiste");

        imageView = findViewById(R.id.imageArtiste);
        titre = findViewById(R.id.Titre);
        genre = findViewById(R.id.Genre);
        jour = findViewById(R.id.jour);
        heure = findViewById(R.id.heure);

        Glide.with(this).load(info.getPhoto()).into(imageView);
        titre.setText(info.getNom());
        genre.setText(info.getStyle());
        jour.setText(info.getDate_passage());
        heure.setText(info.getHeure_passage() + " h");
    }
}