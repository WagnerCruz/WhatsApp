package com.whatsappandroid.cursoandroid.whatsapp.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.whatsappandroid.cursoandroid.whatsapp.config.ConfiguracaoFirebase;

public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;

    @Exclude //anotação para que ele não considere o getId do objeto usuario
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    //este construtor é criado vazio por exigencia do firebase, pois necessita de um construtor criado
    // para poder salvar os dados no banco
    public Usuario() {

    }

    public void salvar() {
        DatabaseReference referenciaFB = ConfiguracaoFirebase.getFirebase();

        //aqui cria os nós (branches) no firebase, abaixo serão criados 2 nós:
        //o primeiro nó é "Uusarios" - .child("Usuarios")
        //o segundo nó é o ID criado para aquele usuário sendo cadastrado no firebase - .child(getId())
        //os valores carregados no objeto usuario são gravados no banco (nome, id, email, senha)
        referenciaFB.child("Usuarios").child(getId()).setValue(this);



    }


}
