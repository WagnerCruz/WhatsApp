package com.whatsappandroid.cursoandroid.whatsapp.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences; //para gravar um dado local no aparelho
    private final String NOME_ARQUIVO = "Whatsapp.Preferencias"; //nome do arquivo que será criado
    private int MODE = 0; //restrição para que, somente o aplicativo que está criando o arquivo, tenha acesso ao mesmo
    private SharedPreferences.Editor editor;

    private String CHAVE_NOME = "nome";
    private String CHAVE_FONE = "telefone";
    private String CHAVE_TOKEN = "token";


    public Preferencias(Context contextoParametro) {

        contexto = contextoParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO, MODE);
        editor = preferences.edit(); // iniciar a edição do arquivo de preferências criado


    }

    public void salvarPreferencias(String nome, String telefone, String token) {

        editor.putString(CHAVE_NOME, nome);
        editor.putString(CHAVE_FONE, telefone);
        editor.putString(CHAVE_TOKEN, token);
        editor.commit();
    }

    public HashMap<String, String> getDadosUsuario(){

        HashMap<String, String> dadosUsuario = new HashMap<>();
        dadosUsuario.put(CHAVE_NOME, preferences.getString(CHAVE_NOME,null));
        dadosUsuario.put(CHAVE_FONE, preferences.getString(CHAVE_FONE,null));
        dadosUsuario.put(CHAVE_TOKEN, preferences.getString(CHAVE_TOKEN,null));


        return dadosUsuario;
    }



}
