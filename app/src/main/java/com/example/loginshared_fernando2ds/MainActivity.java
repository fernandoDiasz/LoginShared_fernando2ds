package com.example.loginshared_fernando2ds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CheckBox checkboxGravar;
    Button btn_entrar, btn_novoUser;
    EditText edt_nome, edt_email, edt_senha;
    SharedPreferences preferences;

    TextView txt_logoff;
    private void initComponents(){
        checkboxGravar = findViewById(R.id.checkboxGravar);
        btn_entrar = findViewById(R.id.btn_entrar);
        btn_novoUser = findViewById(R.id.btn_novoUser);
        edt_nome = findViewById(R.id.edt_nome);
        edt_email = findViewById(R.id.edt_email);
        edt_senha = findViewById(R.id.edt_senha);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        preferences = getSharedPreferences("Login", 0);
        String pedt_nome = preferences.getString("chaveNome", "");
        String pedt_email = preferences.getString("chaveEmail", "");
        String pedt_senha = preferences.getString("chaveSenha", "");

        if(!pedt_nome.isEmpty() && !pedt_email.isEmpty() && !pedt_senha.isEmpty()){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }



        /*



        edt_nome.requestFocus();

        checkboxGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("chaveNome", edt_nome.getText().toString());
                editor.putString("chaveEmail", edt_email.getText().toString());
                editor.putString("chaveSenha", edt_senha.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this, "Gravado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        */



        btn_entrar.setOnClickListener(new View.OnClickListener() {
            private boolean validarDados(){
                boolean retorno = true;
                if(edt_nome.getText().toString().isEmpty()){
                    retorno = false;
                    edt_nome.setError("Não pode estar vazio");
                }
                if(edt_email.getText().toString().isEmpty()){
                    retorno = false;
                    edt_email.setError("Não pode estar vazio");
                }
                if(edt_senha.getText().toString().isEmpty()){
                    retorno = false;
                    edt_senha.setError("Não pode estar vazio");
                }
                return retorno;
            }

            @Override
            public void onClick(View v) {

                if(validarDados()) {
                    if (checkboxGravar.isChecked()) {
                        SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("chaveNome", edt_nome.getText().toString());
                        editor.putString("chaveEmail", edt_email.getText().toString());
                        editor.putString("chaveSenha", edt_senha.getText().toString());
                        editor.commit();
                    }
                }
            }
        });

        edt_nome.setText("");
        edt_email.setText("");
        edt_senha.setText("");


        txt_logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();

            }
        });

        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);

        /*
        edt_nome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String nome;
                SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);

                nome=prefs.getString("chaveNome", "");



                edt_nome.setText(nome);
            }
        });

        edt_email.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email;
                SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                email=prefs.getString("chaveEmail", "");
                edt_email.setText(email);
            }
        });

        edt_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senha;
                SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                senha=prefs.getString("chaveSenha", "");
                edt_senha.setText(senha);
            }
        });

         */

    }
}