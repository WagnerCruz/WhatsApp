package com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;
import com.whatsappandroid.cursoandroid.whatsapp.R;
import com.whatsappandroid.cursoandroid.whatsapp.config.ConfiguracaoFirebase;
import com.whatsappandroid.cursoandroid.whatsapp.model.Usuario;

//import com.whatsappandroid.cursoandroid.whatsapp.R;

public class LoginActivity extends AppCompatActivity {

//    private EditText nome;
//    private EditText fone;
//    private EditText codarea;
//    private EditText cod_ddd;
//    private Button cadastrar;
//    private String[] permissoes = new String[]{
//            Manifest.permission.SEND_SMS,
//            Manifest.permission.INTERNET};

    private EditText email;
    private EditText senha;
    private Button btn_login;
    private Usuario usuario;
    private FirebaseAuth autenticador; //realizar a autenticação do usuário e senha digitados


    private DatabaseReference referenciaFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edit_email);
        senha = findViewById(R.id.edit_senha);
        btn_login = findViewById(R.id.btlogin);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = new Usuario();

                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                validateLogin();

            }
        });


       }

    private void validateLogin() {

        //realizara a autenticação do usuário utiilizando o email e senha digitados
        autenticador = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //informa o email e senha digitados, e usa o metodo addOnCompleteListener para verificar se a autenticação foi feita corretamente
        autenticador.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                String erro = "";

                //task.isSuccesfull retorna se o login foi feito corretamente
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,"Login efetuado" , Toast.LENGTH_SHORT).show();
                }else{

                    try {
                        throw task.getException();

                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "A senha digitada está incorreta";

                    } catch (FirebaseAuthInvalidUserException e) {
                        erro = "O Email digitado está incorreto";

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this,"Erro: " + erro , Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    public void abrirCadastroUsuario(View view) {

        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);

    }

}

// Codigos exemplo para validação de token e envio de SMS

/*
        Permissoes.validarPermissoes(1,this, permissoes);

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

*/






