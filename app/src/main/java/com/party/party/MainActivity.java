package com.party.party;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLocalizar = (Button) findViewById(R.id.botao_ponto_maps);
        Button btnEntrar = (Button) findViewById(R.id.botao_entrar);
        Button btnRegistrar = (Button) findViewById(R.id.botao_registrar);
        btnLocalizar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
               //  Intent it = new Intent(MainActivity.this, MapsActivity.class);
                Intent it = new Intent(MainActivity.this, LocalizacaoDispositivo.class);
                startActivity(it);
            }
        }

        );


        btnEntrar.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v) {
                //  Intent it = new Intent(MainActivity.this, MapsActivity.class);
                Intent it = new Intent(MainActivity.this, Login.class);
                startActivity(it);
            }
        }

        );


        btnRegistrar.setOnClickListener(new View.OnClickListener(){

                                         public void onClick(View v) {
                 //  Intent it = new Intent(MainActivity.this, MapsActivity.class);
                 Intent it = new Intent(MainActivity.this, Registro.class);
                 startActivity(it);
             }
         }

        );
    }




}