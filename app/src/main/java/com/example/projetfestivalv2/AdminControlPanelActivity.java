package com.example.projetfestivalv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminControlPanelActivity extends AppCompatActivity {
    Button butF;
    Button butA;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control_panel);
        getSupportActionBar().hide();

        butF = findViewById(R.id.butA);
        butA = findViewById(R.id.butB);

        intent = this.getIntent();
        int idAdm = intent.getIntExtra("idAdm",0);
        String token = intent.getStringExtra("token");

        butF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminControlPanelActivity.this, ModifFestActivity.class);
                intent.putExtra("idAdm", idAdm);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        butA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminControlPanelActivity.this, AdminControlPanelActivityArtiste.class);
                intent.putExtra("idAdm", idAdm);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
    }


}