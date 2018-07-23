package com.whatsappandroid.cursoandroid.whatsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.whatsappandroid.cursoandroid.whatsapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText fone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fone = findViewById(R.id.edit_fone);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN) NNN-NNN-NNN");
        MaskTextWatcher maskFone = new MaskTextWatcher(fone, smf);
        fone.addTextChangedListener(maskFone);

    }
}
