package com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.whatsappandroid.cursoandroid.whatsapp.Helper.Permissoes;
import com.whatsappandroid.cursoandroid.whatsapp.Helper.Preferencias;
import com.whatsappandroid.cursoandroid.whatsapp.R;

import java.util.Random;

//import com.whatsappandroid.cursoandroid.whatsapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText fone;
    private EditText codarea;
    private EditText cod_ddd;
    private Button cadastrar;
    private String[] permissoes = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permissoes.validarPermissoes(this, permissoes);


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

                //Gerar token
                Random randomico = new Random();
                int valorRandom = randomico.nextInt(9999 - 1000)+1000;
                String token = String.valueOf(valorRandom);
                String mensagemEnvio = "Codigo de confirmação: " + token;

//                Log.i("TOKEN", "Token: " + token);

                //Salvar dados para validação
                Preferencias preferencias = new Preferencias(getApplicationContext());
                preferencias.salvarPreferencias(nomeuser,fonesemformat,token);

                boolean enviadosms = enviaSMS("+" + fonesemformat, mensagemEnvio);

                Log.i("RETORNO ENVIO SMS", "Retorno: " + enviadosms);


                //envio de SMS

//
//                HashMap<String, String> usuario = preferencias.getDadosUsuario();//
//                Log.i("TOKEN", "TOKEN:" + usuario.get("token") + " Nome: " +usuario.get("nome") + " Fone: " +usuario.get("teleWagnerfone") );

            }
        });

    }

    public MaskTextWatcher MascaraDados(String formato, EditText dados){

        SimpleMaskFormatter smf = new SimpleMaskFormatter(formato);
        MaskTextWatcher mascara_dados = new MaskTextWatcher(dados, smf);

        return mascara_dados;
    }

    //Enviar o SMS

    private boolean enviaSMS(String telefone, String mensagem) {

        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone,null,mensagem,null,null);

            return true;


        } catch (Exception e) {
            e.printStackTrace();

            return false;

        }


    };


}





