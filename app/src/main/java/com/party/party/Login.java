package com.party.party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {
    Button btnEntrar;
    TextView text;
    EditText nome;
    EditText  senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    btnEntrar = (Button)findViewById(R.id.botao_entrar);
     //   nome = (EditText) findViewById(R.id.)

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,PerfilUsuario.class);
                startActivity(intent);


                String senha = "admin";

                MessageDigest algorithm = null;
                try {
                    algorithm = MessageDigest.getInstance("SHA-256");
                    byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
                    System.out.println(messageDigest);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }





            }
        });

    }
}
