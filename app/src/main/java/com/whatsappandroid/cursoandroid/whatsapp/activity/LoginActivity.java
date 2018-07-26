package com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.whatsappandroid.cursoandroid.whatsapp.Helper.Preferencias;
import com.whatsappandroid.cursoandroid.whatsapp.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText fone;
    private EditText codarea;
    private EditText cod_ddd;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nome = findViewById(R.id.idnome);
        fone = findViewById(R.id.edit_fone);
        codarea = findViewById(R.id.id_cod_area);
        cod_ddd = findViewById(R.id.id_cod_ddd);
        cadastrar = findViewById(R.id.btncadastrar);


        fone.addTextChangedListener(MascaraDados("NNN-NNN-NNN",fone));
        codarea.addTextChangedListener(MascaraDados("+NN",codarea));
        cod_ddd.addTextChangedListener(MascaraDados("NN",cod_ddd));

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeuser = nome.getText().toString();

                String telefoneCompleto = codarea.getText().toString() +
                        cod_ddd.getText().toString() +
                        fone.getText().toString();

                String fonesemformat = telefoneCompleto.replace("+", "");
                fonesemformat = fonesemformat.replace("-", "");

                Random randomico = new Random();
                int valorRandom = randomico.nextInt(9999 - 1000)+1000;
                String token = String.valueOf(valorRandom);

//                Log.i("TOKEN", "Token: " + token);

                Preferencias preferencias = new Preferencias(getApplicationContext());
                preferencias.salvarPreferencias(nomeuser,fonesemformat,token);

                HashMap<String, String> usuario = preferencias.getDadosUsuario();

                Log.i("TOKEN", "TOKEN:" + usuario.get("token") + " Nome: " +usuario.get("nome") + " Fone: " +usuario.get("teleWagnerfone") );

            }
        });

    }

    public MaskTextWatcher MascaraDados(String formato, EditText dados){

        SimpleMaskFormatter smf = new SimpleMaskFormatter(formato);
        MaskTextWatcher mascara_dados = new MaskTextWatcher(dados, smf);

        return mascara_dados;
    }

}


