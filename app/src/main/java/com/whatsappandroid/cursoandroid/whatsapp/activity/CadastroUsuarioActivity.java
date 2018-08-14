package com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whatsappandroid.cursoandroid.whatsapp.R;
import com.whatsappandroid.cursoandroid.whatsapp.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button btn_cadastrar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_cadastro_usuario);

        nome = findViewById(R.id.edt_nome);
        email = findViewById(R.id.edt_email);
        senha = findViewById(R.id.edt_senha);
        btn_cadastrar = findViewById(R.id.bt_cadastrar);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                cadastrarUsuario(usuario.getNome(),usuario.getEmail(),usuario.getSenha());



            }
        });

    }

    private void cadastrarUsuario(String nome, String email, String senha) {

        nome = usuario.getNome();
        

    }


}
