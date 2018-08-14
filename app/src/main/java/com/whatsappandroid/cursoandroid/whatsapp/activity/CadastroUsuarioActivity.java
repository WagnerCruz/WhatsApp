package com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.whatsappandroid.cursoandroid.whatsapp.R;
import com.whatsappandroid.cursoandroid.whatsapp.config.ConfiguracaoFirebase;
import com.whatsappandroid.cursoandroid.whatsapp.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button btn_cadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

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
            public void onClick(View v) {

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

        //cria a instância do autenticador do firebase passando email e senha
        // como parâmetros para cadastro de autenticação
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroUsuarioActivity.this,"Usuário cadastrado com sucesso",Toast.LENGTH_SHORT).show();

                    // recupera os dados do usuário a partir do objeto firebase retornado no task
                    usuario.setId(task.getResult().getUser().getUid());
                    usuario.salvar();

                    autenticacao.signOut();
                    finish();

                } else {
                    String erro = "";

                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) { // veerifica erro na senha informada pelo usuário
                        erro = "A senha digitada não é forte o suficiente.";

                        e.printStackTrace();
                    } catch (FirebaseAuthInvalidCredentialsException e) { //verificar erro no email informado pelo usuário
                        erro = "O email digitado é inválido";

                    } catch (FirebaseAuthUserCollisionException e) { //identifica que o usuário já está cadastrado no aplicativo
                        erro = "Este usuário já está cadastrado no aplicativo";

                    } catch (Exception e) {
                        erro = "Erro ao efetuar o cadastro";
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroUsuarioActivity.this,"Erro: " + erro,Toast.LENGTH_SHORT).show();
                }

            }
        });
        

    }


}
