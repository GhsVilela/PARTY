package com.party.party;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Logado extends AppCompatActivity {

    Button sair, mapa, criarEvento, perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);

        sair = (Button)findViewById(R.id.buttonsair);
        mapa = (Button)findViewById(R.id.buttonmapa);
        criarEvento = (Button)findViewById(R.id.buttoncriarevento);
        perfil = (Button)findViewById(R.id.buttonperfil);


        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EditarPerfil.class));
            }
        });
        criarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EditarFesta.class));
            }
        });
    }
}
