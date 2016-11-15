package com.party.party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        TextView txtTermos = (TextView) findViewById(R.id.txtTermos);

        txtTermos.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v) {

                                                Intent it = new Intent(Registro.this, TermosDeUso.class);
                                                startActivity(it);
                                            }
                                        }

        );
    }
}
