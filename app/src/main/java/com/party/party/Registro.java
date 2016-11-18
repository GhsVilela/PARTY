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
import android.widget.CheckBox;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends Activity {

    final Context context = this;
    Button confirmar;
    String insertUrl = "http://partyiftm.16mb.com/bd/insertUser.php";
    RequestQueue requestQueue;
    EditText usuario, senha, senhaConfirma, email;
    CheckBox checkBox;
    int flag = 0;

    public void mensagem()
    {
        if(flag==3)
        {
            Toast.makeText(context, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else if(flag==2)
        {
            Toast.makeText(context, "Senhas digitadas não coincidem!", Toast.LENGTH_SHORT).show();
        }
        else if(flag==1)
        {
            Toast.makeText(context, "Por favor, Aceite os termos!", Toast.LENGTH_SHORT).show();
        }
        else if(flag==0)
        {
            Toast.makeText(context, "Email não é válido!", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


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
        checkBox = (CheckBox) findViewById((R.id.checkBox));

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
                        mensagem();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {



                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        boolean validarEmail = isEmailValid(email.getText().toString());

                        if(senha.getText().toString().equals(senhaConfirma.getText().toString()) && checkBox.isChecked()==true && validarEmail==true)
                        {
                        Map<String,String> parameters  = new HashMap<String, String>();
                            parameters.put("nome",usuario.getText().toString());
                            parameters.put("senha",senha.getText().toString());
                            parameters.put("email",email.getText().toString());

                            flag=3;

                        return parameters;
                        }
                        else if(!senha.getText().toString().equals(senhaConfirma.getText().toString()))
                        {
                            flag=2;
                        }
                        else if(checkBox.isChecked()==false)
                        {
                            flag = 1;
                        }
                        else if(validarEmail == false)
                        {
                            flag = 0;
                        }

                        return null;
                    }

                };
                requestQueue.add(request);



            }

        });




    }
}