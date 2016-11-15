package com.party.party;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends Activity {

    final Context context = this;
    Button confirmar;
    String insertUrl = "http://partyiftm.16mb.com/bd/insertUser.php";
    RequestQueue requestQueue;
    EditText usuario, senha, senhaConfirma, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);





        TextView txtTermos = (TextView) findViewById(R.id.txtTermos);
        confirmar = (Button) findViewById(R.id.botao_confirmar);
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        senhaConfirma = (EditText) findViewById(R.id.senha2);
        email = (EditText) findViewById(R.id.email);

        txtTermos.setOnClickListener(new View.OnClickListener(){

                                            public void onClick(View v) {

                                                Intent it = new Intent(Registro.this, TermosDeUso.class);
                                                startActivity(it);
                                            }
                                        }

        );

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {



                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        if(senha.getText().toString().equals(senhaConfirma.getText().toString()))
                        {
                        Map<String,String> parameters  = new HashMap<String, String>();
                            parameters.put("nome",usuario.getText().toString());
                            parameters.put("senha",senha.getText().toString());
                            parameters.put("email",email.getText().toString());

                            System.out.println("Ok");

                        return parameters;
                        }
                        else
                        {
                            System.out.println("Senhas não batem!");
                        }
                        return null;
                    }
                };
                //Toast.makeText(context, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                requestQueue.add(request);

            }

        });




    }
}