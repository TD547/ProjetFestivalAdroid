package com.example.projetfestivalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminControlPanelActivityArtiste extends AppCompatActivity {
    Button butA;
    Button butB;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control_panel_artiste);
        getSupportActionBar().hide();

        butA = findViewById(R.id.butA);
        butB = findViewById(R.id.butB);

        intent = this.getIntent();
        int idAdm = intent.getIntExtra("idAdm",0);
        String token = intent.getStringExtra("token");

        butA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminControlPanelActivityArtiste.this, ModifArtisteListeActivity.class);
                intent.putExtra("idAdm", idAdm);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        butB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminControlPanelActivityArtiste.this, AjouterArtisteActivity.class);
                intent.putExtra("idAdm", idAdm);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
    }
}
