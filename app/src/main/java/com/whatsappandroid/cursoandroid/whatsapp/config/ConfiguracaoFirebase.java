package com.whatsappandroid.cursoandroid.whatsapp.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class ConfiguracaoFirebase {
    //ao utilizar final a classe não pode ser extendida

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;

    public static DatabaseReference getFirebase() {
        //ao definir o metodo como static não é necessário referenciar o método (utilizando new)
        //a chamada pode ser feita diretamente

        if (referenciaFirebase == null) {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }

        return referenciaFirebase;
    }

    //verificação de autenticação no firebase
    public static FirebaseAuth getFirebaseAutenticacao() {

        if (autenticacao == null) { //verifica se usuário já está autenticado no firebase
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }

}
