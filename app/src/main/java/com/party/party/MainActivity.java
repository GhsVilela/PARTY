package com.party.party;

import android.app.Activity;
import android.content.Context;
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

public class MainActivity extends Activity {
    Button btnLocalizar;
    Button btnEntrar;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLocalizar = (Button) findViewById(R.id.botao_ponto_maps);
        btnEntrar = (Button) findViewById(R.id.botao_entrar);
        btnRegistrar = (Button) findViewById(R.id.botao_registrar);

        btnLocalizar.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v) {
                                                  Intent it = new Intent(MainActivity.this, LocalizacaoDispositivo.class);
                                                //Intent it = new Intent(getContext(), LocalizacaoDispositivo.class);
                                                startActivity(it);

                                            }
                                        }

        );


        btnEntrar.setOnClickListener(new View.OnClickListener(){

                                         public void onClick(View v) {
                                             Intent it = new Intent(MainActivity.this, Login.class);
                                             //Intent it = new Intent(getContext(), Login.class);
                                             startActivity(it);

                                         }
                                     }

        );

        btnRegistrar.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v) {
                                                Intent it = new Intent(MainActivity.this, Registro.class);
                                                //Intent it = new Intent(getContext(), Registro.class);
                                                startActivity(it);

                                            }
                                        }

        );




    }
    private Context getContext(){
        return this;
    }



}
