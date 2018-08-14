package com.whatsappandroid.cursoandroid.whatsapp.Helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissoes {

    public static boolean validarPermissoes(int requestcode,Activity activity,String[] permissoes) {

        if (Build.VERSION.SDK_INT >= 23) {

            List<String> listapermissoes = new ArrayList<String>();


            //verifica a lista de permissões caso alguma delas não faça parte das permissões atualmente garantidas
            //gguarda na lista a permissão não existente na lista atual
            for (String permissao : permissoes) {
                //checkSelf verifica se para a activity existe a permissão necessária/solicitada
                Boolean validapermissao = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;

                if (!validapermissao) listapermissoes.add(permissao);

            }

            // se a lista estiver vazia, não é necessária nenhuma outra permissão além das já existentes
            if (listapermissoes.isEmpty()) return true;

            //converter o objeto list para uma lista de array
            String[] novaspermissoes = new String[listapermissoes.size()];
            listapermissoes.toArray(novaspermissoes);

            //solicita permissão
            ActivityCompat.requestPermissions(activity,novaspermissoes,requestcode);

        }


        return true;
    }

}
